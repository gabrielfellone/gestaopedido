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
@Table("produtos")
public class Produtos {

    @Column
    @PrimaryKey
    private UUID id;
    private String nome;
    private String preco;
    private String descricao;

    public Produtos(String id, String nome,
                    String preco, String descricao){

        this.id = UUID.fromString(id);
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;

    }
}
