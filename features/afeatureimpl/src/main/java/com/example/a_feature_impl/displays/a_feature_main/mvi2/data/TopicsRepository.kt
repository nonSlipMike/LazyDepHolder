package com.example.a_feature_impl.displays.a_feature_main.mvi2.data

import com.example.a_feature_impl.displays.a_feature_main.mvi2.Topic
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.DataResult

interface TopicsRepository {
    suspend fun getTopics(): DataResult<List<Topic>, TopicsRepositoryError>
    suspend fun getTopic(id: String): Topic

    sealed class TopicsRepositoryError {
        data object NoTopics : TopicsRepositoryError()
    }
}
