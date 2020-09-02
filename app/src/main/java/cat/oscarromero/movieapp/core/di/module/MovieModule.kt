package cat.oscarromero.movieapp.core.di.module

import cat.oscarromero.data.repository.MoviesRepositoryNetwork
import cat.oscarromero.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class MovieModule {

    @Binds
    abstract fun moviesRepositoryProvider(moviesRepositoryNetwork: MoviesRepositoryNetwork): MoviesRepository
}
