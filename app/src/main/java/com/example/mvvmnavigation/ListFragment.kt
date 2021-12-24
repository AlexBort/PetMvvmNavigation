package com.example.mvvmnavigation


import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvmnavigation.databinding.FragmentListBinding
import com.example.mvvmnavigation.databinding.PartResultBinding
import com.example.mvvmnavigation.handle_results.ErrorResult
import com.example.mvvmnavigation.handle_results.ProgressResult
import com.example.mvvmnavigation.handle_results.SuccessResult
import com.example.mvvmnavigation.handle_results.getMainActivity
import com.example.mvvmnavigation.models.User

class ListFragment : BaseFragment() {
    private var viewModel: KtListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KtListViewModel::class.java)
        lifecycle.addObserver(viewModel!!)


/*        viewModel!!.dataListString.observe(this,
            Observer<List<String?>> {
                when()
//                    strings -> Log.d("TEST", strings.toString())
            })*/
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentListBinding.inflate(inflater, container, false)


        viewModel?.dataListString?.observe(this) { result ->
            renderResult(
                root = getMainActivity().root,
                result = result,
                onSuccess = {
                    Log.d("ListFragment", it.toString())
                },
                onError = {
                    getMainActivity().partResultLayout.errorContainer.visibility = VISIBLE
                    Log.e("ListFragment", it.message ?: "")
                },
                onProgress = {
                    getMainActivity().partResultLayout.progressBar.visibility = VISIBLE
                })
        }


        view.viewmodel = viewModel
        val listView = view.listView

        viewModel!!.dataListUser.observe(
            this,
//            Observer<List<User?>?> { list -> listView.adapter = UserListAdapter(context, list) })
            return view.root
        )
    }
}