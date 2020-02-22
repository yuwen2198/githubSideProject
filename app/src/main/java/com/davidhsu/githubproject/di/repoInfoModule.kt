package com.davidhsu.githubproject.di

import com.davidhsu.githubproject.api.AppApi
import com.davidhsu.githubproject.appConst.PrefsConst
import com.davidhsu.githubproject.models.RepoInfoModule
import com.davidhsu.githubproject.models.RepoInfoModuleImpl
import com.davidhsu.githubproject.viewmodel.RepoInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repoInfoModule = module {
    single(named(RepoInfoModule::class.java.simpleName)) {
        RepoInfoModuleImpl(
            get(named(AppApi::class.java.simpleName)),
            get(named(PrefsConst.App.NAME)))
    }

    viewModel {
        RepoInfoViewModel(
            get(named(RepoInfoModule::class.java.simpleName))
        )
    }
}