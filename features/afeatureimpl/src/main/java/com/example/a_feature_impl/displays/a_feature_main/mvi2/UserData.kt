package com.example.a_feature_impl.displays.a_feature_main.mvi2

import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.DarkThemeConfig
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.ThemeBrand

/**
 * Class summarizing user interest data
 */
data class UserData(
    val bookmarkedNewsResources: Set<String>,
    val viewedNewsResources: Set<String>,
    val followedTopics: Set<String>,
    val themeBrand: ThemeBrand,
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean,
    val shouldHideOnboarding: Boolean,
) {
    companion object {
        fun fake() = UserData(
            bookmarkedNewsResources = setOf("1", "2", "3"),
            viewedNewsResources = setOf("4", "5", "6"),
            followedTopics = setOf("7", "8", "9"),
            themeBrand = ThemeBrand.DEFAULT,
            darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
            useDynamicColor = true,
            shouldHideOnboarding = false,
        )
    }
}
