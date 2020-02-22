package com.davidhsu.githubproject.di

import com.davidhsu.githubproject.api.AppApi
import com.davidhsu.githubproject.appConst.PrefsConst
import com.davidhsu.githubproject.models.UserInfoModel
import com.davidhsu.githubproject.models.UserInfoModelImpl
import com.davidhsu.githubproject.viewmodel.UserInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userInfoModule = module {
    single(named(UserInfoModel::class.java.simpleName)) {
        UserInfoModelImpl(
            get(named(AppApi::class.java.simpleName)),
            get(named(PrefsConst.App.NAME))
        )
    }

    viewModel {
        UserInfoViewModel(
            get(named(UserInfoModel::class.java.simpleName))
        )
    }
}