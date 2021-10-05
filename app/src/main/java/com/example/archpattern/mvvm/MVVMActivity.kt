package com.example.archpattern.mvvm

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProviders
import com.example.archpattern.BaseActivity
import com.example.archpattern.R
import com.example.archpattern.databinding.ActivityMvvmactivityBinding

class MVVMActivity : BaseActivity() {
    private lateinit var binding: ActivityMvvmactivityBinding
    private lateinit var viewModel: UniversitiesViewModel
    private val universitiesList = arrayListOf<String>()

    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvvmactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(UniversitiesViewModel::class.java)

        adapter = ArrayAdapter<String>(this, R.layout.row_layout, R.id.name, universitiesList)

        binding.list.adapter = adapter

        binding.list.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Toast.makeText(this, "Click on ${universitiesList[position]}", Toast.LENGTH_SHORT)
                    .show()
            }

        observeViewModel()

//        setValues(universitiesList)
    }

    private fun observeViewModel(){
        viewModel.universitiesList.observe(this, {
            setValues(it)
        })
        viewModel.isLoading.observe(this, {
            if(it) onLoading()
        })

        viewModel.universitiesError.observe(this, {
            it?.let { onError(it)}
        })
    }

    private fun onError(errorMsg: String?) {
        binding.list.isVisible = false
        binding.progressBar.isVisible = false
        Toast.makeText(this, resources.getString(R.string.error_network), Toast.LENGTH_LONG).show()
        Log.i("UniReceived", "onSuccess: $errorMsg")
    }

    fun onLoading(){
        binding.list.isVisible = false
        binding.progressBar.isVisible = true
    }

    private fun setValues(universities: List<String>) {
        universitiesList.clear()
        universitiesList.addAll(universities)
        binding.list.isVisible = true
        binding.progressBar.isVisible = false
        adapter?.notifyDataSetChanged()
    }
}