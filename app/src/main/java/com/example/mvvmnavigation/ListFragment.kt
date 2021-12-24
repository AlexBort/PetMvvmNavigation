package com.example.mvvmnavigation


import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.mvvmnavigation.databinding.FragmentListBinding
import com.example.mvvmnavigation.models.User

class ListFragment : Fragment() {
    private var viewModel: KtListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(KtListViewModel::class.java)
        lifecycle.addObserver(viewModel!!)
        viewModel!!.dataListString.observe(this,
            Observer<List<String?>> { strings -> Log.d("TEST", strings.toString()) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentListBinding.inflate(inflater, container, false)
        view.viewmodel = viewModel
        val listView = view.listView
        viewModel!!.dataListUser.observe(
            this,
            Observer<List<User?>?> { list -> listView.adapter = UserListAdapter(context, list) })
        return view.root
    }
}