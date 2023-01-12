package com.welder.appdefilmes.view.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.welder.appdefilmes.databinding.CategoriaItemBinding
import com.welder.appdefilmes.view.Model.Categoria
import com.welder.appdefilmes.view.Model.Filme

class AdapterCategoria (private  val context:Context,  val listaCategoria: MutableList<Categoria>):
    RecyclerView.Adapter<AdapterCategoria.CategoriaViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriaViewHolder {
      val itemLista = CategoriaItemBinding.inflate(LayoutInflater.from(context), parent,false)
    return CategoriaViewHolder(itemLista)
    }

    override fun onBindViewHolder(holder: CategoriaViewHolder, position: Int) {
        holder.titulo.text = listaCategoria[position].titulo
        val  categoria = listaCategoria[position]
        holder.recyclerViewFilmes.adapter = AdapterFilme(context,categoria.filmes)
        holder.recyclerViewFilmes.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
    }

    override fun getItemCount() = listaCategoria.size



    inner class CategoriaViewHolder(binding: CategoriaItemBinding): RecyclerView.ViewHolder(binding.root){

        val titulo = binding.txtTituloCategoria
        val recyclerViewFilmes = binding.recyclerViewFilmes
    }
 }



