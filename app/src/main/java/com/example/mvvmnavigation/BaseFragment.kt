package com.example.mvvmnavigation

import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.example.mvvmnavigation.handle_results.ErrorResult
import com.example.mvvmnavigation.handle_results.ProgressResult
import com.example.mvvmnavigation.handle_results.Result
import com.example.mvvmnavigation.handle_results.SuccessResult

open class BaseFragment : Fragment() {
    open fun <T> renderResult(
        root: View?, result: Result<T>,
        onProgress: () -> Unit,
        onError: (Exception) -> Unit,
        onSuccess: (T) -> Unit
    ) {
        (root as ViewGroup).children.forEach { it.visibility = GONE }
        when(result){
            is SuccessResult -> onSuccess(result.data)
            is ErrorResult -> onError(result.error)
            is ProgressResult -> onProgress()
        }

    }
}