package com.niranjankhatri.kotlinapp.di.koin

import android.app.Application
import com.niranjankhatri.kotlinapp.MainActivity
import com.niranjankhatri.kotlinapp.di.dagger2.ClassA
import com.niranjankhatri.kotlinapp.di.dagger2.ClassB
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.*

class KoinApplication : Application() {

    val modulesForYourClassA = module {
        // used qualifiers
        single(named("my_class_a")) { ClassA() } // repositories, database, api component
    }

    val modulesForClassB = module {
        factory { ClassB(get()) } // new object every time an injection is performed.
        // for cases when object needs to live as long as activity or fragment lives.
        scope(named<MainActivity>()) {
            scoped { ClassB(get()) }
        }
    }

    val appModule = module {
        single {
            Random()
        }
        single<KoinNumberRepository> {  KoinNumberRepositoryImpl(get()) }
    }

    val mainModule = module {
        scope(named<KoinActivity>()) {
            scoped {
                KoinViewModel(get())
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KoinApplication)
            modules(
                listOf(
                    appModule,
                    mainModule
                )
            )
        }
    }
}