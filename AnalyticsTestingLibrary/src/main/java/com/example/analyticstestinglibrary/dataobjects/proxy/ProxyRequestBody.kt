package com.example.analyticstestinglibrary.dataobjects.proxy

data class ProxyRequestBody(
    var header: ProxyRequestHeader? = null,
    var body: ProxyRequestBody? = null,
    var text: String? = null
)