package com.ryderbelserion.alexandrite

import com.ryderbelserion.alexandrite.api.Application
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.requests.GatewayIntent
import java.io.File

class Alexandrite(token: String) : Application(
    token,
    File("./bot"),
    listOf(
        GatewayIntent.GUILD_PRESENCES
    )
) {

    override fun onReady() {

    }

    override fun onStart() {

    }

    override fun onGuildReady(guild: Guild) {

    }
}