package br.com.senac.chatbot_hamburgueria;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ChatConfiguration {

    @Bean
    ChatClient chatClient(
            ChatClient.Builder builder,
            @Value("classpath:/prompts/system-prompt-hamburgueria.st") Resource systemPrompt, CardapioTools cardapioTools, ChatMemory chatMemory
            ){

        return builder
                .defaultSystem(systemPrompt)
                .defaultTools(cardapioTools)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
    @Bean
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(new InMemoryChatMemoryRepository())
                .maxMessages(20)
                .build();
    }
}
