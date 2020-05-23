package ru.belyaev.vitaliy.tomtimer.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.belyaev.vitaliy.tomtimer.util.CrashReporter

open class BaseViewModel : ViewModel(), CoroutineScope by MainScope() {
    open val error = MutableLiveData<Throwable>()
    open val loading = MutableLiveData<Boolean>()

    override fun onCleared() {
        super.onCleared()
        cancel()
    }

    private fun createExceptionHandler(exceptionBlock: ((Throwable) -> Boolean)? = null): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            CrashReporter.logException(exception)

            val isHandled = exceptionBlock?.invoke(exception) ?: false
            if (isHandled) return@CoroutineExceptionHandler

            error.postValue(exception)
        }
    }

    protected fun launchLoadingErrorJob(
        exceptionBlock: ((Throwable) -> Boolean)? = null,
        loadingBlock: MutableLiveData<Boolean>? = null,
        block: suspend () -> Unit
    ): Job {
        return launch(createExceptionHandler(exceptionBlock)) {
            try {
                error.postValue(null)
                if (loadingBlock != null) {
                    loadingBlock.postValue(true)
                } else {
                    loading.postValue(true)
                }
                block.invoke()
            } finally {
                if (loadingBlock != null) {
                    loadingBlock.postValue(false)
                } else {
                    loading.postValue(false)
                }
            }
        }
    }
}
