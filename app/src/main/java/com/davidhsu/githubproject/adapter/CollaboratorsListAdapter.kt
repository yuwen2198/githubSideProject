package com.davidhsu.githubproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.davidhsu.githubproject.LogUtil
import com.davidhsu.githubproject.R
import com.davidhsu.githubproject.api.responseData.RepoCollaboratorsResponse
import kotlinx.android.synthetic.main.recycler_collaborators_adapter_item.view.*

class CollaboratorsListAdapter(private val context: Context, private var items: List<RepoCollaboratorsResponse>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recycler_collaborators_adapter_item, parent, false)
        return CollaboratorsViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        LogUtil.i("name = ${items[position].login}")
        holder.itemView.textCollaboratorName.text = items[position].login
        holder.itemView.textCollaboratorID.text = items[position].id.toString()

        Glide.with(context)
            .load(items[position].avatar_url)
            .apply(RequestOptions.circleCropTransform())
            .into(holder.itemView.ImageCollaboratorHeader)
    }

    fun setData(item: List<RepoCollaboratorsResponse>) {
        this.items = item
        notifyDataSetChanged()
    }

    inner class CollaboratorsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

}