package com.gerenciador.jpa.controllers;

import com.gerenciador.jpa.dtos.ProdutoRecordDto;
import com.gerenciador.jpa.entidades.Produto;
import com.gerenciador.jpa.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/gerenciador/usuarios")
public class ProdutoController {

    private final UsuarioService usuarioService;

    public ProdutoController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos os produtos de um usuário
    @GetMapping("/{usuarioId}/produtos")
    public ResponseEntity<Set<Produto>> getAllProdutosByUsuario(@PathVariable int usuarioId) {
        Set<Produto> produtos = usuarioService.listarProdutosDoUsuario(usuarioId);
        if (produtos == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    // Cadastrar um novo produto para um usuário específico
    @PostMapping("/{usuarioId}/produtos")
    public ResponseEntity<Produto> saveProduto(@PathVariable int usuarioId, @RequestBody ProdutoRecordDto produtoRecordDto) {
        Produto produto = new Produto();
        produto.setName(produtoRecordDto.name());
        produto.setValor(produtoRecordDto.valor());
        produto.setQuantidade(produtoRecordDto.quantidade());
        Produto saved = usuarioService.cadastrarProduto(usuarioId, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Alterar um produto existente de um usuário específico
    @PutMapping("/{usuarioId}/produtos/{produtoId}")
    public ResponseEntity<Produto> updateProduto(@PathVariable int usuarioId, @PathVariable UUID produtoId, @RequestBody ProdutoRecordDto produtoRecordDto) {
        Produto produto = new Produto();
        produto.setId(produtoId);
        produto.setName(produtoRecordDto.name());
        produto.setValor(produtoRecordDto.valor());
        produto.setQuantidade(produtoRecordDto.quantidade());
        Produto updated = usuarioService.alterarProduto(usuarioId, produto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // Consultar um produto de um usuário específico
    @GetMapping("/{usuarioId}/produtos/{produtoId}")
    public ResponseEntity<Produto> getProduto(@PathVariable int usuarioId, @PathVariable UUID produtoId) {
        return usuarioService.consultarProduto(produtoId)
                .filter(produto -> produto.getUsuario().getId() == usuarioId)
                .map(produto -> ResponseEntity.status(HttpStatus.OK).body(produto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Deletar um produto de um usuário específico
    @DeleteMapping("/{usuarioId}/produtos/{produtoId}")
    public ResponseEntity<String> deleteProduto(@PathVariable int usuarioId, @PathVariable UUID produtoId) {
        usuarioService.deletarProduto(usuarioId, produtoId);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }
}