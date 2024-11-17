package com.example.a_feature_impl.displays.a_feature_main.mvi2.domain.Entities

import com.example.a_feature_impl.displays.a_feature_main.mvi2.Topic
import kotlin.random.Random

/**
 * External data layer representation of a fully populated NiA news resource
 */
data class NewsResource(
    val id: String,
    val title: String,
    val content: String,
    val url: String,
    val headerImageUrl: String?,
    val publishDate: String,
    val type: String,
    val topics: List<Topic>,
) {
    companion object {
        fun fake() = NewsResource(
            id = Random.nextInt().toString(),
            title = "Title",
            content = "Content",
            url = "https://android.com",
            headerImageUrl = "https://android.com/image.jpg",
            publishDate = "00_00",
            type = "type",
            topics = listOf(
                Topic.fake(),
                Topic.fake()
            )
        )
    }
}