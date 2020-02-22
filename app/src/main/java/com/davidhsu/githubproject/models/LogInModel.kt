package com.davidhsu.githubproject.models

import android.content.SharedPreferences
import com.davidhsu.githubproject.appConst.PrefsConst
import com.davidhsu.githubproject.extension.get
import com.davidhsu.githubproject.extension.set

interface LogInModel {
    fun setGithubID(githubID: String)
    fun getGithubID(): String
    fun setAccount(account: String)
    fun getAccount(): String
    fun setPassWord(password: String)
    fun getPassWord(): String
}

class LogInModelImpl(private val appPre: SharedPreferences): LogInModel {
    override fun setGithubID(githubID: String) {
        appPre[PrefsConst.UserAccountData.GITHUB_ID] = githubID
    }

    override fun getGithubID(): String = appPre[PrefsConst.UserAccountData.GITHUB_ID] ?: ""

    override fun setAccount(account: String) {
        appPre[PrefsConst.UserAccountData.ACCOUNT] = account
    }

    override fun getAccount()  = appPre[PrefsConst.UserAccountData.ACCOUNT] ?: ""

    override fun setPassWord(password: String) {
        appPre[PrefsConst.UserAccountData.PASSWORD] = password
    }

    override fun getPassWord() = appPre[PrefsConst.UserAccountData.PASSWORD] ?: ""

}