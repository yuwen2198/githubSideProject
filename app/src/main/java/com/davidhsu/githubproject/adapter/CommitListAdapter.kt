package com.davidhsu.githubproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.api.responseData.UserRepoCommit
import kotlinx.android.synthetic.main.recycler_commit_adapter_item.view.*

class CommitListAdapter(private var items: List<UserRepoCommit>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_commit_adapter_item, parent, false)
        return CommitViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.commitItem.text = items[position].commit.message
    }

    fun setData(item: List<UserRepoCommit>) {
        this.items = item
        notifyDataSetChanged()
    }

    inner class CommitViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

}