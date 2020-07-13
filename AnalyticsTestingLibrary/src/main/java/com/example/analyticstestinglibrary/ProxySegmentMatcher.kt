package com.example.analyticstestinglibrary

import com.example.analyticstestinglibrary.dataobjects.segment.Segment
import com.example.analyticstestinglibrary.dataobjects.proxy.ProxyLog
import com.squareup.moshi.Moshi

class ProxySegmentMatcher(val moshi: Moshi, val expectedEvents: List<ExpectedSegmentEvent>): ProxyMatcher {

    var allEvents : MutableList<ExpectedSegmentEvent> = ArrayList()

    override fun match(proxyLog: ProxyLog): ProxyLogMatchResult {
        if (proxyLog.host == "api.segment.io" && proxyLog.status == "COMPLETE" && proxyLog.request != null) {
            if (proxyLog.request.body?.text != null) {
                val segment = moshi.adapter(Segment::class.java)
                    .fromJson(proxyLog.request.body!!.text!!)
                if (segment?.batch != null) {
                    for (segmentCall in segment?.batch) {
                        allEvents.add(ExpectedSegmentEvent(segmentCall.event ?: segmentCall.name, segmentCall.properties?.pageType))
                        if (allEvents.containsAll(expectedEvents)) {
                            return ProxyLogMatchResult(isMatch = true, finishedMatching = true)
                        }
                    }
                }
                return ProxyLogMatchResult(isMatch = true, finishedMatching = false)
            }
        }
        return ProxyLogMatchResult(isMatch = false, finishedMatching = false)
    }

}