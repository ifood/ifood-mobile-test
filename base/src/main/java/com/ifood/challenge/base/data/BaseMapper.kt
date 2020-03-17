package com.ifood.challenge.base.data

import com.ifood.challenge.base.common.exception.EssentialParamMissingException
import io.reactivex.functions.Function

/**
 * Base mapper to all Mappers extend
 *
 * @param Raw The result from server
 * @param Model The object to create from Raw
 */
abstract class BaseMapper<Raw, Model> : Function<Raw, Model> {

    private var missingParams = mutableListOf<String>()

    abstract val trackException: (Throwable) -> Unit

    fun addMissingParam(param: String) {
        if (missingParams.contains(param)) return
        missingParams.add(param)
    }

    private fun getMissingParams() =
        missingParams.joinToString(separator = ", ", prefix = "[ ", postfix = " ]")

    private fun isMissingParams() = missingParams.isNotEmpty()

    private fun resetMissingParams() = missingParams.clear()

    fun throwException(raw: Any) {
        val essentialParamException = EssentialParamMissingException(getMissingParams(), raw)
        trackException(essentialParamException)
        resetMissingParams()
        throw essentialParamException
    }

    @Throws(EssentialParamMissingException::class)
    override fun apply(raw: Raw): Model {
        assertEssentialParams(raw)
        if (isMissingParams()) throwException(raw as Any)
        return copyValues(raw)
    }

    /**
     * Check if the required parameters were return from server
     *
     * @param raw The result from server
     * @throws EssentialParamMissingException When a required parameter is missing
     */
    abstract fun assertEssentialParams(raw: Raw)

    /**
     * Create a [Model] using the values in [Raw]
     *
     * @param raw The result from server
     * @return A model with the raw's values
     */
    abstract fun copyValues(raw: Raw): Model
}
