package com.meu.myrepositories.presentation.di

import com.meu.myrepositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    fun viewModelModule(): Module {
        return module {
            viewModel {
                MainViewModel(get())
            }
        }
    }
}