package com.gerenciador.jpa.dtos;

// Importa a classe UUID, caso precise de identificadores únicos

import java.util.UUID;

/**
 * ProdutoRecordDto é um record que representa os dados de um produto.
 *
 * @param name Nome do produto
 * @param valor Valor do produto
 * @param quantidade Quantidade disponível do produto
 * @param usuarioId ID do usuário associado ao produto (pode ser nulo)
 */
public record ProdutoRecordDto(
        String name,        // Nome do produto
        double valor,       // Valor do produto
        int quantidade,     // Quantidade disponível
        Integer usuarioId   // ID do usuário associado
) {

}



