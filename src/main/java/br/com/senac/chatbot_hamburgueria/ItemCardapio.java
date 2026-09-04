package br.com.senac.chatbot_hamburgueria;

import java.math.BigDecimal;

public record ItemCardapio(
        String codigo,
        String nome,
        String descricao,
        BigDecimal preco,
        String categoria,
        Boolean vegetariano
) {
}
