package com.ryderbelserion.alexandrite

import com.ryderbelserion.alexandrite.api.Application
import net.dv8tion.jda.api.entities.Guild
import net.dv8tion.jda.api.requests.GatewayIntent

class Alexandrite(token: String) : Application(
    token,
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