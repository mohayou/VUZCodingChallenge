package com.inmobiles.vuzcodingchallenge.dependencyinjection

import dagger.hilt.DefineComponent

@DefineComponent.Builder
interface BindingComponentBuilder {
    fun build(): CustomBindingComponent
}