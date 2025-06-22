package com.gerenciador.jpa.repositories;

import com.gerenciador.jpa.entidades.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

// Interface que estende JpaRepository para fornecer operações CRUD para Produto
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    // Métodos CRUD já são herdados de JpaRepository:
    // - save(Produto produto)
    // - findById(UUID id)
    // - findAll()
    // - deleteById(UUID id)
    // - etc.
}

