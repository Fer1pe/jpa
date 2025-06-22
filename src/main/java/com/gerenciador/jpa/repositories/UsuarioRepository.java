package com.gerenciador.jpa.repositories;

import com.gerenciador.jpa.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface que permite operações CRUD no banco de dados para a entidade Usuario
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Métodos CRUD já estão disponíveis:
    // - save(Usuario usuario)
    // - findById(Integer id)
    // - findAll()
    // - deleteById(Integer id)
    // - etc.
}