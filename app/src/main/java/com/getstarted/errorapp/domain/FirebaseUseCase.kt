package com.getstarted.errorapp.domain

import com.getstarted.errorapp.data.FirebaseRepo

class FirebaseUseCase {
    //todo pasos para guardar #2
    val repo= FirebaseRepo()
    //Agregando datos del repositorio
    fun agregarUsuarioFirebase(nombre:String,descripcion:String){
        repo.AgregarUsuario(nombre,descripcion)
    }

}