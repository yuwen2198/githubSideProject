package com.davidhsu.githubproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.models.LogInModel

class LogInActivityViewModel(private val model: LogInModel): AutoDisposeViewModel() {

    private val _isFirstLogin = MutableLiveData<Boolean>()
    val isFirstLogin: LiveData<Boolean> = _isFirstLogin
    private val _isDataEmpty = MutableLiveData<Boolean>()
    val isDataEmpty: LiveData<Boolean> = _isDataEmpty

    fun checkIsFirstLogIn() {
        val githubID = model.getGithubID()

        if (githubID.isEmpty()) {
            LogUtil.i("githubID = empty")
        } else {
            LogUtil.i("githubID = $githubID")
        }

        if (githubID.isEmpty()) {
            _isFirstLogin.postValue(true)
        } else {
            _isFirstLogin.postValue(false)
        }
    }

    fun saveAccountAndPassword(githubID: String) {
        if (githubID.isEmpty()) {
            _isDataEmpty.postValue(true)
        } else {
            model.setGithubID(githubID)
            _isDataEmpty.postValue(false)
        }
    }
}