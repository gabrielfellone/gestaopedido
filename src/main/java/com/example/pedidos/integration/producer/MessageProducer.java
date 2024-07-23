package com.example.pedidos.integration.producer;

import com.example.pedidos.entity.dto.PedidoDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class MessageProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();


    public void validaPagamento(PedidoDto pedidoDto) throws JsonProcessingException {
        amqpTemplate.convertAndSend(
                "pedido-pagamento-exchange",
                "pedido-pagamento-rout-key",
                objectMapper.writeValueAsString(pedidoDto)
        );
    }
}
