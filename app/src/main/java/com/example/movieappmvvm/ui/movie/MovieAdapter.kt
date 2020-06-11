package com.example.movieappmvvm.ui.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappmvvm.data.model.ResultsItem
import com.example.movieappmvvm.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(var context: MovieActivity, var dataList: List<ResultsItem>, private val itemClick: OnItemClickListener): RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    lateinit var listener: OnItemClickListener

    companion object {
        var mItemClickListener : OnItemClickListener? = null
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false)
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
        fun onClick(view: View, itemView: ResultsItem)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val movieName = itemView.movie_name

        fun bindGenre(movieResponse: ResultsItem) {
            movieName.text = movieResponse.name
        }
    }
}