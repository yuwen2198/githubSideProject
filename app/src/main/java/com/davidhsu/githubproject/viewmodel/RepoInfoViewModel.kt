package com.davidhsu.githubproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.api.responseData.RepoCollaboratorsResponse
import com.davidhsu.githubproject.api.responseData.UserRepoCommit
import com.davidhsu.githubproject.extension.addTo
import com.davidhsu.githubproject.extension.report
import com.davidhsu.githubproject.models.RepoInfoModule

class RepoInfoViewModel(private val model: RepoInfoModule): AutoDisposeViewModel() {

    private val _getRepoCommit = MutableLiveData<List<UserRepoCommit>>()
    val getRepoCommit: LiveData<List<UserRepoCommit>> = _getRepoCommit
    private val _getRepoCollaborators = MutableLiveData<List<RepoCollaboratorsResponse>>()
    val getRepoCollaborators: LiveData<List<RepoCollaboratorsResponse>> = _getRepoCollaborators

    fun getRepoCommitData() {
        val id = model.getUserID()
        val repo = model.getSelectRepo()
        LogUtil.i("getRepoCommitData id = $id , repo = $repo")
        model.getRepoCommitCommit(id, repo).subscribe({
            _getRepoCommit.postValue(it)
        },{
            it.report()
        }).addTo(compositeDisposable)
    }

    fun getRepoCollaboratorsData() {
        val id = model.getUserID()
        val repo = model.getSelectRepo()
        LogUtil.i("getRepoCollaboratorsData id = $id , repo = $repo")
        model.getRepoCollaborators(id, repo).subscribe({
            _getRepoCollaborators.postValue(it)
        },{
            it.report()
        }).addTo(compositeDisposable)

    }

}