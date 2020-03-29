package com.getstarted.errorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import coil.api.load
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_perfil.*
import kotlinx.android.synthetic.main.activity_principal.*

class Perfil : AppCompatActivity() {


    lateinit var mAuth: FirebaseAuth
    var mUser: FirebaseUser? = null
    private lateinit var txtNombre: TextView
    private lateinit var txtCorreo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        txtNombre=findViewById(R.id.txtNombre)
        txtCorreo=findViewById(R.id.txtCorreo)
        //imgUsuario=findViewById(R.id.imgUsuario)


        mAuth = FirebaseAuth.getInstance()
        mUser = mAuth.currentUser


        val name: String? = mUser?.displayName
        val email: String? = mUser?.email
        val photoUrl = mUser?.photoUrl

        txtNombre.text = name
        txtCorreo.text = email

        imgperfil.load(photoUrl)

        //Salir de la aplicacion
        Salir()
        //Eliminar Cuenta
        Eliminar()
    }

    //Eliminar Cuenta
    private fun Eliminar() {

        btnEliminar.setOnClickListener {
            AuthUI.getInstance().delete(this).addOnCompleteListener {
                startActivity(Intent(this,Login::class.java))
                Toast.makeText(this,"Tu cuenta fue eliminada",Toast.LENGTH_SHORT).show()

                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"Ocurrio un error ${it.message}",Toast.LENGTH_SHORT).show()
            }
        }

    }

    //logout
    fun Salir(){
        btnCerrarSesion.setOnClickListener {
            AuthUI.getInstance().signOut(this).addOnSuccessListener{
                startActivity(Intent(this,Login::class.java))
                Toast.makeText(this,"Vuelve pronto,te extrañaremos",Toast.LENGTH_SHORT).show()

                finish()
            }.addOnFailureListener {
                Toast.makeText(this,"Ocurrio un error ${it.message}",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
