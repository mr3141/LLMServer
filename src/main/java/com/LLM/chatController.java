package com.LLM;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class chatController {
    private final ChatClient chatClient;
    // Builder->autoconfigured model in application Properties
    public chatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }
    //call() method sends a request to the AI model, and the content() method returns the AI model’s response as a String
    //stream()
    @GetMapping("/llm")
    String result(@RequestParam String userInput){
        return this.chatClient.prompt()
                        .user(userInput)
                        .call()
                        .content();
    }
    @GetMapping("/stream")
    public Flux<String> chatWithStream(@RequestParam String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }
}
