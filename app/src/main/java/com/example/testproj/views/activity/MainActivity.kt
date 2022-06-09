package com.example.testproj.views.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testproj.databinding.ActivityMainBinding
import com.example.testproj.viewModel.MainActivityViewModel
import com.example.testproj.views.adapters.CharactersAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val adapter by lazy { CharactersAdapter() }
    private var binding:ActivityMainBinding?=null
    private val viewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setUpRecyclerView()
        initObservers()
    }

    private fun initObservers(){
        with(viewModel){
            //getData()
            downloadImage()
            data.observe(this@MainActivity){
                adapter.updateData(it)
            }
            image.observe(this@MainActivity){
                binding?.pdfView?.fromStream(it)?.load()
            }
            lifecycleScope.launch {
                onError.collect {
                    binding?.root?.let { it1 -> Snackbar.make(it1.rootView,it?:"something went wrong",Snackbar.LENGTH_SHORT).show() }
                }
            }
        }
    }

    private fun setUpRecyclerView(){
        binding?.rvCharacters?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = this@MainActivity.adapter
        }
    }



}