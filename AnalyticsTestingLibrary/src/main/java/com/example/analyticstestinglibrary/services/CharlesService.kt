package com.example.analyticstestinglibrary.services

import okhttp3.OkHttpClient
import okhttp3.Request

class CharlesService: ProxyService {
    val startURL = "http://control.charles/recording/start"
    val stopURL = "http://control.charles/recording/stop"
    val clearURL = "http://control.charles/session/clear"
    val exportURL = "http://control.charles/session/export-json"

    val client = OkHttpClient()

    private fun makeRequest(urlString: String): String? {
        val request = Request.Builder()
            .url(urlString)
            .build()
        val response = client.newCall(request).execute()
        return response.body?.string()
    }

    override fun startRecording() {
        makeRequest(startURL)
    }

    override fun stopRecording() {
        makeRequest(stopURL)
    }

    override fun clearData() {
        makeRequest(clearURL)
    }

    override fun exportData(): String? {
        return makeRequest(exportURL)
    }



}