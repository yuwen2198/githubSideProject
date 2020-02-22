package com.davidhsu.githubproject.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.viewmodel.LogInActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LogInActivity : AppCompatActivity() {

    private val viewModel by viewModel<LogInActivityViewModel> { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        checkIsFirstLogin()
        setObserver()

        loginButton.setOnClickListener {
            val githubID = accountEditText.text.toString()
            viewModel.saveAccountAndPassword(githubID)
        }

    }

    private fun setObserver() {
        viewModel.isFirstLogin.observe(this, Observer {
            if (!it) {
                goUserInfoActivity()
            }
        })

        viewModel.isDataEmpty.observe(this, Observer {
            if (it) {
                LogUtil.e("githubID不能為空")
                Toast.makeText(this, getString(R.string.empty_id), Toast.LENGTH_SHORT).show()
            } else {
                goUserInfoActivity()
            }
        })
    }

    private fun checkIsFirstLogin() {
        viewModel.checkIsFirstLogIn()
    }

    private fun goUserInfoActivity() {
        val intent = Intent(this, UserInfoActivity::class.java)
        startActivity(intent)
    }
}
