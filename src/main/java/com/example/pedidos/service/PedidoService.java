package com.example.pedidos.service;

import com.example.pedidos.controller.resources.PedidoRequest;
import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.dto.PedidoDto;
import com.example.pedidos.exception.PedidoException;
import com.example.pedidos.integration.producer.MessageProducer;
import com.example.pedidos.repository.PedidoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@RequiredArgsConstructor
@Service
public class PedidoService {

    @Autowired
    private MessageProducer producer;

    private final PedidoRepository pedidoRepository;

    private final String SUCESSO = "PAGAMENTO REALIZADO COM SUCESSO, POR FAVOR, AGUARDE";
    private final String ERROR = "TIVEMOS UM PROBLEMA EM SEU PEDIDO";

    public void realizarPedido(PedidoRequest pedidoRequest) {

        PedidoDto pedido = PedidoDto.builder()
                .id(UUID.randomUUID())
                .idCliente(pedidoRequest.getIdCliente())
                .idProduto(pedidoRequest.getIdProduto())
                .idCarrinho(pedidoRequest.getIdCarrinho()).build();


        String validaPagamento = validaPagamento(pedido);

        log.info(validaPagamento);

    }

    public String solicitarPedido(PedidoDto pedido) {
        log.info("Pedido validado pagamento do pedido...");
        try {
            producer.validaPagamento(pedido);
        } catch (JsonProcessingException e) {
            return "Ocorreu um erro ao solicitar pedido .. " + e.getMessage();
        }
        return "Pedido aguardando entrega ...";
    }
    public void sucessoPedido(String payload) throws JSONException {
        log.info("Sucesso ao solicitar pedido: {} ", payload);
        getMessage(payload, SUCESSO);
    }
    
    public void erroPedido(String payload) throws JSONException {
        log.info("Erro ao solicitar pedido: {} ", payload);
        getMessage(payload, ERROR);
    }

    private void getMessage(String payload, String status) throws JSONException {

        JSONObject jsonObject = new JSONObject(payload);
        log.info("Json Message: {} ", jsonObject);

        String id = jsonObject.getString("id");
        String idCliente = jsonObject.getString("idCliente");
        String idProduto = jsonObject.getString("idProduto");
        String idCarrinho = jsonObject.getString("idCarrinho");

        Pedido pedido = new Pedido(id, idCliente, idProduto, status);

        salvaPedido(pedido);

        if(status.equalsIgnoreCase(SUCESSO)){
            log.info("Pagamento validado com sucesso {}", pedido);

        }

    }

    public void salvaPedido(Pedido pedido){
        log.info("Atualizando pedido na base {}", pedido);
        try {
            pedidoRepository.save(pedido);
        }  catch (PedidoException e){
            throw new PedidoException("Erro ao atualizar pedido na base");
        }

    }
    public String validaPagamento(PedidoDto pedido){
        log.info("Validando pagamento {}", pedido);
        try {
            log.info("Postando na fila para validar pagamento");
            producer.validaPagamento(pedido);
        } catch (JsonProcessingException e) {
            return "Ocorreu um erro ao validar pagamento .. " + e.getMessage();
        }
        return "Enviado com sucesso para servico de produtos";
    }
}
