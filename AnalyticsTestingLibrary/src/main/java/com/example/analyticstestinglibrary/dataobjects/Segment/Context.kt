package com.example.analyticstestinglibrary.dataobjects.Segment

import com.squareup.moshi.Json

data class Context(
    @Json(name = "traits")
    val traits: ContextTraits? = null,
    val timezone: String? = null,
    val userAgent: String? = null,
    val locale: String? = null
)