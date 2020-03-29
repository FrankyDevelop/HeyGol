package com.getstarted.errorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.getstarted.errorapp.adaptador.PrincipalAdapter
import com.getstarted.errorapp.data.UsuarioModel
import com.getstarted.errorapp.viewmodel.ListaViewModel
import kotlinx.android.synthetic.main.activity_lista.*

class Lista : AppCompatActivity() {
    //esto se hace en el #5
    private lateinit var adapter: PrincipalAdapter

    //Con firebase una vez que esta el viewmodel
   private val viewModel by lazy { ViewModelProvider(this).get(ListaViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
        //se hace en el #5
        adapter= PrincipalAdapter(this)
         //todo #1 para lista
         rcLista.layoutManager=LinearLayoutManager(this)
         //rcLista.adapter=PrincipalAdapter(this)
        //se cambia en el #5
        rcLista.adapter=adapter

        //todo #5 obtener la informacion en la vista
        observeData()
    }

   fun observeData(){
       viewModel.fetchUserData().observe(this, Observer {
           adapter.setListData(it)
           adapter.notifyDataSetChanged()
       })
   }

}
