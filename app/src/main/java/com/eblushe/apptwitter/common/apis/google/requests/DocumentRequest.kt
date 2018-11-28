package com.eblushe.apptwitter.common.apis.google.requests

import com.google.gson.annotations.SerializedName

class DocumentRequest(
    @SerializedName("document")
    var documentContent: DocumentContent
)

class DocumentContent(
    var content: String? = "",
    var type: String = "PLAIN_TEXT"
)