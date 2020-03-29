package com.getstarted.errorapp.viewmodel

import androidx.lifecycle.ViewModel
import com.getstarted.errorapp.domain.FirebaseUseCase
import com.google.firebase.firestore.FirebaseFirestore

class FIrebaseViewModel:ViewModel() {

    //todo pasos para guardar #3
    val FirestoreUseCase= FirebaseUseCase()
     //Enviar datos al activity
    fun crearUsuario(nombre:String,descripcion:String){
        FirestoreUseCase.agregarUsuarioFirebase(nombre,descripcion)
    }

}