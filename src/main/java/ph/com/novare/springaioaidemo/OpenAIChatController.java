package ph.com.novare.springaioaidemo;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OpenAIChatController {

    public static final String SYSTEM_PROMPT = "You are a helpful assistant. Be precise and concise.";
    OpenAiChatClient client;

    @Autowired
    public OpenAIChatController(OpenAiChatClient client) {
        this.client = client;

    }

    /**
     * Performs basic /chat/cpmpletion to the OpenAI API.
     * @param message the instruction to send
     * @return
     */
    @GetMapping("/hello")
    public Map chatCompletion(@RequestParam(value = "message") String message) {

        Prompt prompt = new Prompt(
                List.of(new SystemMessage(SYSTEM_PROMPT),
                        new UserMessage(message)), OpenAiChatOptions.builder()
                .withModel("gpt-3.5-turbo")
                .withMaxTokens(200).build());

        return Map.of("generation", client.call(prompt));
    }


}
