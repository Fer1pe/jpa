package com.gerenciador.jpa.controllers;

import com.gerenciador.jpa.dtos.ProdutoRecordDto;
import com.gerenciador.jpa.entidades.Produto;
import com.gerenciador.jpa.services.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador REST para operações relacionadas a produtos.
 */
@RestController
@RequestMapping("/gerenciador/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    /**
     * Construtor que injeta o serviço de produtos.
     * @param produtoService serviço de produtos
     */
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    /**
     * Lista todos os produtos de um usuário específico.
     * @param usuarioId ID do usuário
     * @return lista de produtos do usuário
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Produto>> getAllProdutosByUsuario(@PathVariable UUID usuarioId) {
        List<Produto> produtos = produtoService.listarProdutosPorUsuario(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    /**
     * Cadastra um novo produto para um usuário específico.
     * @param produtoRecordDto DTO com dados do produto
     * @return produto cadastrado
     */
    @PostMapping
    public ResponseEntity<Produto> saveProduto(@RequestBody ProdutoRecordDto produtoRecordDto) {
        if (produtoRecordDto.usuarioId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        UUID usuarioId = new UUID(produtoRecordDto.usuarioId(), 0L);
        Produto produto = new Produto();
        produto.setName(produtoRecordDto.name());
        produto.setValor(produtoRecordDto.valor());
        produto.setQuantidade(produtoRecordDto.quantidade());
        Produto saved = produtoService.cadastrarProduto(usuarioId, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Altera um produto existente de um usuário específico.
     * @param produtoId ID do produto
     * @param usuarioId ID do usuário
     * @param produtoRecordDto DTO com dados do produto
     * @return produto atualizado
     */
    @PutMapping("/{produtoId}/usuario/{usuarioId}")
    public ResponseEntity<Produto> updateProduto(@PathVariable UUID produtoId, @PathVariable UUID usuarioId, @RequestBody ProdutoRecordDto produtoRecordDto) {
        Produto produto = new Produto();
        produto.setId(produtoId);
        produto.setName(produtoRecordDto.name());
        produto.setValor(produtoRecordDto.valor());
        produto.setQuantidade(produtoRecordDto.quantidade());
        Produto updated = produtoService.alterarProduto(usuarioId, produto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    /**
     * Consulta um produto específico de um usuário.
     * @param produtoId ID do produto
     * @param usuarioId ID do usuário
     * @return produto encontrado ou 404 se não existir
     */
    @GetMapping("/{produtoId}/usuario/{usuarioId}")
    public ResponseEntity<Produto> getProduto(@PathVariable UUID produtoId, @PathVariable UUID usuarioId) {
        Optional<Produto> produtoOpt = produtoService.consultarProduto(usuarioId, produtoId);
        return produtoOpt.map(produto -> ResponseEntity.status(HttpStatus.OK).body(produto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Deleta um produto de um usuário específico.
     * @param produtoId ID do produto
     * @param usuarioId ID do usuário
     * @return mensagem de sucesso
     */
    @DeleteMapping("/{produtoId}/usuario/{usuarioId}")
    public ResponseEntity<String> deleteProduto(@PathVariable UUID produtoId, @PathVariable UUID usuarioId) {
        produtoService.deletarProduto(usuarioId, produtoId);
        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }
}