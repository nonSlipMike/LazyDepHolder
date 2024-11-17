package com.example.a_feature_impl.displays.a_feature_main.mvi2.domain

import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.DataResult
import com.example.a_feature_impl.displays.a_feature_main.mvi2.data.NewsResourceRepository
import com.example.a_feature_impl.displays.a_feature_main.mvi2.data.TopicsRepository
import com.example.a_feature_impl.displays.a_feature_main.mvi2.UserData
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.Result
import com.example.a_feature_impl.displays.a_feature_main.mvi2.domain.Entities.UserNewsResource
import com.example.a_feature_impl.displays.a_feature_main.mvi2.domain.Entities.mapToUserNewsResources
import com.example.a_feature_impl.displays.a_feature_main.mvi2.domain.GetForYouDataUseCase.GetForYouDataErrors
import com.example.a_feature_impl.displays.a_feature_main.mvi2.domain.GetForYouDataUseCase.GetForYouDataSuccess
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.FollowableTopic
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.mapToFollowableTopic

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetForYouDataUseCase @Inject constructor(
	private val topicsRepository: TopicsRepository,
	private val newsResourceRepository: NewsResourceRepository,
	private val dispatcher: CoroutineDispatcher
) : FlowUseCase<Unit, GetForYouDataSuccess, GetForYouDataErrors>(dispatcher) {

    sealed class GetForYouDataErrors {
        data object NoTopicsFound : GetForYouDataErrors()
        data object NoNewsFound : GetForYouDataErrors()
    }

    sealed class GetForYouDataSuccess {
        data class TopicsData(val topics: List<FollowableTopic>) : GetForYouDataSuccess()
        data class NewsData(val news: List<UserNewsResource>) : GetForYouDataSuccess()
    }

    override fun execute(parameters: Unit): Flow<Result<GetForYouDataSuccess, GetForYouDataErrors>> {
        return flow {
            emit(Result.Loading)

            val topicsResult = topicsRepository.getTopics()
            val newsResult = newsResourceRepository.getNewsResources()

            when (topicsResult) {
                is DataResult.Error -> when (topicsResult.error) {
                    TopicsRepository.TopicsRepositoryError.NoTopics ->
                        emit(Result.BusinessRuleError(GetForYouDataErrors.NoTopicsFound))
                }

                is DataResult.Success -> emit(
                    Result.Success(
                        GetForYouDataSuccess.TopicsData(
                            topicsResult.data.mapToFollowableTopic(
                                UserData.fake()
                            )
                        )
                    )
                )
            }

            when (newsResult) {
                is DataResult.Error -> when (newsResult.error) {
                    NewsResourceRepository.NewsResourceRepositoryError.NoNewsResources ->
                        emit(Result.BusinessRuleError(GetForYouDataErrors.NoNewsFound))

                }

                is DataResult.Success -> emit(
                    Result.Success(
                        GetForYouDataSuccess.NewsData(
                            newsResult.data.mapToUserNewsResources(
                                UserData.fake()
                            )
                        )
                    )
                )
            }
        }
    }
}
