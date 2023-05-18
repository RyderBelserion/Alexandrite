package com.ryderbelserion.alexandrite.api.storage

import com.ryderbelserion.alexandrite.api.storage.enums.StorageType
import java.io.File
import java.nio.file.Path

abstract class FileExtension(val name: String, @JvmField val path: Path, @JvmField val type: StorageType) {
    val file: File get() = File(path.toFile(), name)
}