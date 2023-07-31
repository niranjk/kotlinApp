package com.niranjankhatri.kotlinapp.di.dagger2

import com.niranjankhatri.kotlinapp.MyApplication
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Scope

// Consumer

// 1. Constructor Injection
class ClassA @Inject constructor()

class ClassB @Inject constructor(
    private val classA: ClassA
)

// 2. Field Injection

class FieldInjectionClass {
    @Inject
    lateinit var classA: ClassA
}


// Providers

// used for external dependencies -
// when you cannot provide constructor injection
// for eg: interfaces or abstract classes

// @DisableInstallInCheck  // using dagger 2
@Module
@InstallIn(SingletonComponent::class)
object MyModule{
    @Provides
    fun provideClassA(): ClassA = ClassA()

    @Provides
    fun provideClassB(classA: ClassA): ClassB = ClassB(classA)
}


// Connectors

// used for an interface or an abstract class


@Component(modules = [MyModule::class])
interface  MyComponent {
    fun inject( myApplication: MyApplication)
}

// Qualifiers
@Scope
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
@Scope
@MustBeDocumented
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope

@DisableInstallInCheck
@Module
object MyQualifierModule{
    @Named("classA1")
    @ActivityScope
    @Provides
    fun provideClassA1(): ClassA = ClassA()

    @Named("classA2")
    @Provides
    fun provideClassA2(): ClassA = ClassA()

}


// Scope
// keep track of the lifecycle of your components and your dependencies

// Sub Components

class ClassC
@DisableInstallInCheck
@Module
object MySubcomponentModule{
    @Provides
    fun provideClassC(): ClassC = ClassC()
}


