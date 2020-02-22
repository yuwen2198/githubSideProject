package com.davidhsu.githubproject.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.adapter.CollaboratorsListAdapter
import com.davidhsu.githubproject.viewmodel.RepoInfoViewModel
import kotlinx.android.synthetic.main.fragment_collaborators_list.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CollaboratorsFragment: Fragment() {

    private val viewModel by viewModel<RepoInfoViewModel> { parametersOf(this) }

    private val collaboratorsDataAdapter by lazy {
        CollaboratorsListAdapter(context!!, emptyList())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_collaborators_list, container, false)
        initRecyclerView(view)
        viewModel.getRepoCollaboratorsData()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
    }

    private fun initRecyclerView(view: View) {
        view.collaborators_recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = collaboratorsDataAdapter
        }
    }

    private fun setObserver() {
        viewModel.getRepoCollaborators.observe(viewLifecycleOwner, Observer {
            LogUtil.i("repo Collaborators list size = ${it.size}")
            collaboratorsDataAdapter.setData(it)
        })

        viewModel.collaboratorsError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, getString(R.string.get_collaborators_error), Toast.LENGTH_LONG).show()
        })

    }

}