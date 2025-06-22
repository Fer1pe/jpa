// Pacote onde a classe está localizada
package com.gerenciador.jpa.entidades;

// Importações necessárias para JPA e UUID
import jakarta.persistence.*;
import java.util.UUID;

// Indica que a classe é uma entidade JPA
@Entity
// Define o nome da tabela no banco de dados
@Table(name = "PRODUTO")
public class Produto {
    // Identificador único da entidade, gerado automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    // Nome do produto, não pode ser nulo
    @Column(name = "name", nullable = false)
    public String name;

    // Valor do produto, não pode ser nulo
    @Column(name = "valor", nullable = false)
    public double valor;

    // Quantidade do produto, não pode ser nulo
    @Column(name = "quantidade", nullable = false)
    public int quantidade;

    // Relacionamento ManyToOne com a entidade Usuario (um usuário pode ter vários produtos)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Métodos getters e setters para acessar e modificar os atributos

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    // Getter e Setter para o relacionamento com Usuario
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}