# 📚 Gerenciador de Usuários e Produtos

## 🏁 Introdução

Este projeto tem como finalidade o desenvolvimento de uma API REST para o gerenciamento de produtos, utilizando Java e Spring Data JPA. O sistema permite realizar operações de cadastro, consulta, exclusão, modificação e listagem de produtos, com armazenamento dos dados em um banco de dados.

## 🎯 Objetivo Geral

Demonstrar o funcionamento de um sistema que armazena e manipula dados de forma organizada, integrando uma aplicação com um banco de dados. Durante o desenvolvimento, são aplicados conceitos de organização de código e mapeamento de dados.

## 🗂️ Como o Sistema Funciona

O sistema permite o cadastro de usuários e produtos associados a esses usuários. As operações disponíveis incluem:

- Cadastro, consulta, atualização e exclusão de usuários
- Cadastro, consulta, atualização e exclusão de produtos vinculados a usuários
- Listagem de usuários e produtos

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Banco de Dados H2 (em memória)
- Maven
- Git/GitHub
- Postman (para testes)

## 🖥️ Requisitos do Sistema

- Java 17 ou superior
- Maven
- Git

## ⚙️ Instruções de Instalação e Execução

1. Clone o repositório:

    ```bash
    git clone [URL do repositório]
    cd gerenciador
    ```

2. Compile e execute o projeto:

    ```bash
    mvn spring-boot:run
    ```

3. A aplicação estará disponível em:

    ```
    http://localhost:8080
    ```

---

## 🔗 Endpoints Disponíveis

### 🔸 Usuário

| Método | Endpoint                              | Descrição                 |
|--------|---------------------------------------|---------------------------|
| GET    | `/gerenciador/usuarios`               | Lista todos os usuários   |
| POST   | `/gerenciador/usuarios`               | Cria novo usuário        |
| GET    | `/gerenciador/usuarios/{usuarioId}`   | Busca usuário por ID      |
| PUT    | `/gerenciador/usuarios/{usuarioId}`   | Atualiza usuário          |
| DELETE | `/gerenciador/usuarios/{usuarioId}`   | Remove usuário            |

### 🔸 Produto do Usuário

| Método | Endpoint                                                         | Descrição                       |
|--------|------------------------------------------------------------------|----------------------------------|
| GET    | `/gerenciador/usuarios/{usuarioId}/produtos`                     | Lista produtos do usuário        |
| POST   | `/gerenciador/usuarios/{usuarioId}/produtos`                     | Cria produto para o usuário      |
| GET    | `/gerenciador/usuarios/{usuarioId}/produtos/{produtoId}`         | Busca produto específico do usuário |
| PUT    | `/gerenciador/usuarios/{usuarioId}/produtos/{produtoId}`         | Atualiza produto do usuário      |
| DELETE | `/gerenciador/usuarios/{usuarioId}/produtos/{produtoId}`         | Remove produto do usuário        |

**Legendas:**
- `{usuarioId}`: ID do usuário (inteiro)
- `{produtoId}`: ID do produto (UUID)

**Exemplos:**
- `/gerenciador/usuarios/1/produtos`
- `/gerenciador/usuarios/2/produtos/1c0a7e2b-1234-4cde-9a23-abcdef123456`

---

## 💡 Exemplos de Requisições (Body JSON)

### 1. Criar Usuário

**POST** `/gerenciador/usuarios`
```json
{
  "nome": "Felipe"
}
```

### 2. Atualizar Usuário

**PUT** `/gerenciador/usuarios/{usuarioId}`
```json
{
  "nome": "Felipe Silva"
}
```

### 3. Criar Produto para Usuário

**POST** `/gerenciador/usuarios/{usuarioId}/produtos`
```json
{
  "name": "Notebook Dell",
  "valor": 3500.99,
  "quantidade": 2
}
```

### 4. Atualizar Produto do Usuário

**PUT** `/gerenciador/usuarios/{usuarioId}/produtos/{produtoId}`
```json
{
  "name": "Notebook Dell XPS",
  "valor": 4200.00,
  "quantidade": 1
}
```

> ⚠️ **Lembrete:**  
> Para POST e PUT em produtos, use os campos: `name`, `valor`, `quantidade`.  
> Para POST e PUT em usuários, use o campo: `nome`.

---

## 🗃️ Estrutura de Pastas do Projeto

```
gerenciador/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── exemplo/
│   │   │           └── gerenciador/
│   │   │               ├── GerenciadorApplication.java
│   │   │               ├── controller/
│   │   │               │   ├── ProdutoController.java
│   │   │               │   └── UsuarioController.java
│   │   │               ├── model/
│   │   │               │   ├── Produto.java
│   │   │               │   └── Usuario.java
│   │   │               ├── repository/
│   │   │               │   ├── ProdutoRepository.java
│   │   │               │   └── UsuarioRepository.java
│   │   │               └── service/
│   │   │                   ├── ProdutoService.java
│   │   │                   └── UsuarioService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql (se aplicável)
│   └── test/
│       └── java/
│           └── com/
│               └── exemplo/
│                   └── gerenciador/
│                       └── (testes unitários)
├── pom.xml
└── README.md
```

---

## 👨‍🏫 Autoria

Projeto desenvolvido por Felipe para a disciplina de Programação Orientada a Objeto, sob orientação do professor Maromo.
