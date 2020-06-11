package com.example.movieappmvvm.ui.genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappmvvm.R
import com.example.movieappmvvm.data.model.GenresItem
import kotlinx.android.synthetic.main.item_genre.view.*

class GenreAdapter(var context: GenreActivity, var dataList: List<GenresItem>, private val itemClick: OnItemClickListener): RecyclerView.Adapter<GenreAdapter.ViewHolder>() {
    lateinit var listener: OnItemClickListener

    companion object {
        var mItemClickListener : OnItemClickListener? = null
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_genre, viewGroup, false)
        )
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindGenre(dataList[position]!!)
        mItemClickListener = itemClick
        val item = dataList[position]!!
        holder.itemView.setOnClickListener{
            mItemClickListener!!.onClick(it, item)
        }
    }

    interface OnItemClickListener {
        fun onClick(view: View, itemView: GenresItem)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val genreName = itemView.genre_name

        fun bindGenre(genreResponse: GenresItem) {
            genreName.text = genreResponse.name
        }
    }
}