package com.welder.appdefilmes.view.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.welder.appdefilmes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.lgButtonEntrar.setOnClickListener {



                val  email = binding.lgCampoEmail.text.toString()
                val senha = binding.lgCampoSenha.text.toString()

                when{

                            email.isEmpty()->{
                                binding.containerEmail.helperText ="Preencha o email!"
                                binding.containerEmail.boxStrokeColor = Color.parseColor("#FF9800")
                            }

                            senha.isEmpty()->{
                                binding.containerSenha.helperText ="Preencha a senha!"
                                binding.containerSenha.boxStrokeColor = Color.parseColor("#FF9800")


                            }

                    else ->{
                        autenticacao(email, senha)

                    }

                }

        }





        binding.btTelaCadastro.setOnClickListener {

            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)

        }

    }

    private fun autenticacao(email:String, senha:String){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener { autenticacao ->
            if (autenticacao.isSuccessful){
                Toast.makeText(this,"Login efetuado com sucesso!", Toast.LENGTH_SHORT).show()
                navegarTelaPrincipal()
            }
        }.addOnFailureListener {

            Toast.makeText(this,"Erro ao Efetuar Login!", Toast.LENGTH_SHORT).show()


        }

    }

    private fun navegarTelaPrincipal(){
        val intent = Intent(this, PrincipalActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual!=null){
            navegarTelaPrincipal()
        }
    }
}