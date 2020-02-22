package com.davidhsu.githubproject.api

import com.davidhsu.githubproject.api.responseData.InfoResponse
import com.davidhsu.githubproject.api.responseData.RepoCollaboratorsResponse
import com.davidhsu.githubproject.api.responseData.RepoListResponse
import com.davidhsu.githubproject.api.responseData.UserRepoCommit
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AppApi {
    @GET("users/{username}")
    fun getUserInfo(@Path("username") username: String): Single<InfoResponse>

    @GET("users/{username}/repos")
    fun getUserRepoList(@Path("username") username: String): Single<List<RepoListResponse>>

    @GET("repos/{username}/{repo}/commits")
    fun getUserRepoCommit(@Path("username") username: String,
                          @Path("repo") repoName: String): Single<List<UserRepoCommit>>

    @GET("repos/{username}/{repo}/collaborators")
    fun getUserRepoCollaborators(@Path("username") username: String,
                                 @Path("repo") repoName: String,
                                 @Query("access_token") client: String = "a75ab4ec7ec9d5dafcafafe15f7b1ba12a8ed83b")
            : Single<List<RepoCollaboratorsResponse>>
}