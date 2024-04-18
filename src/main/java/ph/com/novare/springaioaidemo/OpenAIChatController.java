package ph.com.novare.springaioaidemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The main controller for this demo.
 * Contains references to the OpenAiChatClient, which is used for all OpenAI-facing commands.
 *
 * @author jerieljan
 */
@RestController
public class OpenAIChatController {

    private static final Logger log = LoggerFactory.getLogger(OpenAIChatController.class);
    public static final String SYSTEM_PROMPT = "You are a helpful assistant. Be precise and concise.";
    public static final String LLM_MODEL = "gpt-3.5-turbo";
    OpenAiChatClient client;

    @Autowired
    public OpenAIChatController(OpenAiChatClient client) {
        this.client = client;

    }

    /**
     * Performs basic /chat/cpmpletion to the OpenAI API.
     *
     * @param system the system prompt to use for the query. Defaults with SYSTEM_PROMPT.
     * @param message the instruction to send.
     *
     * @return the response of the API.
     */
    @GetMapping("/ask")
    public Map chatCompletionSystem(@RequestParam(value = "system", defaultValue = SYSTEM_PROMPT) String system,
                                    @RequestParam(value = "message") String message) {
        if (Objects.equals(system, SYSTEM_PROMPT)) {
            log.info("➡️ /ask: \"{}\"", message);
        } else {
            log.info("➡️ /ask: \"{}\" -- \"{}\"", system, message);
        }

        return openAiChatCompletion(system, message);
    }

    /**
     * Performs basic /chat/cpmpletion to the OpenAI API.
     *
     * @param systemMessage the system prompt to use for the query.
     * @param message the instruction to send.
     *
     * @return the response of the API.
     */
    private Map<String, ChatResponse> openAiChatCompletion(String systemMessage, String message) {
        Prompt prompt = new Prompt(
                List.of(new SystemMessage(systemMessage),
                        new UserMessage(message)), OpenAiChatOptions.builder()
                .withModel(LLM_MODEL)
                .withMaxTokens(200).build());

        return Map.of("generation", client.call(prompt));
    }


}
