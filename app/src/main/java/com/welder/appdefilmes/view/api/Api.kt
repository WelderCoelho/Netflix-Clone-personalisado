package com.welder.appdefilmes.view.api

import com.welder.appdefilmes.view.Model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/filmes")
    fun  listaCategorias(): Call<Categorias>
}