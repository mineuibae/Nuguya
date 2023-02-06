package com.nugu.ya.core.usecase

import com.nugu.ya.core.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

abstract class ResultUseCase<in PARAMETER, RESPONSE>(
    private val dispatcher: CoroutineDispatcher
) {
    protected abstract suspend fun execute(parameter: PARAMETER) : Result<RESPONSE>

    suspend operator fun invoke(parameter: PARAMETER) : Result<RESPONSE> = withContext(dispatcher) {
        return@withContext try {
            execute(parameter)
        } catch (e: Throwable) {
            Result.Error(e)
        }
    }
}