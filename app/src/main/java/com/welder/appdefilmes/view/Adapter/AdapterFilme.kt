package com.welder.appdefilmes.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.welder.appdefilmes.databinding.FilmeItemBinding
import com.welder.appdefilmes.view.Model.Filme

 class AdapterFilme (private val context: Context, private val listFilmes:MutableList<Filme>):
    RecyclerView.Adapter<AdapterFilme.FilmeViewHolder>(){



     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
         val itemLista = FilmeItemBinding.inflate(LayoutInflater.from(context),parent, false)
         return FilmeViewHolder(itemLista)
     }

     override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
         Glide.with(context).load(listFilmes[position].capa).centerCrop().into(holder.capa)
     }

     override fun getItemCount() = listFilmes.size


     inner class FilmeViewHolder(binding: FilmeItemBinding): RecyclerView.ViewHolder(binding.root){

         val capa = binding.capaFilme


     }

 }