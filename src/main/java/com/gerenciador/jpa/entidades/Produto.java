package com.gerenciador.jpa.entidades;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "PRODUTO")

public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "valor", nullable = false)
    public double valor;

    @Column(name = "quantidade", nullable = false)
    public int quantidade;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}




