package com.meu.myrepositories.domain.di

import com.meu.myrepositories.domain.ListUserRepositoriesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    fun useCaseModule():Module{
        return module {
            factory {
                ListUserRepositoriesUseCase(get())
            }
        }
    }
}