package com.welder.appdefilmes.view.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.common.api.Api
import com.google.firebase.auth.FirebaseAuth
import com.welder.appdefilmes.R
import com.welder.appdefilmes.databinding.ActivityPrincipalBinding
import com.welder.appdefilmes.view.Adapter.AdapterCategoria
import com.welder.appdefilmes.view.Model.Categoria
import com.welder.appdefilmes.view.Model.Categorias
import com.welder.appdefilmes.view.Model.Filme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PrincipalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private lateinit var adapterCategoria: AdapterCategoria
    private val listaCategorias: MutableList<Categoria> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        window.statusBarColor = Color.parseColor("#003702")

        val recyclerViewFilmes = binding.RecycleFilmes
        recyclerViewFilmes.layoutManager = LinearLayoutManager(this)
        recyclerViewFilmes.setHasFixedSize(true)
        adapterCategoria = AdapterCategoria(this, listaCategorias)
        recyclerViewFilmes.adapter = adapterCategoria


        binding.sair.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this,"Usuário saiu do App!", Toast.LENGTH_SHORT).show()
        }



        //Configurar retrofit


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            . build()
            .create(com.welder.appdefilmes.view.api.Api::class.java)

        retrofit.listaCategorias().enqueue(object : Callback<Categorias>{
            override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                if (response.code()==200){
                    response.body()?.let{
                        adapterCategoria.listaCategoria.addAll(it.categorias)
                        adapterCategoria.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<Categorias>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }




}

