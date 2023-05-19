package com.ryderbelserion.alexandrite.api

import com.ryderbelserion.alexandrite.api.listeners.Listeners
import com.ryderbelserion.alexandrite.api.schedules.Scheduler
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.events.guild.GuildReadyEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.utils.cache.CacheFlag
import java.io.File

abstract class Application(
    private val token: String,
    file: File = File("./"),
    private val gateways: List<GatewayIntent> = emptyList(),
    private val cacheFlags: List<CacheFlag> = emptyList(),
    private val extra: Application.() -> Unit = {}
): ModuleApplication {

    val jda: JDA = get()

    init {
        if (!file.exists()) file.mkdir()

        startApplication()

        Scheduler.start()
    }

    fun Application.listeners(configuration: Listeners.() -> Unit): Listeners {
        return Listeners(jda).apply(configuration)
    }

    private fun get(): JDA {
        return JDABuilder.createDefault(token, gateways).enableCache(cacheFlags).addEventListeners(Listener(this, extra)).build()
    }

    private fun startApplication() {
        onStart()
    }

    private class Listener(private val app: Application, private val module: Application.() -> Unit): ListenerAdapter() {

        override fun onReady(event: ReadyEvent) {
            module(app)

            app.onReady()
        }

        override fun onGuildReady(event: GuildReadyEvent) {
            app.onGuildReady(event.guild)
        }
    }
}

interface ModuleApplication {

    fun onStart()

    fun onReady()

    fun onGuildReady(guild: Guild)

}