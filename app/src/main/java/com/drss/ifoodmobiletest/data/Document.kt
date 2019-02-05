package com.drss.ifoodmobiletest.data

data class Document(val content: String, val gcsContentUri: String? = null, val type: String = "TEXT_PLAIN", val language: String = "en_US")