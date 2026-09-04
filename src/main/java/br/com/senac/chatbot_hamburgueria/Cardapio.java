package br.com.senac.chatbot_hamburgueria;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class Cardapio {

    public static final BigDecimal TAXA_ENTREGA = new BigDecimal("8.00");

    private static final List<ItemCardapio> ITENS = List.of(
            new ItemCardapio("BRG01", "X-Salada",
                    "Pão brioche, hambúrguer 160g, queijo, alface, tomate e maionese da casa",
                    new BigDecimal("24.90"), "Hambúrguer", false),

            new ItemCardapio("BRG02", "X-Bacon",
                    "Pão brioche, hambúrguer 160g, queijo cheddar e bacon crocante",
                    new BigDecimal("28.90"), "Hambúrguer", false),

            new ItemCardapio("BRG03", "X-Tudo do Zé",
                    "Dois hambúrgueres 160g, ovo, bacon, queijo, presunto, alface e tomate",
                    new BigDecimal("36.50"), "Hambúrguer", false),

            new ItemCardapio("BRG04", "Veggie do Zé",
                    "Pão australiano, burger de grão-de-bico, queijo, rúcula e maionese vegana",
                    new BigDecimal("26.90"), "Hambúrguer", true),

            new ItemCardapio("ACP01", "Batata Frita",
                    "Porção de 300g com cheddar e bacon",
                    new BigDecimal("22.00"), "Acompanhamento", false),

            new ItemCardapio("ACP02", "Onion Rings",
                    "Porção de 8 anéis de cebola empanados",
                    new BigDecimal("19.00"), "Acompanhamento", true),

            new ItemCardapio("BEB01", "Refrigerante Lata",
                    "350ml - Coca-Cola, Guaraná ou Sprite",
                    new BigDecimal("7.00"), "Bebida", true),

            new ItemCardapio("BEB02", "Suco Natural",
                    "500ml - laranja, limão ou maracujá",
                    new BigDecimal("12.00"), "Bebida", true)
    );

    public List<ItemCardapio> listarTodos() {
        return ITENS;
    }

    public Optional<ItemCardapio> buscarPorNome(String nome) {
        if (nome == null || nome.isBlank()) {
            return Optional.empty();
        }

        return ITENS.stream()
                .filter( item -> item.nome().contains(nome) || nome.contains(item.nome()))
                .findFirst();
    }

    public Set<BigDecimal> precosValidos() {
        Set<BigDecimal> set = new HashSet<>();
        ITENS.forEach(item -> set.add(item.preco()));
        set.add(TAXA_ENTREGA);
        return set;
    }
}
