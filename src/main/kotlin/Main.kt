import bot.Bot
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.hooks.EventListener

fun main() {
    val ready = EventListener { event ->
        if(event is ReadyEvent) {
            println("I'm ready!")
        }
    }
    val jda = JDABuilder.createDefault("MTA1ODI5MjY4OTcwNTEyMzg2MQ.G2jGXi.cL7_pkmViPuVkM70pcYqMfxYRM5GKwX5DWXc5s")
        .addEventListeners(ready)
        .addEventListeners(Bot())
        .build()
    jda.awaitReady()
}
