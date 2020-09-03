package cat.oscarromero.data

import cat.oscarromero.data.adapter.NetworkResponse
import cat.oscarromero.data.adapter.NetworkResponseAdapterFactory
import cat.oscarromero.data.dto.GenericErrorDto
import cat.oscarromero.data.dto.MovieDetailsDto
import cat.oscarromero.data.dto.MoviesResponseDto
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.File
import java.util.concurrent.TimeUnit

typealias GenericResponse<S> = NetworkResponse<S, GenericErrorDto>

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query(QUERY_PARAM_API_KEY) apiKey: String,
        @Query(QUERY_PARAM_LANGUAGE) language: String
    ): GenericResponse<MoviesResponseDto>

    @GET("movie/{$PATH_ID}")
    suspend fun getMovieDetails(
        @Path(PATH_ID) id: Int,
        @Query(QUERY_PARAM_API_KEY) apiKey: String,
        @Query(QUERY_PARAM_LANGUAGE) language: String,
        @Query(QUERY_PARAM_APPEND_TO_RESPONSE) appendToResponse: String
    ): GenericResponse<MovieDetailsDto>

    companion object {
        private const val CACHE_SIZE: Long = 20 * 1024 * 1024
        private const val TIMEOUT_REQUEST: Long = 45

        fun createMovieApi(cacheDir: File): MovieApi {
            val cache = Cache(cacheDir, CACHE_SIZE)
            val httpClient = OkHttpClient.Builder()
                .cache(cache)
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    this.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(NetworkResponseAdapterFactory())
                .build()
                .create(MovieApi::class.java)
        }
    }
}
