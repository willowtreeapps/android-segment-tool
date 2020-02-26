package com.example.analyticstestinglibrary.dataobjects.segment

data class BatchElement(
    val category: String? = null,
    val type: String? = null,
    val messageID: String? = null,
    val timestamp: String? = null,
    val context: Context? = null,
    val anonymousID: String? = null,
    val event: String? = null,
    val properties: Properties? = null,
    val traits: Traits? = null,
    val name: String? = null
)