package com.getstarted.errorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.getstarted.errorapp.viewmodel.FIrebaseViewModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_principal.*

class Principal : AppCompatActivity() {

    private lateinit var viewModel: FIrebaseViewModel

    //Instancia FIrestore
    val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        btnperfil.setOnClickListener {
            startActivity(Intent(this,Perfil::class.java))
        }

        viewModel=ViewModelProvider(this).get(FIrebaseViewModel::class.java)

        btnAddProject.setOnClickListener {
            crearUsuario()
        }

        btnDatos.setOnClickListener {
            obtnerUsuaruio()
        }
        btnLista.setOnClickListener {
            startActivity(Intent(this,Lista::class.java))
        }

    }

    //todo pasos para guardar #4

    //Crear usuario
    fun crearUsuario(){
        val nombre:String=edtNombre.text.toString().trim()
        val descripcion:String =edtDescripcion.text.toString().trim()

        if(nombre.isNotEmpty() && descripcion.isNotEmpty()){
            viewModel.crearUsuario(nombre,descripcion)
            Toast.makeText(this,"Usuario guardado exitosamente",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"No se pudo guardar",Toast.LENGTH_SHORT).show()
        }
    }

    //Obtener datos de usuario (En tiempo real)
    fun obtnerUsuaruio(){
        db.collection("Usuarios").document("Franky").get()
            .addOnSuccessListener { documento ->
                if(documento.exists()){
                    val nombre:String?=documento.getString("Nombre")
                    txtTitulo.text=nombre

                }else{
                    Toast.makeText(this,"Nell prro",Toast.LENGTH_SHORT).show()
                }
            }
    }

    /*Obtener datos del usuario


    fun obtnerUsuaruio(){
        db.collection("Usuarios").document("Franky").addSnapshotListener { Snap, err ->
            if(err!=null){
                Toast.makeText(this,"Valio verga wey :'v",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            if(Snap !=null && Snap.exists()){
            val nombre:String?=Snap?.getString("Nombre")
            txtTitulo.text=nombre
            }
            else{
                Toast.makeText(this,"Es neta wey? :'v",Toast.LENGTH_SHORT).show()
            }
        }
    }
    */
}

