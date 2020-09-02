package cat.oscarromero.movieapp.ui.view.listofmovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.ui.model.MovieModel

class ListOfMoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private val movies: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.cell_movie, parent, false)
        )

    override fun getItemCount(): Int =
        movies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun loadMovies(movies: List<MovieModel>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }
}
