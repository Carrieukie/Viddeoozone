package com.karis.videoozone.ui.fragments.videolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karis.videoozone.R
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.util.Numberutils
import com.karis.videoozone.util.interfaces.Onclick
import kotlinx.android.synthetic.main.video_item.view.*


class MovieListAdapter(private var onclick: Onclick) : PagingDataAdapter<Videoitem, MovieListAdapter.ViewHolder>(
    DIFF_CALLBACK
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        val ytvideos = getItem(position)
        holder.itemView.textView_VideoTitle.text = ytvideos?.snippet?.title
        holder.itemView.textView_channelName.text = ytvideos?.snippet?.channelTitle
        holder.itemView.textView_Videoviews.text = Numberutils.getShortenedNumber(ytvideos?.statistics?.viewCount?.toLong()!!) + " views in "
        holder.itemView.textView_hoursReleased.text = Numberutils.covertTimeToText(ytvideos?.snippet?.publishedAt)
        Glide.with(holder.itemView.context).load(ytvideos.snippet?.thumbnails?.maxres?.url).into(
            holder.itemView.imageView_video
        )

        setFadeAnimation(holder.itemView);

        holder.itemView.setOnClickListener {
            onclick.videoItemClicked(ytvideos)
        }
    }
    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 1000
        view.startAnimation(anim)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Videoitem>() {
    override fun areItemsTheSame(oldItem: Videoitem, newItem: Videoitem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Videoitem, newItem: Videoitem): Boolean {
        return oldItem == newItem
    }

}
