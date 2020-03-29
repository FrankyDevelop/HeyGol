package com.getstarted.errorapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    //TODO lo de la documentacion esta dentro de ste archivo https://firebase.google.com/docs/auth/android/firebaseui

    //TODO RC_SIGN_IN

    companion object{
        private const val RC_SIGN_IN=426
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login()
    }

    //Login general
    fun login(){

        //Proveedores (estan en la documentacion
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build())

        //Accion del boton
        btnLogin.setOnClickListener {
            //utiliza el metodo startActivityResult (esta parte es de la documentacion
            startActivityForResult(
                AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setAvailableProviders(providers)
                    .setIsSmartLockEnabled(false)
                    .build(),
                RC_SIGN_IN)
        }
    }

    //docuemtnacion
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            //si se loguea correctamente entra la Welcome
            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                val user = FirebaseAuth.getInstance().currentUser
                Toast.makeText(this,"Benvenido ${user!!.displayName}", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,Principal::class.java))
                finish()
            } else {
                //sino se queda ahi y muestra un mensaje de error
                Toast.makeText(this,"Ocurrio un error ${response!!.error!!.errorCode}", Toast.LENGTH_SHORT).show()
            }
        }

    }

}
