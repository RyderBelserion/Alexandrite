package com.ryderbelserion.alexandrite.api.storage.types.file

interface FileLoader {
    fun load()

    fun save()

    val implName: String?
}