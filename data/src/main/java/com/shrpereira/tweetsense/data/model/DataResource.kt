package com.shrpereira.tweetsense.data.model

import android.util.Log
import com.github.leonardoxh.livedatacalladapter.Resource
import com.google.gson.Gson
import retrofit2.HttpException

private const val TAG = "DataResource"

class DataResource<T> {

	var responseData: T? = null
		private set
	var error: Throwable? = null
		private set

	internal fun success(body: T?) {
		this.responseData = body
	}

	internal fun error(error: Throwable?) {
		error?.let { checkedError ->

			if (checkedError is HttpException) {

				checkedError.response().errorBody()?.string()?.let {
					try {
						val errorModel = Gson().fromJson(it, HttpErrorsModel::class.java)
						val message = errorModel.errors.joinToString(" ") { error -> error.message }
						val exception = Exception(message)
						this.error = exception
						return
					} catch (e: Exception) {
						Log.d(TAG, "Error converting to json. ErrorBody: $it")
					}
				}
			}

			this.error = error
		}
	}

	fun <R> switchTo(updatedData: R?, error: Throwable?): DataResource<R> {
		return DataResource<R>().also {
			it.success(updatedData)
			it.error(error)
		}
	}

	companion object {

		fun <T> getFromResource(resource: Resource<T>): DataResource<T> {
			val callResponse = DataResource<T>()

			callResponse.apply {
				success(resource.resource)
				error(resource.error)
			}

			return callResponse
		}

		fun <T> newInstance(resource: T?, throwable: Throwable?): DataResource<T> {
			val callResponse = DataResource<T>()

			callResponse.apply {
				success(resource)
				error(throwable)
			}

			return callResponse
		}
	}
}