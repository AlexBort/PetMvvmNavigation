package com.example.mvvmnavigation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.mvvmnavigation.databinding.FragmentListBinding

class ListFragment : Fragment() {
    private var viewModel: ListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        lifecycle.addObserver(viewModel!!)
        viewModel!!.data.observe(this,
            { strings -> Log.d("TEST", strings.toString()) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = FragmentListBinding.inflate(inflater, container, false)
        view.viewmodel = viewModel
        val listView = view.listView
        viewModel!!.users.observe(
            viewLifecycleOwner,
            { list -> listView.adapter = UserListAdapter(context, list) })
        return view.root
    }
}