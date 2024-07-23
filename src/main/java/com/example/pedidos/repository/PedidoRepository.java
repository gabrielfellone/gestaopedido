package com.example.pedidos.repository;

import com.example.pedidos.entity.Pedido;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface PedidoRepository  extends CassandraRepository<Pedido, UUID> {
}
