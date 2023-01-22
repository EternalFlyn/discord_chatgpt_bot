import bot.Bot
import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener

fun main() {
    val ready = EventListener { event ->
        if(event is ReadyEvent) {
            println("I'm ready!")
        }
    }
    val dotenv = dotenv {
        directory = "assets"
        filename = "env"
    }
    val jda = JDABuilder.createDefault(dotenv["DISCORD_TOKEN"])
        .addEventListeners(ready)
        .addEventListeners(Bot())
        .build()
    jda.awaitReady()
}
