package com.example.analyticstestinglibrary

import com.example.analyticstestinglibrary.dataobjects.proxy.ProxyLog

data class ProxyLogMatchResult(val isMatch: Boolean, val finishedMatching: Boolean)

interface ProxyMatcher {
    fun match(proxyLog : ProxyLog) : ProxyLogMatchResult
}