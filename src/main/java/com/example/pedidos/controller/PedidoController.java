package com.example.pedidos.controller;

import com.example.pedidos.controller.resources.PedidoRequest;
import com.example.pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RestController
@RequestMapping("/v1/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    @PostMapping("realizarPedido")
    public ResponseEntity<String> realizarPedido(@RequestBody PedidoRequest request) {
        log.info("Realizando pedido {}", request);
        pedidoService.realizarPedido(request);
        return ResponseEntity.status(CREATED).body("Pedido enviado com sucesso, por favor, acompanhar o status do pedido!");
    }

}
