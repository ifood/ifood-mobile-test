package bloder.com.domain.api_response.search

class SearchError(val reason: SEARCH_ERROR, val errorMessage: String) : Throwable()

enum class SEARCH_ERROR {
    UNKNOWN, NOT_FOUND
}