package com.example.testproj.views.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testproj.R
import com.example.testproj.databinding.LayoutRowItemBinding
import com.example.testproj.model.CharactersDataItem

class CharactersAdapter(private val list:MutableList<CharactersDataItem> = mutableListOf()): RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding:LayoutRowItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(data: CharactersDataItem){
            with(binding){
                txtName.text  = data.name
                imgCharacter.apply {
                    Glide.with(context).load(data.image).
                    placeholder(R.drawable.ic_launcher_background).
                    error(R.drawable.ic_launcher_background).
                    into(this)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    fun updateData(mutableList: MutableList<CharactersDataItem>){
        with(list){
            clear()
            addAll(mutableList)
            notifyItemRangeInserted(0,size)
        }
    }
}