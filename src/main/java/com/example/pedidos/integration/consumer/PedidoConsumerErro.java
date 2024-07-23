package com.example.pedidos.integration.consumer;

import com.example.pedidos.service.PedidoService;
import org.json.JSONException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PedidoConsumerErro {

    @Autowired
    private PedidoService pedidoService;

    @RabbitListener(queues = {"pedido-response-erro-queue"})
    public void receive(@Payload Message message) throws JSONException {
        System.out.println("Message " + message + "  " + LocalDateTime.now());
        String payload = String.valueOf(message.getPayload());

        pedidoService.erroPedido(payload);
    }
}
