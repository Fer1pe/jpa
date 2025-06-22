package com.gerenciador.jpa.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nome", nullable = false)
    private String nome;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


//    // Métodos de administração de produtos
//    public void cadastrarProduto(Produto produto) {
//        // Lógica para cadastrar produto
//    }
//
//    public void alterarProduto(Produto produto) {
//        // Lógica para alterar produto
//    }
//
//    public Produto consultarProduto(int produtoId) {
//        // Lógica para consultar produto
//        return null; // Retornar produto consultado
//    }
//
//    public void deletarProduto(int produtoId) {
//        // Lógica para deletar produto
//    }
}


