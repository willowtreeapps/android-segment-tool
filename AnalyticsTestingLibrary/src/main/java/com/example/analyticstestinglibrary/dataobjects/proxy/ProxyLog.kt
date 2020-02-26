package com.example.analyticstestinglibrary.dataobjects.proxy

import com.example.analyticstestinglibrary.dataobjects.ProxyResponse

data class ProxyLog(
    val status: String? = null,
    val method: String? = null,
    val protocolVersion: String? = null,
    val scheme: String? = null,
    val host: String? = null,
    val actualPort: String? = null,
    val path: String? = null,
    val query: String? = null,
    val request: ProxyRequest? = null,
    val response: ProxyResponse? = null
) {

}