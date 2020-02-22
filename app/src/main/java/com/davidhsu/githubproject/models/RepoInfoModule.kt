package com.davidhsu.githubproject.models

import android.content.SharedPreferences
import com.davidhsu.githubproject.api.AppApi
import com.davidhsu.githubproject.api.responseData.RepoCollaboratorsResponse
import com.davidhsu.githubproject.api.responseData.UserRepoCommit
import com.davidhsu.githubproject.appConst.PrefsConst
import com.davidhsu.githubproject.extension.get
import io.reactivex.Single

interface RepoInfoModule {
    fun getUserID(): String
    fun getSelectRepo(): String
    fun getRepoCommitCommit(githubID: String, repoName: String): Single<List<UserRepoCommit>>
    fun getRepoCollaborators(githubID: String, repoName: String): Single<List<RepoCollaboratorsResponse>>
}

class RepoInfoModuleImpl(private val appApi: AppApi, private val appPre: SharedPreferences): RepoInfoModule {
    override fun getUserID(): String = appPre[PrefsConst.UserAccountData.GITHUB_ID] ?: ""

    override fun getSelectRepo(): String = appPre[PrefsConst.RepoData.SELECT_REPO] ?: ""

    override fun getRepoCommitCommit(githubID: String, repoName: String): Single<List<UserRepoCommit>> =
        appApi.getUserRepoCommit(githubID, repoName)

    override fun getRepoCollaborators(githubID: String, repoName: String): Single<List<RepoCollaboratorsResponse>> =
        appApi.getUserRepoCollaborators(githubID, repoName)

}