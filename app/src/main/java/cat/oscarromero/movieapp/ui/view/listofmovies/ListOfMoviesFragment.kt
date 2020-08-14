package cat.oscarromero.movieapp.ui.view.listofmovies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.ui.viewmodel.ListOfMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list_of_movies.*

@AndroidEntryPoint
class ListOfMoviesFragment : Fragment() {

    private val listOfMoviesViewModel: ListOfMoviesViewModel by viewModels()
    private val moviesAdapter = ListOfMoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_of_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listOfMoviesViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer {
            moviesAdapter.loadMovies(it)
        })

        listOfMoviesViewModel.failureLiveData.observe(viewLifecycleOwner, Observer {
            Log.e("MovieAppLD", it.toString())
        })

        listOfMoviesViewModel.loadMovies()

        moviesRecyclerView.adapter = moviesAdapter
    }

    companion object {
        fun newInstance() =
            ListOfMoviesFragment()
    }
}
