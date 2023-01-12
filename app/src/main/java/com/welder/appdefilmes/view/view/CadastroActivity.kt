package com.welder.appdefilmes.view.view

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.welder.appdefilmes.databinding.ActivityCadastroBinding

class CadastroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        


        binding.btVamosla.setOnClickListener {
            val email = binding.EmailCadastro.text.toString()

            if (!email.isEmpty()){
               binding.layoutSenha.visibility = View.VISIBLE
                binding.btVamosla.visibility = View.GONE
                binding.btContinuar.visibility =View.VISIBLE
                binding.texTitulo.setText("Revivendo Emoções do mundo do futebol")
                binding.textDescricao.setText("Crie uma conta para saber mais sobre nosso App ")
                binding.layoutEmail.helperText=""
                binding.containerheader.visibility = View.VISIBLE
            }else{
                binding.layoutEmail.helperText ="O email é obrigatório!"
                binding.layoutEmail.boxStrokeColor= Color.parseColor("#FF0000")
            }
        }

        binding.btContinuar.setOnClickListener {

            val  email = binding.EmailCadastro.text.toString()
            val senha = binding.SenhaCadastro.text.toString()

            if (!email.isEmpty() && !senha.isEmpty()){
                cadastro(email, senha)

            }else if (senha.isEmpty()){

                binding.layoutSenha.helperText ="A senha é obrigatória!"
                binding.layoutSenha.boxStrokeColor= Color.parseColor("#FF0000")

            }else{

            binding.layoutEmail.helperText ="O email é obrigatório!"
            binding.layoutEmail.boxStrokeColor= Color.parseColor("#FF0000")
        }


            }
        }

    private fun cadastro(email: String, senha:String){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener { cadastro ->
            if (cadastro.isSuccessful){
                Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show()
                binding.layoutEmail.helperText=""
                binding.layoutSenha.helperText=""
            }
        }.addOnFailureListener {

            val erro =  it

            when{
                erro is FirebaseAuthWeakPasswordException ->{
                    binding.layoutSenha.helperText ="Digite uma Senha com no mínimo 6 caracteres!"
                }

                erro is FirebaseAuthUserCollisionException->{
                    binding.layoutEmail.helperText ="E-mail já foi cadastrado!"
                }
                erro is FirebaseNetworkException ->{
                    binding.layoutSenha.helperText = "Sem conexão com a internet!"
                }else ->{
                    Toast.makeText(this,"Erro ao cadastrar usuário!", Toast.LENGTH_SHORT).show()
                }
            }
        }



    }
    }
