package com.davidhsu.githubproject.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.davidhsu.githubproject.LoadingDialog
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.adapter.RepoListAdapter
import com.davidhsu.githubproject.api.responseData.InfoResponse
import com.davidhsu.githubproject.api.responseData.RepoListResponse
import com.davidhsu.githubproject.callback.RepoItemClickListener
import com.davidhsu.githubproject.viewmodel.UserInfoViewModel
import kotlinx.android.synthetic.main.activity_userinfo.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserInfoActivity : AppCompatActivity() {

    private val viewModel by viewModel<UserInfoViewModel> { parametersOf(this) }

    private val repoAdapter by lazy {
        RepoListAdapter(emptyList())
    }

    private val loadingDialog : LoadingDialog by lazy {
        LoadingDialog(this, getString(R.string.loading), R.drawable.ic_dialog_loading)
    }

    private val itemClick = object : RepoItemClickListener{
        override fun onItemClick(itemName: String) {
            LogUtil.i("repo item click")
            viewModel.setSelectRepo(itemName)
            goRepoInfoActivity(itemName)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userinfo)

        showLoadingDialog()
        initRecyclerView()
        viewModel.getUserInfo()
        setObserver()

        logoutButton.setOnClickListener {
            LogUtil.i("logout click")
            viewModel.logout()
        }
    }

    private fun setObserver() {
        viewModel.infoData.observe(this, Observer {
            setUserInfoData(it)
        })

        viewModel.clearGithubID.observe(this, Observer {
            if(it.isEmpty()){
                goLoginActivity()
            } else {
                LogUtil.e(getString(R.string.delete_id_error))
            }
        })

        viewModel.repoData.observe(this, Observer {
            setUserRepoList(it)
            cancelLoadingDialog()
        })

        viewModel.getError.observe(this, Observer {
            viewModel.logout()
            Toast.makeText(this, getString(R.string.error_id), Toast.LENGTH_SHORT).show()
            cancelLoadingDialog()
        })
    }

    private fun setUserInfoData(infoResponse: InfoResponse) {
        LogUtil.i("setUserInfoData")
        Glide.with(this)
            .load(infoResponse.header_url)
            .apply(RequestOptions.circleCropTransform())
            .into(imageHeader)

        textName.text = infoResponse.name
        userID.text = infoResponse.id.toString()
    }

    private fun initRecyclerView() {
        repoList.apply {
            layoutManager = LinearLayoutManager(this@UserInfoActivity)
            repoAdapter.setCallBack(itemClick)
            adapter = repoAdapter
        }
    }

    private fun setUserRepoList(list: List<RepoListResponse>) {
        LogUtil.i("setUserRepoList list size = ${list.size}")
        if (!list.isNullOrEmpty()) {
            repoAdapter.setData(list)
        }
    }

    private fun goLoginActivity() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun goRepoInfoActivity(itemName: String) {
        val intent = Intent(this, RepoInfoActivity::class.java)
        intent.putExtra("item", itemName)
        startActivity(intent)
    }

    private fun showLoadingDialog() {
        loadingDialog.show()
    }

    private fun cancelLoadingDialog() {
        loadingDialog.dismiss()
    }

}