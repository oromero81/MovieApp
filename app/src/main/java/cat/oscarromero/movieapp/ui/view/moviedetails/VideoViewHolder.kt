package cat.oscarromero.movieapp.ui.view.moviedetails

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cat.oscarromero.movieapp.ui.model.VideoModel
import kotlinx.android.synthetic.main.cell_video.view.*

class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(videoModel: VideoModel, videoSelected: (VideoModel) -> Unit) {
        with(itemView) {
            videoTypeTextView.text = videoModel.type
            setOnClickListener { videoSelected.invoke(videoModel) }
        }
    }
}
