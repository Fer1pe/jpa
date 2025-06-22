package com.gerenciador.jpa.entidades;

// Importações de bibliotecas necessárias
import com.fasterxml.jackson.annotation.JsonProperty; // Anotação para controle de serialização JSON
import jakarta.persistence.*; // Anotações JPA para mapeamento ORM

import java.util.HashSet; // Implementação de Set
import java.util.Set;     // Interface Set

// Entidade JPA que representa a tabela USUARIO no banco de dados
@Entity
@Table(name = "USUARIO")
public class Usuario {
    // Identificador único do usuário (chave primária)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nome do usuário (não pode ser nulo)
    @Column(name = "nome", nullable = false)
    private String nome;

    // Relacionamento um-para-muitos com Produto, não serializado na resposta JSON
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private Set<Produto> produtos = new HashSet<>();

    // Getter para produtos
    public Set<Produto> getProdutos() {
        return produtos;
    }

    // Setter para produtos
    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    // Getter para id
    public int getId() {
        return id;
    }

    // Setter para id
    public void setId(int id) {
        this.id = id;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Setter para nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Métodos comentados para administração de produtos (CRUD)
    // public void cadastrarProduto(Produto produto) {
    //     // Lógica para cadastrar produto
    // }
    //
    // public void alterarProduto(Produto produto) {
    //     // Lógica para alterar produto
    // }
    //
    // public Produto consultarProduto(int produtoId) {
    //     // Lógica para consultar produto
    //     return null; // Retornar produto consultado
    // }
    //
    // public void deletarProduto(int produtoId) {
    //     // Lógica para deletar produto
    // }
}