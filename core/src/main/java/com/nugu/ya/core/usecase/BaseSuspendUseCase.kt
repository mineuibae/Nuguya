package com.nugu.ya.core.usecase

abstract class BaseSuspendUseCase<in PARAMETER, RESPONSE> {
    protected abstract suspend fun execute(parameter: PARAMETER) : RESPONSE
    suspend operator fun invoke(parameter: PARAMETER) = execute(parameter)
}