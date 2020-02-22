package com.davidhsu.githubproject.api.responseData

data class UserRepoCommit(
    val author: Any,
    val comments_url: String,
    val commit: Commit,
    val committer: Any,
    val html_url: String,
    val node_id: String,
    val parents: List<Any>,
    val sha: String,
    val url: String
)

data class Commit(
    val author: Author,
    val comment_count: Int,
    val committer: Committer,
    val message: String,
    val tree: Tree,
    val url: String,
    val verification: Verification
)

data class Author(
    val date: String,
    val email: String,
    val name: String
)

data class Committer(
    val date: String,
    val email: String,
    val name: String
)

data class Tree(
    val sha: String,
    val url: String
)

data class Verification(
    val payload: Any,
    val reason: String,
    val signature: Any,
    val verified: Boolean
)