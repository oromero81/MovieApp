package cat.oscarromero.movieapp.core.di.module

import android.content.Context
import cat.oscarromero.data.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun movieApiProvider(@ApplicationContext context: Context): MovieApi =
        MovieApi.createMovieApi(context.cacheDir)
}
