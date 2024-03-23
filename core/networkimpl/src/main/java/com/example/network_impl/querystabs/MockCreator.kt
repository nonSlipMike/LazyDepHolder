package com.example.network_impl.querystabs

import android.content.Context
import okhttp3.Request

class MockCreator(
    private val context: Context,
    val request: Request
) {

    /**
     * Возвращает содержимое файла из `assets`
     */
    @MockerMarker
    fun asset(assetPath: String): String = context.assets
        .open(assetPath)
        .bufferedReader()
        .readText()
}
