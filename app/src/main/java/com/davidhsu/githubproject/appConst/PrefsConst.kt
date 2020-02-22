package com.davidhsu.githubproject.appConst

const val SHAREDPREFERENCES_NAME = "github_project"

object PrefsConst {

    object App {
        const val NAME = SHAREDPREFERENCES_NAME
    }

    object UserAccountData {
        const val ACCOUNT = "account"
        const val PASSWORD = "password"
        const val GITHUB_ID = "github_id"
    }

    object ApiConst {
        const val MAX_PARALLEL_REQUESTS = 64
        const val MAX_PARALLEL_REQUESTS_PER_HOST = 16
        const val READ_TIMEOUT_SECONDS = 15L
        const val CONN_TIMEOUT_SECONDS = 30L
        const val IDLE_CONNECTION_NUMBER = 5
        const val KEEP_ALIVE_TIMEOUT = 60L
    }

    object RepoData{
        const val SELECT_REPO = "select_repo"
    }

}