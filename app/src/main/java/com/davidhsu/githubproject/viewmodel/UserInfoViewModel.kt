package com.davidhsu.githubproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.api.responseData.InfoResponse
import com.davidhsu.githubproject.api.responseData.RepoListResponse
import com.davidhsu.githubproject.extension.addTo
import com.davidhsu.githubproject.extension.report
import com.davidhsu.githubproject.models.UserInfoModel

class UserInfoViewModel(private val model: UserInfoModel): AutoDisposeViewModel() {

    private val _userInfoData = MutableLiveData<InfoResponse>()
    val infoData: LiveData<InfoResponse> = _userInfoData
    private val _userRepoData = MutableLiveData<List<RepoListResponse>>()
    val repoData: LiveData<List<RepoListResponse>> = _userRepoData
    private val _getError = MutableLiveData<Boolean>()
    val getError: LiveData<Boolean> = _getError
    private val _clearGithubID = MutableLiveData<String>()
    val clearGithubID: LiveData<String> = _clearGithubID

    fun getUserInfo() {
        val githubID = model.getGithubID()
        model.getUserInfo(githubID).flatMap {
            _userInfoData.postValue(it)
            model.getUserRepoList(githubID)
        }.subscribe({
            LogUtil.i("list = ${it.size}")
            _userRepoData.postValue(it)
        },{
            _getError.postValue(false)
            it.report()
        }).addTo(compositeDisposable)
    }

    fun logout() {
        model.setGithubID("")
        _clearGithubID.postValue("")
    }

    fun setSelectRepo(name: String) {
        model.setSelectRepo(name)
    }

}