package com.niranjankhatri.kotlinapp.di.dagger2

import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainSubcomponent {
    fun inject(dagger2Activity: Dagger2Activity)
}