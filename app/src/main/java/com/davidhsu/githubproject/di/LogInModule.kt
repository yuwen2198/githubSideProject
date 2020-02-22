package com.davidhsu.githubproject.di

import com.davidhsu.githubproject.appConst.PrefsConst
import com.davidhsu.githubproject.models.LogInModel
import com.davidhsu.githubproject.models.LogInModelImpl
import com.davidhsu.githubproject.viewmodel.LogInActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val logInModule = module {
    single(named(LogInModel::class.java.simpleName)) {
        LogInModelImpl(get(named(PrefsConst.App.NAME)))
    }

    viewModel {
        LogInActivityViewModel(
            get(named(LogInModel::class.java.simpleName))
        )
    }
}