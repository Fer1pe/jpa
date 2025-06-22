
package com.gerenciador.jpa.services;

import com.gerenciador.jpa.entidades.Produto;
import com.gerenciador.jpa.entidades.Usuario;
import com.gerenciador.jpa.repositories.ProdutoRepository;
import com.gerenciador.jpa.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ProdutoRepository produtoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
    }

    // CRUD para Usuario
    public Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario alterarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> consultarUsuario(int usuarioId) {
        return usuarioRepository.findById(usuarioId);
    }

    public void deletarUsuario(int usuarioId) {
        usuarioRepository.deleteById(usuarioId);
    }

    // CRUD de Produto feito pelo usuário

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

    public Produto alterarProduto(int usuarioId, Produto produto) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            produto.setUsuario(usuario); // Garante o relacionamento
            return produtoRepository.save(produto);
        }
        throw new IllegalArgumentException("Usuário não encontrado para alterar produto.");
    }

    public Optional<Produto> consultarProduto(UUID produtoId) {
        return produtoRepository.findById(produtoId);
    }

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
    public Set<Produto> listarProdutosDoUsuario(int usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        return usuarioOpt.map(Usuario::getProdutos).orElse(null);
    }
}