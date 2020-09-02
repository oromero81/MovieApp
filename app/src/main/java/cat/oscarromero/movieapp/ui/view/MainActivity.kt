package cat.oscarromero.movieapp.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cat.oscarromero.movieapp.R
import cat.oscarromero.movieapp.ui.view.listofmovies.ListOfMoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.containerLayout, ListOfMoviesFragment.newInstance())
            .commit()
    }
}
