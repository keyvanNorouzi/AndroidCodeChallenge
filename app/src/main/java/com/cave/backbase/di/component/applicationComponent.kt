package com.cave.backbase.di.component

import com.cave.backbase.di.modules.applicationModule
import org.koin.core.module.Module

val applicationComponent: List<Module> = listOf(
    applicationModule
)
