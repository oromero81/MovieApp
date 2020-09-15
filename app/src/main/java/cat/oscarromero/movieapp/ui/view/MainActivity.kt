package cat.oscarromero.movieapp.ui.view

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import cat.oscarromero.movieapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.navHostFragment)
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        topAppBar.setupWithNavController(navController, appBarConfiguration)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.movieDetailsFragment -> {
                    appBarLayout.setExpanded(true, false)
                    topAppBar.visibility = View.GONE
                }
                R.id.listOfMoviesFragment -> {
                    topAppBar.visibility = View.VISIBLE
                }
                R.id.videoPlayerFragment -> {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                    topAppBar.visibility = View.GONE
                }
            }
        }
    }
}
