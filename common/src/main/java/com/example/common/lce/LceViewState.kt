package com.example.common.lce

data class LceViewState<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val error: String? = null,
    val animateError: Boolean = false
)

fun <T> Lce<T>.reduce(prev: LceViewState<T>? = null): LceViewState<T> {
    val prevState = prev ?: LceViewState()
    return when (this) {
        is Lce.Success -> prevState.copy(data = data, error = null, loading = false)
        is Lce.Loading -> prevState.copy(
            loading = true,
            error = null,
            data = null,
            animateError = false
        )
        is Lce.Error -> prevState.copy(
            error = message,
            loading = false,
            data = null,
            animateError = message.isNotEmpty()
        )
    }
}
