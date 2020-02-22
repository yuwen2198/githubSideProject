package com.davidhsu.githubproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.adapter.CommitListAdapter
import com.davidhsu.githubproject.viewmodel.RepoInfoViewModel
import kotlinx.android.synthetic.main.fragment_commit_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommitListFragment: Fragment() {

    private val viewModel by viewModel<RepoInfoViewModel> { parametersOf(this) }

    private val commitDataAdapter by lazy {
        CommitListAdapter(emptyList())
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_commit_list, container, false)
        initRecyclerView(view)
        viewModel.getRepoCommitData()
        return view
    }

    private fun initRecyclerView(view: View) {
        view.commit_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = commitDataAdapter
        }
    }

    private fun setObserver() {
        viewModel.getRepoCommit.observe(viewLifecycleOwner, Observer {
            LogUtil.i("repo commit list size = ${it.size}")
            commitDataAdapter.setData(it)
        })
    }

}