package com.example.redioteka.repository.paged

import androidx.paging.PagingSource
import com.example.redioteka.models.Movie
import com.example.redioteka.repository.MovieRepo

class SeriesPagingSource(
    private val repository: MovieRepo
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        return try {
            val nextPage = params.key ?: 0
            val movieListResponse = repository.getTopSeries(
                params.loadSize,
                nextPage * params.loadSize
            )

            LoadResult.Page(
                data = movieListResponse.movies,
                prevKey = if (nextPage == 0) null else nextPage - 1,
                nextKey = nextPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}