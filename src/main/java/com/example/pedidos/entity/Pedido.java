package com.example.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("pedido")
public class Pedido {
    
    @Column
    @PrimaryKey
    private UUID id;
    private UUID idCliente;
    private Long idProduto;
    private String status;

    public Pedido(String id, String idCliente,
                  String idProduto, String status){

        this.id = UUID.fromString(id);
        this.idCliente = UUID.fromString(idCliente);
        this.idProduto = Long.parseLong(idProduto);
        this.status = status.toUpperCase();

    }
}
