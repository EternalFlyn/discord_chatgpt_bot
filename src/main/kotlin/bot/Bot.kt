package bot

import com.theokanning.openai.OpenAiService
import com.theokanning.openai.completion.CompletionRequest
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class Bot: ListenerAdapter() {

    private val dotenv = dotenv {
        directory = "assets"
        filename = "env"
    }
    private val service by lazy { OpenAiService(dotenv["OPENAI_TOKEN"]) }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        val message = event.message
        val content = message.contentRaw
        val channel = event.channel
        println("message receive")
        if (content.startsWith("!chat") && !message.author.isBot) {
            // Use OpenAI API to get response
            val request = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(content.removePrefix("!chat"))
                .maxTokens(2048)
                .temperature(0.9)
                .topP(1.0)
                .frequencyPenalty(0.1)
                .presencePenalty(0.6)
                .build()
            service.createCompletion(request).choices.forEach { responseText ->
                // Send response to Discord
                channel.sendMessage(responseText.text).queue()
            }
        }
    }

}