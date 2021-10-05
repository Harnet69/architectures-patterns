package com.example.archpattern.mvp

import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.archpattern.BaseActivity
import com.example.archpattern.R
import com.example.archpattern.databinding.ActivityMvpactivityBinding

class MVPActivity : BaseActivity(), UniversitiesPresenter.UniversitiesView {
    private lateinit var binding: ActivityMvpactivityBinding
    private val universitiesList = arrayListOf<String>()
    private var adapter: ArrayAdapter<String>? = null

    //TODO inject it instead of instantiate
    private lateinit var presenter: UniversitiesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvpactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = UniversitiesPresenter(this)

        adapter = ArrayAdapter<String>(this, R.layout.row_layout, R.id.name, universitiesList)

        binding.list.adapter = adapter

        binding.list.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                Toast.makeText(this, "Click on ${universitiesList[position]}", Toast.LENGTH_SHORT)
                    .show()
            }

        setValues(universitiesList)
    }

    override fun onError(errorMsg: String?){
        binding.list.isVisible = false
        binding.progressBar.isVisible = false
        Toast.makeText(this,resources.getString(R.string.error_network), Toast.LENGTH_LONG).show()
        Log.i("UniReceived", "onSuccess: $errorMsg")
    }

    override fun onLoading(){
        binding.list.isVisible = false
        binding.progressBar.isVisible = true
    }

    override fun setValues(universities: List<String>){
        universitiesList.clear()
        universitiesList.addAll(universities)
        binding.list.isVisible = true
        binding.progressBar.isVisible = false
        adapter?.notifyDataSetChanged()
    }
}