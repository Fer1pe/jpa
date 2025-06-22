package com.gerenciador.jpa.controllers;

import com.gerenciador.jpa.entidades.Usuario;
import com.gerenciador.jpa.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para operações relacionadas à entidade Usuario.
 */
@RestController
@RequestMapping("/gerenciador/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    /**
     * Construtor que injeta o serviço de usuário.
     * @param usuarioService serviço de usuário
     */
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Lista todos os usuários cadastrados.
     * @return lista de usuários
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        // Boa prática: o controller nunca deve acessar o repository diretamente.
        // Use sempre métodos do service.
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.listarTodosUsuarios());
    }

    /**
     * Cadastra um novo usuário.
     * @param usuario usuário a ser salvo
     * @return usuário salvo
     */
    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        Usuario saved = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Consulta um usuário pelo ID.
     * @param id identificador do usuário
     * @return usuário encontrado ou 404 se não existir
     */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioService.consultarUsuario(id);
        return usuarioOpt.map(usuario -> ResponseEntity.status(HttpStatus.OK).body(usuario))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Deleta um usuário pelo ID.
     * @param id identificador do usuário
     * @return mensagem de sucesso
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable int id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }
}