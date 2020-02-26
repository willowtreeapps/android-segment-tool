package com.example.analyticstestinglibrary.dataobjects.segment

import com.squareup.moshi.Json

data class Segment(
    @Json(name = "batch")
    val batch: List<BatchElement>? = null
)