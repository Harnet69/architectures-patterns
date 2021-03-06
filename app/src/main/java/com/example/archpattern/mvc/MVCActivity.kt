package com.example.archpattern.mvc

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.archpattern.BaseActivity
import com.example.archpattern.R
import com.example.archpattern.databinding.ActivityMvcactivityBinding

class MVCActivity : BaseActivity() {
    private lateinit var binding: ActivityMvcactivityBinding
    private val universitiesList = arrayListOf<String>()
    private var adapter: ArrayAdapter<String>? = null
    //TODO inject it instead of instantiate
    private lateinit var controller: UniversitiesController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMvcactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = UniversitiesController(this)

        adapter = ArrayAdapter<String>(this, R.layout.row_layout, R.id.name, universitiesList)

        binding.list.adapter = adapter

        binding.list.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Click on ${universitiesList[position]}", Toast.LENGTH_SHORT).show()
        }

        setValues(universitiesList)
    }

    fun onError(errorMsg: String?){
        binding.list.isVisible = false
        binding.progressBar.isVisible = false
        Toast.makeText(this,resources.getString(R.string.error_network), Toast.LENGTH_LONG).show()
        Log.i("UniReceived", "onSuccess: $errorMsg")

    }

    fun onLoading(){
        binding.list.isVisible = false
        binding.progressBar.isVisible = true
    }

    fun setValues(values: List<String>){
        universitiesList.clear()
        universitiesList.addAll(values)
        binding.list.isVisible = false
        binding.progressBar.isVisible = false
        binding.list.isVisible = true
        adapter?.notifyDataSetChanged()
    }
}