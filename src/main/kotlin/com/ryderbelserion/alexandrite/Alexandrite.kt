package com.ryderbelserion.alexandrite

import com.ryderbelserion.alexandrite.api.Application
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.requests.GatewayIntent
import java.io.File

class Alexandrite(token: String) : Application(
    token,
    File("./bot"),
    listOf(
        GatewayIntent.GUILD_PRESENCES,
        GatewayIntent.SCHEDULED_EVENTS,
        GatewayIntent.GUILD_VOICE_STATES,
        GatewayIntent.GUILD_EMOJIS_AND_STICKERS
    )
) {

    override fun onReady() {

    }

    override fun onStart() {

    }

    override fun onGuildReady(guild: Guild) {

    }
}