package com.gerenciador.jpa.entidades;

// Importa as anotações JPA e a classe UUID
import jakarta.persistence.*;
import java.util.UUID;

// Indica que esta classe é uma entidade JPA
@Entity
// Define o nome da tabela no banco de dados como "PRODUTO"
@Table(name = "PRODUTO")

public class Produto {
    // Define o campo "id" como chave primária, gerada automaticamente, do tipo UUID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Mapeia o campo "name" para a coluna "name", não permitindo valores nulos
    @Column(name = "name", nullable = false)
    public String name;

    // Mapeia o campo "valor" para a coluna "valor", não permitindo valores nulos
    @Column(name = "valor", nullable = false)
    public double valor;

    // Mapeia o campo "quantidade" para a coluna "quantidade", não permitindo valores nulos
    @Column(name = "quantidade", nullable = false)
    public int quantidade;


    // Getter para o campo "id"
    public UUID getId() {
        return id;
    }

    // Setter para o campo "id"
    public void setId(UUID id) {
        this.id = id;
    }

    // Getter para o campo "name"
    public String getName() {
        return name;
    }

    // Setter para o campo "name"
    public void setName(String name) {
        this.name = name;
    }

    // Getter para o campo "valor"
    public double getValor() {
        return valor;
    }

    // Setter para o campo "valor"
    public void setValor(double valor) {
        this.valor = valor;
    }

    // Getter para o campo "quantidade"
    public int getQuantidade() {
        return quantidade;
    }

    // Setter para o campo "quantidade"
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}