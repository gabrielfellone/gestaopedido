package com.example.pedidos.repository;

import com.example.pedidos.entity.Pedido;
import com.example.pedidos.entity.Produtos;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface ProdutosRepository extends CassandraRepository<Produtos, UUID> {
}
