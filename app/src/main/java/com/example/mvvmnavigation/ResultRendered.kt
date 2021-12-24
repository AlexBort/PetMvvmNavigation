package com.example.mvvmnavigation

import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.children
import com.example.mvvmnavigation.databinding.PartResultBinding
import com.example.mvvmnavigation.handle_results.Result

fun <T> BaseFragment.renderSimpleResult(
    root: ViewGroup?, result: Result<T>, onSuccess: (T) -> Unit) {
    val partResultBinding = root?.let { PartResultBinding.bind(it) }
    renderResult(root = root, result = result,
        onProgress = {partResultBinding?.progressBar?.visibility = VISIBLE},
        onError = {partResultBinding?.errorContainer?.visibility = VISIBLE
            Log.e("ERROR MESSAGE= ", it.message ?: "")
                  },
        onSuccess = {root?.children?.forEach { it.visibility = GONE }
            onSuccess(it)
        }
    )

}