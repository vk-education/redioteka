package com.example.redioteka

import com.example.redioteka.models.*
import retrofit2.http.*

interface ServiceApi {
    @GET("users/{user}")
    suspend fun getUser(@Path("user") userId: String): User

    @PATCH("users/{user}")
    suspend fun updateUser(@Path("user") userId: String,
                           @Body form: User): User

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
    suspend fun favouriteMovie(@Path("movie") movieId: String,
                               @Query("action") action: String)

    @GET("media/movie/{movie}/stream")
    suspend fun getMovieStream(@Path("movie") movieId: String): List<Stream>

    @GET("users/{user}/media")
    suspend fun getUserFavourites(@Path("user") userId: String): MovieFavourite

    @GET("media/category/top")
    suspend fun getTop(@Query("limit") limit: Int,
                       @Query("type") type: String): MovieTop

    @GET("media/category/newest")
    suspend fun getNewest(@Query("limit") limit: Int,
                          @Query("type") type: String): MovieNewest

    @GET("media/category/newest")
    suspend fun getGenreMovies(@Query("limit") limit: Int,
                               @Query("type") type: String,
                               @Query("genres") genre: String): MovieGenre

    @GET("media/genres")
    suspend fun getGenres(): List<Genre>

    @GET("actors/{actor}")
    suspend fun getActor(@Path("actor") actorId: String): Actor

    @GET("search")
    suspend fun search(@Query("query") query: String): SearchResult
}