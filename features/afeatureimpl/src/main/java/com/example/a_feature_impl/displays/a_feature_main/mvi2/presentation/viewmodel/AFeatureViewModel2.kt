package com.example.a_feature_impl.displays.a_feature_main.mvi2.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.Result
import com.example.a_feature_impl.displays.a_feature_main.AFeatureRepository
import com.example.a_feature_impl.displays.a_feature_main.mvi2.domain.GetForYouDataUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import timber.log.Timber

class AFeatureViewModel2 @AssistedInject constructor(
	private val getForYouDataUseCase: GetForYouDataUseCase,
	repository: AFeatureRepository,
	@Assisted savedStateHandle: SavedStateHandle,
) : BaseViewModel<ForYouScreenReducer.ForYouState, ForYouScreenReducer.ForYouEvent, ForYouScreenReducer.ForYouEffect>(
	initialState = ForYouScreenReducer.ForYouState.initial(),
	reducer = ForYouScreenReducer()
) {


	override suspend fun initialDataLoad() {
		getForYouDataUseCase(Unit).collect { result ->
			sendEvent(
				event = ForYouScreenReducer.ForYouEvent.UpdateLoading(
					isLoading = result.isLoading()
				)
			)

			when (result) {
				is Result.BusinessRuleError -> when (result.error) {
					GetForYouDataUseCase.GetForYouDataErrors.NoNewsFound -> {
						Timber.e("BusinessRuleError: No news found")
					}

					GetForYouDataUseCase.GetForYouDataErrors.NoTopicsFound -> {
						Timber.e("BusinessRuleError: No topics found")
					}
				}

				is Result.Error -> {
					Timber.e("Error: ${result.error}")
				}

				is Result.Loading -> Unit
				is Result.Success -> when (val data = result.data) {
					is GetForYouDataUseCase.GetForYouDataSuccess.NewsData -> sendEvent(
						event = ForYouScreenReducer.ForYouEvent.UpdateNews(
							news = data.news
						)
					)

					is GetForYouDataUseCase.GetForYouDataSuccess.TopicsData -> sendEvent(
						event = ForYouScreenReducer.ForYouEvent.UpdateTopics(
							topics = data.topics
						)
					)
				}
			}
		}
	}

	fun onTopicClick(topicId: String) {
		sendEffect(
			effect = ForYouScreenReducer.ForYouEffect.NavigateToTopic(
				topicId = topicId
			)
		)
	}

	@AssistedFactory
	interface Factory {
		fun create(savedStateHandle: SavedStateHandle): AFeatureViewModel2
	}
}