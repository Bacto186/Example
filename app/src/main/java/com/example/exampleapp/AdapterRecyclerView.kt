package com.example.exampleapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exampleapp.database.EntityApp

class AdapterRecyclerView : RecyclerView.Adapter<AdapterRecyclerView.ViewHolderRecycleView>() {
    var apps = listOf<EntityApp>()
        set(value) {
            field =value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderRecycleView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView: View = layoutInflater.inflate(R.layout.item_recyclerview, parent, false)
        return ViewHolderRecycleView(itemView)
    }

    override fun getItemCount() = apps.size

    override fun onBindViewHolder(holder: ViewHolderRecycleView, position: Int) {
        val app = apps[position]
        holder.txtName.text = app.nameApp
        holder.txtDescription.text = app.descriptionApp
    }
    class ViewHolderRecycleView(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtName: TextView = itemView.findViewById(R.id.nameApp)
        val txtDescription: TextView = itemView.findViewById(R.id.descriptionApp)
    }
}