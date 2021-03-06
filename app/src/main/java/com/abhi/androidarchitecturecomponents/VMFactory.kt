package com.abhi.androidarchitecturecomponents

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 1/2/18.
 *
 * View model factory
 */
@Singleton
class VMFactory @Inject constructor(private val viewModelMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var viewModel = viewModelMap.get(modelClass)

        if (viewModel == null) {
            for (entry in viewModelMap) {
                if (modelClass.isAssignableFrom(entry.key)) {
                    viewModel = entry.value
                    break
                }
            }
        }
        if (viewModel == null) throw IllegalArgumentException("Unknown model class" + modelClass)
        return viewModel.get() as T
    }

}