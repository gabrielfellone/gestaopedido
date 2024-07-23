package com.example.pedidos.integration.consumer;

import com.example.pedidos.service.PedidoService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PedidoConsumerSucesso {

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = {"pedido-response-sucesso-queue"})
    public void receive(@Payload Message message) throws JSONException {
        String payload = String.valueOf(message.getPayload());
        pedidoService.sucessoPedido(payload);
    }

    @RabbitListener(queues = {"pedido-entregue-sucesso-queue"})
    public void entreguePedido(@Payload Message message) throws JSONException {
        String payload = String.valueOf(message.getPayload());
        pedidoService.entreguePedido(payload);
    }


}
