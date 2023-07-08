package com.ryderbelserion.alexandrite.listeners

import com.ryderbelserion.alexandrite.api.schedules.Scheduler
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.utils.FileUpload
import java.io.File
import java.nio.file.Files
import kotlin.time.Duration.Companion.seconds

class FileWatcherListener : ListenerAdapter() {

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

            val dir = File(".${File.separator}bot${File.separator}videos")
            if (!dir.exists()) dir.mkdir()

            val oldFile = File("$dir${File.separator}${attachment.fileName}")

            val newFile = File("$dir${File.separator}${oldFile.nameWithoutExtension}.mp4")

            val proxy = attachment.proxy.downloadToFile(oldFile).thenAccept {
                it.renameTo(newFile)

                event.message.reply("I've converted that file for you!")
                    .addFiles(FileUpload.fromData(newFile, newFile.name)).queue()
            }.exceptionally { stack ->
                stack.printStackTrace()
                null
            }

            proxy.get().let {
                Scheduler.countdown(10.seconds, task = {
                    val ifExists =
                        Files.deleteIfExists(java.nio.file.Path.of("$dir${File.separator}${oldFile.nameWithoutExtension}.mp4"))

                    if (ifExists) println("File was deleted!")
                })

                if (dir.listFiles()?.isNotEmpty() == true) {
                    dir.listFiles()?.forEach {
                        it.deleteOnExit()
                    }
                }
            }
        }
    }
}