package com.inmobiles.vuzcodingchallenge.dependencyinjection

import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@BindingScope
@DefineComponent(parent = SingletonComponent::class)
interface CustomBindingComponent