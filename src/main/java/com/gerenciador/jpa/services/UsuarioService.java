package com.gerenciador.jpa.services;

import com.gerenciador.jpa.entidades.Produto;
import com.gerenciador.jpa.entidades.Usuario;
import com.gerenciador.jpa.repositories.ProdutoRepository;
import com.gerenciador.jpa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Serviço responsável pelas operações relacionadas ao Usuário e seus Produtos.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    /**
     * Construtor com injeção de dependências dos repositórios.
     */
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    // CRUD para Usuario

    /**
     * Lista todos os usuários cadastrados.
     * @return lista de usuários
     */
    public List<Usuario> listarTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    /**
     * Cadastra um novo usuário.
     * @param usuario Usuário a ser cadastrado.
     * @return Usuário salvo.
     */
    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Consulta um usuário pelo ID.
     * @param usuarioId ID do usuário.
     * @return Optional contendo o usuário, se encontrado.
     */
    public Optional<Usuario> consultarUsuario(int usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    /**
     * Deleta um usuário pelo ID.
     * @param usuarioId ID do usuário a ser deletado.
     */
    @Transactional
    public void deletarUsuario(int usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    // CRUD de Produto feito pelo usuário

    /**
     * Cadastra um produto para um usuário específico.
     * @param usuarioId ID do usuário.
     * @param produto Produto a ser cadastrado.
     * @return Produto salvo.
     */
    @Transactional
    public Produto cadastrarProduto(int usuarioId, Produto produto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            produto.setUsuario(usuario); // Relacionamento bidirecional
            Produto salvo = produtoRepository.save(produto);

            // Atualiza o set de produtos do usuário (opcional, para consistência em memória)
            usuario.getProdutos().add(salvo);
            usuarioRepository.save(usuario);

            return salvo;
        }
        throw new IllegalArgumentException("Usuário não encontrado para cadastrar produto.");
    }

    /**
     * Altera um produto de um usuário.
     * @param usuarioId ID do usuário.
     * @param produto Produto com dados atualizados.
     * @return Produto salvo.
     */
    @Transactional
    public Produto alterarProduto(int usuarioId, Produto produto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            produto.setUsuario(usuario); // Garante o relacionamento
            return produtoRepository.save(produto);
        }
        throw new IllegalArgumentException("Usuário não encontrado para alterar produto.");
    }

    /**
     * Consulta um produto pelo ID.
     * @param produtoId ID do produto.
     * @return Optional contendo o produto, se encontrado.
     */
    public Optional<Produto> consultarProduto(UUID produtoId) {
        return produtoRepository.findById(produtoId);
    }

    /**
     * Deleta um produto de um usuário.
     * @param usuarioId ID do usuário.
     * @param produtoId ID do produto.
     */
    @Transactional
    public void deletarProduto(int usuarioId, UUID produtoId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Produto> produtoOpt = produtoRepository.findById(produtoId);

        if (usuarioOpt.isPresent() && produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            if (produto.getUsuario().getId() == usuarioId) {
                produtoRepository.delete(produto);
            } else {
                throw new IllegalArgumentException("Produto não pertence ao usuário informado.");
            }
        } else {
            throw new IllegalArgumentException("Usuário ou produto não encontrado.");
        }
    }

    // Listar os produtos de um usuário

    /**
     * Lista todos os produtos de um usuário.
     * @param usuarioId ID do usuário.
     * @return Conjunto de produtos do usuário, ou null se não encontrado.
     */
    public Set<Produto> listarProdutosDoUsuario(int usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        return usuarioOpt.map(Usuario::getProdutos).orElse(null);
    }
}

