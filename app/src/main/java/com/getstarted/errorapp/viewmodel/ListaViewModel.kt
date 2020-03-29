package com.getstarted.errorapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getstarted.errorapp.data.ListaRepo
import com.getstarted.errorapp.data.UsuarioModel

class ListaViewModel:ViewModel() {

    private val listaRepo=ListaRepo()

    fun fetchUserData():LiveData<MutableList<UsuarioModel>>{
           val mutableData=MutableLiveData<MutableList<UsuarioModel>>()
           listaRepo.getUserData().observeForever {userList->
               mutableData.value=userList
           }
        return mutableData
    }
}