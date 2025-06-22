package com.gerenciador.jpa.controllers;

// Importa a entidade Usuario
import com.gerenciador.jpa.entidades.Usuario;
// Importa o serviço UsuarioService
import com.gerenciador.jpa.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para operações relacionadas a usuários.
 */
@RestController // Indica que esta classe é um controlador REST
@RequestMapping("/gerenciador/usuarios") // Define o caminho base para as rotas deste controlador
public class UsuarioController {

    private final UsuarioService usuarioService; // Injeta o serviço de usuário

    // Construtor para injeção de dependência do serviço
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Listar todos os usuários
    @GetMapping // Mapeia requisições GET para este método
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        // Como você não tem método específico para listar no service, usamos o repository direto
        // (adicione em UsuarioService se quiser centralizar lógica)
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioService.usuarioRepository.findAll()); // Retorna todos os usuários com status 200
    }

    // Cadastrar novo usuário
    @PostMapping // Mapeia requisições POST para este método
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        // Descomente o método cadastrarUsuario no UsuarioService para usar aqui!
        Usuario saved = usuarioService.usuarioRepository.save(usuario); // Salva o usuário recebido no corpo da requisição
        return ResponseEntity.status(HttpStatus.CREATED).body(saved); // Retorna o usuário salvo com status 201
    }

    // Consultar usuário por ID
    @GetMapping("/{id}") // Mapeia requisições GET com parâmetro de caminho "id"
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        Optional<Usuario> usuarioOpt = usuarioService.usuarioRepository.findById(id); // Busca usuário pelo ID
        return usuarioOpt.map(usuario -> ResponseEntity.status(HttpStatus.OK).body(usuario))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 200 se encontrado, 404 se não
    }

    // Deletar usuário por ID
    @DeleteMapping("/{id}") // Mapeia requisições DELETE com parâmetro de caminho "id"
    public ResponseEntity<String> deleteUsuario(@PathVariable int id) {
        // Descomente o método deletarUsuario no UsuarioService para usar aqui!
        usuarioService.usuarioRepository.deleteById(id); // Deleta o usuário pelo ID
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso."); // Retorna mensagem de sucesso
    }
}

