package com.ifood.challenge.base.common.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific should extend [BusinessException] class.
 */
sealed class AppException(message: String = "") : RuntimeException(message)

/**
 * Object used to identify a generic http request exception
 */
object HttpError : AppException("Server http response code with error")

/**
 * Object used to identify a network without internet connection
 */
object NetworkError : AppException()

/**
 * Unknown error
 * */
object UnknownException : AppException("Need check Logcat to see more information")

/**
 * Extend this class for feature specific failures.
 *
 * */
abstract class BusinessException : AppException()

/**
 * Exception thrown when an essential parameter is missing in the
 * backend/network response.
 *
 */
class EssentialParamMissingException(
    missingParam: String,
    rawObject: Any
) : AppException("The params: $missingParam are missing in received object: $rawObject")
