package com.inmobiles.vuzcodingchallenge

import android.app.Application
import androidx.databinding.DataBindingUtil
//import com.inmobiles.vuzcodingchallenge.dependencyinjection.BindingComponent
import com.inmobiles.vuzcodingchallenge.dependencyinjection.BindingComponentBuilder
import com.inmobiles.vuzcodingchallenge.dependencyinjection.CustomBindingEntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import javax.inject.Provider

@HiltAndroidApp
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
    }

    init {
        instance = this
    }

    @Inject
    lateinit var bindingComponentProvider: Provider<BindingComponentBuilder>

    override fun onCreate() {
        super.onCreate()
//        DataBindingUtil.setDefaultComponent(bindingComponentProvider.get().build())
        DataBindingUtil.setDefaultComponent(
            EntryPoints.get(
                bindingComponentProvider.get().build(),
                CustomBindingEntryPoint::class.java
            )
        )
    }

}