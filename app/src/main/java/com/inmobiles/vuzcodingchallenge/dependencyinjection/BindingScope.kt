package com.inmobiles.vuzcodingchallenge.dependencyinjection

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.BINARY

@Scope
@Retention(BINARY)
annotation class BindingScope