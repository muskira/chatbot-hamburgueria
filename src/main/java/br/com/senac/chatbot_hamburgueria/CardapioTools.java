package br.com.senac.chatbot_hamburgueria;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CardapioTools {

    @Autowired
    private Cardapio cardapio;

    private static final Logger log = LoggerFactory.getLogger(CardapioTools.class);


    //
    @Tool(description = """
            Consulta o cardápio completo e atual da Hamburgueria do Zé.
            Retorna nome, descrição, preço e categoria de cada item.
            Use SEMPRE esta ferramenta antes de falar sobre itens ou preços.
            """)
    public List<ItemCardapio> consultarCardapio(){
        log.info("[TOOL] ConsultaCardapio");
        return cardapio.listarTodos();
    }

    @Tool(description = """
            Consulta o preço atual de um item específico do cardápio.
            Use quando o cliente perguntar o valor de u produto pelo nome.
            """)
    public String consultarPreco(
            @ToolParam(description = "Nome do item, por exemplo: X-Bacon")String nome){

        log.info("[TOOL] consultarPreco('{}')", nome);

        return cardapio.buscarPorNome(nome)
                .map(i -> "%s: R$ %s" .formatted(i.nome(), i.preco()))
                .orElse("Item não encontrado no cardápio. NÃO invente o preço:"
                        + "informe ao cliente que esse item não existe e ofereça o cardápio.");
    }
}
