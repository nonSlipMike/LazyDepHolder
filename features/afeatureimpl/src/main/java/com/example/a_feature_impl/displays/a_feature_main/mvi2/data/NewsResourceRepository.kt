package com.example.a_feature_impl.displays.a_feature_main.mvi2.data

import com.example.a_feature_impl.displays.a_feature_main.mvi2.domain.Entities.NewsResource
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.DataResult


interface NewsResourceRepository {
    suspend fun getNewsResources(): DataResult<List<NewsResource>, NewsResourceRepositoryError>
    suspend fun getNewsResource(id: String): NewsResource

    sealed class NewsResourceRepositoryError {
        data object NoNewsResources : NewsResourceRepositoryError()
    }
}
