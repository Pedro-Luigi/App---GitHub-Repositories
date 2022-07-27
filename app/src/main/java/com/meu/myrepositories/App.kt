package com.meu.myrepositories

import android.app.Application
import com.meu.myrepositories.data.di.DataModule
import com.meu.myrepositories.domain.di.DomainModule
import com.meu.myrepositories.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}