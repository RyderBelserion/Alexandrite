package com.ryderbelserion.alexandrite.api.utils

import java.io.BufferedOutputStream
import java.io.FileOutputStream
import java.net.JarURLConnection
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.ZipFile

/**
 * Description: Extracts files from inside the .jar file.
 */
object FileUtils {

    /**
     * Extracts files from inside the .jar into an output
     *
     * @param input the directory in the .jar
     * @param output the output wherever you use this.
     * @param replace if we should replace or not.
     */
    fun extract(input: String, output: Path, replace: Boolean) {
        val directory = FileUtils::class.java.getResource(input) ?: return

        if (directory.protocol != "jar") return

        val jar: ZipFile = try {
            (directory.openConnection() as JarURLConnection).jarFile
        } catch (exception: Exception) {
            throw RuntimeException(exception)
        }

        val filePath = input.substring(1)
        val fileEntries = jar.entries()

        while (fileEntries.hasMoreElements()) {
            val entry = fileEntries.nextElement()
            val entryName = entry.name

            if (!entryName.startsWith(filePath)) continue

            val outFile = output.resolve(entryName)
            val exists = Files.exists(outFile)

            if (!replace && exists) continue

            if (entry.isDirectory) {
                if (exists) return
                try {
                    Files.createDirectories(outFile)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                continue
            }

            try {
                jar.getInputStream(entry).use { inputStream ->
                    BufferedOutputStream(FileOutputStream(outFile.toFile())).use { outputStream ->
                        val buffer = ByteArray(4096)
                        var readCount: Int

                        while (inputStream.read(buffer).also { readCount = it } > 0) {
                            outputStream.write(buffer, 0, readCount)
                        }

                        outputStream.flush()
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }
    }
}