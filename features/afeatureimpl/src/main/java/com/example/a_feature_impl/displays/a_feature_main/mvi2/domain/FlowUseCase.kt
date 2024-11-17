package com.example.a_feature_impl.displays.a_feature_main.mvi2.domain

import android.util.Log
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.Result
import com.example.a_feature_impl.displays.a_feature_main.mvi2.utils.mapToAppError
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
//тут что то было
abstract class FlowUseCase<in Parameters, Success, BusinessRuleError>(private val dispatcher: CoroutineDispatcher) {

    operator fun invoke(parameters: Parameters): Flow<Result<Success, BusinessRuleError>> {
        return execute(parameters)
            .catch { e ->
                Log.e("FlowUseCase", "An error occurred while executing the use case", e)
                emit(Result.Error(e.mapToAppError()))
            }
            .flowOn(dispatcher)
    }

    abstract fun execute(parameters: Parameters): Flow<Result<Success, BusinessRuleError>>
}
