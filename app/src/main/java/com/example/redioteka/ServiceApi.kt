package com.example.redioteka

import com.example.redioteka.models.Actor
import com.example.redioteka.models.Genre
import com.example.redioteka.models.Message
import com.example.redioteka.models.Movie
import com.example.redioteka.models.MovieFavourite
import com.example.redioteka.models.MovieGenre
import com.example.redioteka.models.MovieNewest
import com.example.redioteka.models.MovieTop
import com.example.redioteka.models.SearchResult
import com.example.redioteka.models.Stream
import com.example.redioteka.models.User
import com.example.redioteka.models.UserAuth
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {
    @GET("users/{user}")
    suspend fun getUser(@Path("user") userId: String): User

    @PATCH("users/{user}")
    suspend fun updateUser(
        @Path("user") userId: String,
        @Body form: User
    ): User

    @PUT("users/{user}/avatar")
    suspend fun updateAvatar(
        @Path("user") userId: String,
        @Part avatar: MultipartBody.Part
    ): User

    @POST("users/signup")
    suspend fun signupUser(@Body form: UserAuth): User

    @POST("users/login")
    suspend fun loginUser(@Body form: UserAuth): User

    @GET("users/logout")
    suspend fun logoutUser()

    @GET("media/movie/{movie}")
    suspend fun getMovie(@Path("movie") movieId: String): Movie

    @POST("media/movie/{movie}/like")
    suspend fun likeMovie(@Path("movie") movieId: String): Message

    @POST("media/movie/{movie}/dislike")
    suspend fun dislikeMovie(@Path("movie") movieId: String): Message

    @POST("media/movie/{movie}/favourites")
    suspend fun favouriteMovie(
        @Path("movie") movieId: String,
        @Query("action") action: String
    )

    @GET("media/movie/{movie}/stream")
    suspend fun getMovieStream(@Path("movie") movieId: String): List<Stream>

    @GET("users/{user}/media")
    suspend fun getUserFavourites(@Path("user") userId: String): MovieFavourite

    @GET("media/category/top")
    suspend fun getTop(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("type") type: String
    ): MovieTop

    @GET("media/category/newest")
    suspend fun getNewest(
        @Query("limit") limit: Int,
        @Query("type") type: String
    ): MovieNewest

    @GET("media/category/newest")
    suspend fun getGenreMovies(
        @Query("limit") limit: Int,
        @Query("type") type: String,
        @Query("genres") genre: String
    ): MovieGenre

    @GET("media/genres")
    suspend fun getGenres(): List<Genre>

    @GET("actors/{actor}")
    suspend fun getActor(@Path("actor") actorId: String): Actor

    @GET("search")
    suspend fun search(@Query("query") query: String): SearchResult
}
