package com.ifood.challenge.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseViewModelTests : BaseTests() {

    @Rule
    @JvmField
    val archComponentsRule = InstantTaskExecutorRule()

}
