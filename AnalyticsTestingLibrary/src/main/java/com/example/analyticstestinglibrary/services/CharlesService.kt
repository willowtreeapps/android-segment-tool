package com.example.analyticstestinglibrary.services

import com.example.analyticstestinglibrary.dataobjects.proxy.ProxyLog
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request

class CharlesService: ProxyService {
    val startURL = "http://control.charles/recording/start"
    val stopURL = "http://control.charles/recording/stop"
    val clearURL = "http://control.charles/session/clear"
    val exportURL = "http://control.charles/session/export-json"

    val client = OkHttpClient()

    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

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

    override fun exportData(): List<ProxyLog> {
        val type = Types.newParameterizedType(List::class.java, ProxyLog::class.java)
        val proxyLogs = moshi.adapter<List<ProxyLog>>(type)
        return proxyLogs.fromJson(makeRequest(exportURL)) ?: emptyList()
    }



}