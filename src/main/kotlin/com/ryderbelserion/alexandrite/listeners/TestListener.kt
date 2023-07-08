package com.ryderbelserion.alexandrite.listeners

import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.FileUpload
import java.io.File

class TestListener : ListenerAdapter() {

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (!event.message.isFromGuild) return
        if (event.channel.id != "1127117469740445777") return

        if (event.message.attachments.size == 0) return

        val filteredAttachments = event.message.attachments.filterNot {
            it.fileExtension != "mkv"
        }

        filteredAttachments.forEach { attachment ->
            if (attachment.size / 1024 > 25000) {
                event.channel.sendMessage("File is too big.").queue()
                return
            }

            val dir = File("./bot/videos")
            if (!dir.exists()) dir.mkdir()

            val oldFile = File("$dir/${attachment.fileName}")

            val newFile = File("$dir/${oldFile.nameWithoutExtension}.mp4")

            attachment.proxy.downloadToFile(oldFile).thenAccept {
                oldFile.renameTo(newFile)

                event.message.reply("I've converted that file for you!").addFiles(FileUpload.fromData(newFile, newFile.name)).queue()
            }

            dir.listFiles()?.forEach {
                it.delete()
            }
        }
    }
}