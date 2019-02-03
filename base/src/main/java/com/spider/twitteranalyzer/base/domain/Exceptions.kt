package com.spider.twitteranalyzer.base.domain

class PrivateAccountError : RuntimeException("This is a private account!")
class NotFoundAccountError : RuntimeException("Account not found!")
class NetworkError : RuntimeException("No internet connection!")
class TimeoutError : RuntimeException("Timeout connection!")
class UnknownError(throwable: Throwable) : RuntimeException(throwable)

