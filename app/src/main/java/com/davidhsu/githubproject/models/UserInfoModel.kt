package com.davidhsu.githubproject.models

import android.content.SharedPreferences
import com.davidhsu.githubproject.api.AppApi
import com.davidhsu.githubproject.api.responseData.InfoResponse
import com.davidhsu.githubproject.api.responseData.RepoListResponse
import com.davidhsu.githubproject.appConst.PrefsConst
import com.davidhsu.githubproject.extension.get
import com.davidhsu.githubproject.extension.set
import io.reactivex.Single

interface UserInfoModel {
    fun getGithubID(): String
    fun setGithubID(id: String)
    fun getUserInfo(githubID: String): Single<InfoResponse>
    fun getUserRepoList(githubID: String): Single<List<RepoListResponse>>
    fun setSelectRepo(itemName: String)
}

class UserInfoModelImpl(private val appApi: AppApi, private val appPre: SharedPreferences): UserInfoModel {
    override fun getGithubID(): String = appPre[PrefsConst.UserAccountData.GITHUB_ID] ?: ""

    override fun setGithubID(id: String) {
        appPre[PrefsConst.UserAccountData.GITHUB_ID] = id
    }

    override fun getUserInfo(githubID: String): Single<InfoResponse> = appApi.getUserInfo(githubID)

    override fun getUserRepoList(githubID: String): Single<List<RepoListResponse>> = appApi.getUserRepoList(githubID)

    override fun setSelectRepo(itemName: String) {
        appPre[PrefsConst.RepoData.SELECT_REPO] = itemName
    }
}