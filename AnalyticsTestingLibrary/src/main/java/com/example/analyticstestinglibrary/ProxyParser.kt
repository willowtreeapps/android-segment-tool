package com.example.analyticstestinglibrary

import com.example.analyticstestinglibrary.dataobjects.segment.BatchElement
import com.example.analyticstestinglibrary.dataobjects.segment.Segment
import com.example.analyticstestinglibrary.dataobjects.proxy.ProxyLog
import com.example.analyticstestinglibrary.services.ProxyService
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class ProxyParser(private val proxyService: ProxyService, private val waitIterations: Int, private val waitMillis: Long) {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun getSegmentEvents(expectedEvents: List<ExpectedSegmentEvent>): List<BatchElement> {
        val segmentRequestLog = getProxyLogsByMatcher(ProxySegmentMatcher(moshi, expectedEvents))
        return parseSegmentEvents(segmentRequestLog)
    }

    internal fun parseSegmentEvents(proxyLogList: List<ProxyLog>) : List<BatchElement> {
        val events = ArrayList<BatchElement>()
        for (loggedCall in proxyLogList) {
            val segment = moshi.adapter(Segment::class.java).fromJson(loggedCall.request!!.body!!.text!!)
            events.addAll(segment!!.batch!!)
        }
        return events
    }

    fun getProxyLogsByMatcher(matcher: ProxyMatcher): List<ProxyLog> {
        var waitCounter = 0
        val matchedLogs = ArrayList<ProxyLog>()
        var logCounter = 0
        while (waitCounter < waitIterations) {
            Thread.sleep(waitMillis)
            val proxyLogs = proxyService.exportData()
            for (i in logCounter until proxyLogs!!.size) {
                logCounter++
                val log = proxyLogs[i]
                val filterResult = matcher.match(log)
                if (filterResult.isMatch) {
                    matchedLogs.add(log)
                }
                if (filterResult.finishedMatching) {
                    return matchedLogs
                }
            }
            waitCounter++
        }
        throw Exception() // TODO: Implement custom exception(i.e. CharlesMatchTimeoutException())
    }
}