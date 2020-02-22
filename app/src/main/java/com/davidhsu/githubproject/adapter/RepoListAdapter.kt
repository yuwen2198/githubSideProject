package com.davidhsu.githubproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.api.responseData.RepoListResponse
import com.davidhsu.githubproject.callback.RepoItemClickListener
import kotlinx.android.synthetic.main.adapter_item_repo.view.*

class RepoListAdapter(private var items: List<RepoListResponse>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var repoItemClickListener: RepoItemClickListener ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.adapter_item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        LogUtil.i("name = ${items[position].name}")
        holder.itemView.repoName.text = items[position].name
        holder.itemView.repoName.setOnClickListener {
            repoItemClickListener?.onItemClick(items[position].name)
        }
    }

    fun setData(items: List<RepoListResponse>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun setCallBack(callback: RepoItemClickListener) {
        repoItemClickListener = callback
    }

    inner class RepoViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)
}