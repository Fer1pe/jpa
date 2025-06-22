package com.gerenciador.jpa.services;

// Importações das entidades e repositórios necessários
import com.gerenciador.jpa.entidades.Produto;
import com.gerenciador.jpa.entidades.Usuario;
import com.gerenciador.jpa.repositories.ProdutoRepository;
import com.gerenciador.jpa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Serviço responsável pelas operações relacionadas a Produto,
 * incluindo cadastro, alteração, consulta, listagem e exclusão,
 * sempre garantindo o vínculo com o usuário correto.
 */
@Service
public class ProdutoService {

    // Repositório para operações com Produto
    private final ProdutoRepository produtoRepository;
    // Repositório para operações com Usuario
    private final UsuarioRepository usuarioRepository;

    /**
     * Construtor com injeção de dependências dos repositórios.
     */
    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, UsuarioRepository usuarioRepository) {
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Cadastra um novo produto para um usuário específico.
     * @param usuarioId ID do usuário
     * @param produto Produto a ser cadastrado
     * @return Produto cadastrado
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public Produto cadastrarProduto(UUID usuarioId, Produto produto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(Math.toIntExact((Long) usuarioId.getMostSignificantBits()));
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado para cadastro de produto.");
        }
        produto.setUsuario(usuarioOpt.get());
        return produtoRepository.save(produto);
    }

    /**
     * Altera um produto existente, garantindo que pertence ao usuário.
     * @param usuarioId ID do usuário
     * @param produto Produto a ser alterado
     * @return Produto alterado
     * @throws IllegalArgumentException se o usuário não for encontrado
     */
    public Produto alterarProduto(UUID usuarioId, Produto produto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(Math.toIntExact((Long) usuarioId.getMostSignificantBits()));
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado para alteração de produto.");
        }
        if (produto.getUsuario() == null || produto.getUsuario().getId() != usuarioOpt.get().getId()) {
            produto.setUsuario(usuarioOpt.get());
        }
        return produtoRepository.save(produto);
    }

    /**
     * Consulta um produto pelo seu ID, garantindo que pertence ao usuário.
     * @param usuarioId ID do usuário
     * @param produtoId ID do produto
     * @return Optional contendo o produto, se encontrado e pertencente ao usuário
     */
    public Optional<Produto> consultarProduto(UUID usuarioId, UUID produtoId) {
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
        if (produtoOpt.isPresent() && produtoOpt.get().getUsuario().getId() == Math.toIntExact((Long) usuarioId.getMostSignificantBits())) {
            return produtoOpt;
        }
        return Optional.empty();
    }

    /**
     * Lista todos os produtos de um usuário específico.
     * @param usuarioId ID do usuário
     * @return Lista de produtos do usuário
     */
    public List<Produto> listarProdutosPorUsuario(UUID usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(Math.toIntExact((Long) usuarioId.getMostSignificantBits()));
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return usuario.getProdutos().stream().toList();
        }
        return List.of();
    }

    /**
     * Deleta um produto, garantindo que ele pertence ao usuário.
     * @param usuarioId ID do usuário
     * @param produtoId ID do produto
     * @throws IllegalArgumentException se o produto não for encontrado ou não pertencer ao usuário
     */
    public void deletarProduto(UUID usuarioId, UUID produtoId) {
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);
        if (produtoOpt.isPresent() && produtoOpt.get().getUsuario().getId() == Math.toIntExact((Long) usuarioId.getMostSignificantBits())) {
            produtoRepository.deleteById(produtoId);
        } else {
            throw new IllegalArgumentException("Produto não encontrado ou não pertence ao usuário informado.");
        }
    }
}

