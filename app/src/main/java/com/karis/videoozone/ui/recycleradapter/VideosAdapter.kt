package com.karis.videoozone.ui.recycleradapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karis.videoozone.R
import com.karis.videoozone.util.interfaces.Onclick
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.util.VideoItemuTIL
import kotlinx.android.synthetic.main.video_item.view.*

class VideosAdapter(private var videos: List<Videoitem>, onclick: Onclick) :
    RecyclerView.Adapter<VideosAdapter.ViewHolder>() {

    private var onclick: Onclick = onclick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.video_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ytvideos = videos[position]
        holder.itemView.textView_VideoTitle.text = ytvideos.snippet?.title
        holder.itemView.textView_channelName.text = ytvideos.snippet?.channelTitle
        holder.itemView.textView_Videoviews.text = VideoItemuTIL.convertViews(ytvideos.statistics?.viewCount?.toLong()!!) + " Views in "
        holder.itemView.textView_hoursReleased.text = VideoItemuTIL.covertTimeToText(ytvideos.snippet?.publishedAt)
        Glide.with(holder.itemView.context).load(ytvideos.snippet?.thumbnails?.maxres?.url).into(holder.itemView.imageView_video)
        holder.itemView.setOnClickListener {
            onclick.videoItemClicked(ytvideos)
        }
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}