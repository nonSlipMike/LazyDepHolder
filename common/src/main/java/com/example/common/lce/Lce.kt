package com.example.common.lce

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

typealias MyFlow<T> = Flow<Lce<T>>

typealias MyFlowList<T> = Flow<Lce<List<T>>>

sealed class Lce<out T> {

    open val data: T? = null

    abstract fun <R> map(f: (T) -> R): Lce<R>

    inline fun doOnData(f: (T) -> Unit) {
        if (this is Success) {
            f(data)
        }
    }

    data class Success<out T>(override val data: T) : Lce<T>() {
        override fun <R> map(f: (T) -> R): Lce<R> = Success(f(data))
    }

    data class Error(val message: String) : Lce<Nothing>() {
        constructor(t: Throwable) : this(t.message ?: "")

        override fun <R> map(f: (Nothing) -> R): Lce<R> = this
    }

    object Loading : Lce<Nothing>() {
        override fun <R> map(f: (Nothing) -> R): Lce<R> = this
    }
}

inline fun <S> lce(crossinline block: suspend () -> S): Flow<Lce<S>> = flow {
    emit(Lce.Loading)
    emit(Lce.Success(block()))
}.catch { emit(Lce.Error(it)) }
