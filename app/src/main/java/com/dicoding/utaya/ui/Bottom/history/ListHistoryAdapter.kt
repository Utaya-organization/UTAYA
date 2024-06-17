package com.dicoding.utaya.ui.Bottom.history

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.utaya.R

class ListHistoryAdapter(private val listHistory: ArrayList<History>) : RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_history, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listHistory.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (skin, foto) = listHistory[position]
        holder.ivFoto.setImageResource(foto)
        holder.tvSkin.text = skin
    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFoto: ImageView = itemView.findViewById(R.id.iv_history)
        val tvSkin: TextView = itemView.findViewById(R.id.tv_result)
    }

}