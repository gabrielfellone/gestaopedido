package com.example.pedidos.entity.dto;

import lombok.Builder;

import java.util.UUID;


@Builder
public record PedidoDto(UUID id, UUID idCliente, UUID idProduto, UUID idCarrinho) {
}
