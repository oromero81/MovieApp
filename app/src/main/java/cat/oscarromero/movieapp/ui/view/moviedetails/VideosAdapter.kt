package cat.oscarromero.movieapp.ui.view.moviedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.ui.model.VideoModel

class VideosAdapter(private val videoSelected: (VideoModel) -> Unit) :
    RecyclerView.Adapter<VideoViewHolder>() {

    private val videos: MutableList<VideoModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder =
        VideoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_video, parent, false)
        )

    override fun getItemCount(): Int =
        videos.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videos[position], videoSelected)
    }

    fun loadVideos(videos: List<VideoModel>) {
        this.videos.clear()
        this.videos.addAll(videos)
        notifyDataSetChanged()
    }
}
