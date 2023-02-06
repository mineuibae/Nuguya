package com.nugu.ya.core.usecase

abstract class BaseUseCase<in PARAMETER, RESPONSE> {
    protected abstract fun execute(parameter: PARAMETER) : RESPONSE
    operator fun invoke(parameter: PARAMETER) = execute(parameter)
}