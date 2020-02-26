package com.example.analyticstestinglibrary.dataobjects.Segment

import com.squareup.moshi.Json

data class Segment(
    @Json(name = "batch")
    val batch: List<BatchElement>? = null
)