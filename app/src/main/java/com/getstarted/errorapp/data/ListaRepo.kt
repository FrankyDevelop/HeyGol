package com.getstarted.errorapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class ListaRepo {

    fun getUserData():LiveData<MutableList<UsuarioModel>>{
        val mutableData=MutableLiveData<MutableList<UsuarioModel>>()

        FirebaseFirestore.getInstance().collection("Proyectos").get().addOnSuccessListener {result->
            val listData= mutableListOf<UsuarioModel>()

            for(document in result){
                val imgUrl:String?=document.getString("imgUrl")
                val nombre:String?=document.getString("nombre")
                val descripcion:String?=document.getString("descripcion")

                val usuario=UsuarioModel(imgUrl!!,nombre!!,descripcion!!)
                listData.add(usuario)
            }
              mutableData.value=listData
        }
        return mutableData
    }

}