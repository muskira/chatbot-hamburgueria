package br.com.senac.chatbot_hamburgueria;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping ("/hello")
public class HelloController {

    private final ChatClient chatClient;

    public HelloController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    record ChatHelloInput(
            String id,
            String prompt

    ){}

    record ChatHelloOutput(
            String id,
            String message
    ){}
    // GetMapping - Bruno
    @PostMapping // swagger
    @Operation(summary = "Envie uma menssagem o Gemini") // swagger
    public ChatHelloOutput hello(@RequestBody ChatHelloInput input) {

        String conversaId = definirId(input.id);

        String chatResponse = chatClient.prompt()
                .user(input.prompt)
                .advisors(adv -> adv.param(ChatMemory.CONVERSATION_ID, conversaId))
                .call()
                .content();

        return new ChatHelloOutput(conversaId, chatResponse);
    }

    private static String definirId(String id){
        if (id == null || id.isBlank()){
            return UUID.randomUUID().toString();
        }else {
            return id;
        }
    }

}
