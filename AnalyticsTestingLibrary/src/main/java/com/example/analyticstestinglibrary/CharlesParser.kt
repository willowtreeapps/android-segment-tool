package com.example.analyticstestinglibrary

import com.example.analyticstestinglibrary.dataobjects.Segment.BatchElement
import com.example.analyticstestinglibrary.dataobjects.Segment.Segment
import com.example.analyticstestinglibrary.dataobjects.proxy.ProxyLog
import com.example.analyticstestinglibrary.services.CharlesService
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class CharlesParser(private val charlesService: CharlesService, private val waitIterations: Int, private val waitMillis: Long) {
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    private fun getCharlesLogObjects(charlesExportString: String?): List<ProxyLog>? {
        val type = Types.newParameterizedType(List::class.java, ProxyLog::class.java)
        val charlesLogs = moshi.adapter<List<ProxyLog>>(type)
        return charlesLogs.fromJson(charlesExportString)
    }

    fun getSegmentEvents(expectedEvents: List<ExpectedSegmentEvent>): List<BatchElement> {
        val segmentRequestCharlesLog = getCharlesLogsByMatcher(CharlesSegmentMatcher(moshi, expectedEvents))
        return parseSegmentEvents(segmentRequestCharlesLog)
    }

    internal fun parseSegmentEvents(charlesLogs: List<ProxyLog>) : List<BatchElement> {
        val events = ArrayList<BatchElement>()
        for (charlesLog in charlesLogs) {
            val segment = moshi.adapter(Segment::class.java).fromJson(charlesLog.request!!.body!!.text!!)
            events.addAll(segment!!.batch!!)
        }
        return events
    }

    fun getCharlesLogsByMatcher(matcher: ProxyMatcher): List<ProxyLog> {
        var waitCounter = 0
        val matchedLogs = ArrayList<ProxyLog>()
        var logCounter = 0
        while (waitCounter < waitIterations) {
            Thread.sleep(waitMillis)
            val charlesLogs = getCharlesLogObjects(charlesService.exportData())
            for (i in logCounter until charlesLogs!!.size) {
                logCounter++
                val charlesLog = charlesLogs[i]
                val filterResult = matcher.match(charlesLog)
                if (filterResult.isMatch) {
                    matchedLogs.add(charlesLog)
                }
                if (filterResult.finishedMatching) {
                    return matchedLogs
                }
            }
            waitCounter++
        }
        throw Exception() // CharlesMatchTimeoutException()
    }
}