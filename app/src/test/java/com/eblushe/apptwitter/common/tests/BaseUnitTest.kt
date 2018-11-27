package com.eblushe.apptwitter.common.tests

import android.content.Context
import android.content.SharedPreferences
import com.eblushe.apptwitter.application.CONFIG_API_URL
import com.eblushe.apptwitter.common.databases.AppDatabase
import com.eblushe.apptwitter.common.databases.dao.TweetDAO
import com.eblushe.apptwitter.common.databases.dao.UserDAO
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

    @Mock
    lateinit var tweetDAO: TweetDAO

    @Mock
    lateinit var userDAO: UserDAO

    @Before
    fun beforeTest() {
        MockitoAnnotations.initMocks(this)

        BDDMockito.given(appDatabase.tweetDao()).willReturn(tweetDAO)
        BDDMockito.given(appDatabase.userDao()).willReturn(userDAO)
        BDDMockito.given(preferences.edit()).willReturn(editor)

        StorageProvider.init(context, preferences)
        StorageProvider.database = appDatabase

        ApiProvider.initTwitterClient(CONFIG_API_URL)

        StandAloneContext.startKoin(diModule)
        StandAloneContext.loadKoinModules(
            listOf(
                mockModule,
                module {
                    single(override = true) { appDatabase }
                })
        )
    }

    @After
    fun afterTest() {
        StandAloneContext.stopKoin()
    }
}