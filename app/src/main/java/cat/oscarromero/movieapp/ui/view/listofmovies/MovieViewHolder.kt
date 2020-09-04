package cat.oscarromero.movieapp.ui.view.listofmovies

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cat.oscarromero.movieapp.core.loadImageFromUrl
import cat.oscarromero.movieapp.ui.model.MovieModel
import kotlinx.android.synthetic.main.cell_movie.view.*

class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(movieModel: MovieModel, movieSelected: (MovieModel) -> Unit) {
        with(itemView) {
            thumbnailImageView.loadImageFromUrl(movieModel.thumbnail)

            setOnClickListener { movieSelected.invoke(movieModel) }
        }
    }
}
