package com.eblushe.apptwitter.common.tests

import android.content.Context
import android.content.SharedPreferences
import com.eblushe.apptwitter.application.CONFIG_API_URL
import com.eblushe.apptwitter.common.databases.AppDatabase
import com.eblushe.apptwitter.common.di.diModule
import com.eblushe.apptwitter.common.di.mockModule
import com.eblushe.apptwitter.common.providers.ApiProvider
import com.eblushe.apptwitter.common.providers.StorageProvider
import org.junit.After
import org.junit.Before
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.MockitoAnnotations

open class BaseUnitTest : KoinTest {

    @Mock
    lateinit var context: Context

    @Mock
    lateinit var preferences: SharedPreferences

    @Mock
    lateinit var editor: SharedPreferences.Editor

    @Mock
    lateinit var appDatabase : AppDatabase

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)
        StandAloneContext.startKoin(diModule)
        StandAloneContext.loadKoinModules(
            listOf(
                mockModule,
                module {
                    single(override = true) { appDatabase }
                })
        )

        ApiProvider.initTwitterClient(CONFIG_API_URL)
        StorageProvider.init(context, preferences)
        BDDMockito.given(preferences.edit()).willReturn(editor)
    }

    @After
    fun afterTest() {
        StandAloneContext.stopKoin()
    }
}