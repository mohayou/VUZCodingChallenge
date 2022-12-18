package com.inmobiles.vuzcodingchallenge.dependencyinjection

import androidx.databinding.DataBindingComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@BindingScope
@InstallIn(CustomBindingComponent::class)
interface CustomBindingEntryPoint: DataBindingComponent