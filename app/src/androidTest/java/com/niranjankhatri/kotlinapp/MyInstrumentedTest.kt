package com.niranjankhatri.kotlinapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import com.niranjankhatri.kotlinapp.di.dagger2.ClassA
import com.niranjankhatri.kotlinapp.di.dagger2.MyModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MyInstrumentedTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var myObject: ClassA

    @Before
    fun init(){
        hiltRule.inject()
    }

    @Test
    fun writeYourTestHere(){
        // write your test ..
    }
}


// Test dependencies

@Module
@TestInstallIn(
    components = [
        SingletonComponent::class
    ],
    replaces = [MyModule::class]
)
class MyTestModule{
    @Provides
    fun provideMyObject() = MyTestObject()
}

class MyTestObject


// test runner for hilt

class HiltTestRunner : AndroidJUnitRunner(){
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}
