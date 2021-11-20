package com.example.mvvmnavigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmnavigation.databinding.FragmentListBinding
import java.util.*

class ListFragment : Fragment() {
    var viewModel: ListViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        lifecycle.addObserver(viewModel!!)
        val map: Map<*, *> = HashMap<Any?, Any?>()
        viewModel!!.data.observe(this,
            { strings -> Log.d("TEST", strings.toString()) })
        val listeners: Set<TaskListener> = HashSet()
        for (taskListener in listeners) {
        }
    }

    fun fori() {
        val list: List<Int> = ArrayList()
        for (i in list.indices) {
            print(list[i])
        }
    }

    fun foreach() {
        val list: List<Int> = ArrayList()
        for (integer in list) {
            print(integer)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentListBinding.inflate(inflater, container, false)
        view.viewmodel = viewModel
        val listView = view.listView
        viewModel!!.usersList.observe(
            this,
            { list -> listView.adapter = UserListAdapter(context, list) })
        return view.root
    }
}