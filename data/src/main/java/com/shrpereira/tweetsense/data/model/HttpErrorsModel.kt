package com.shrpereira.tweetsense.data.model

import com.google.gson.annotations.SerializedName

data class HttpErrorsModel(
	@SerializedName("errors") val errors: List<ErrorModel>
)

data class ErrorModel(
	@SerializedName("code") val code: Int,
	@SerializedName("message") val message: String
)