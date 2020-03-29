package com.getstarted.errorapp.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.getstarted.errorapp.R
import com.getstarted.errorapp.data.UsuarioModel
import kotlinx.android.synthetic.main.itemlista.view.*

class PrincipalAdapter(private val context:Context):RecyclerView.Adapter<PrincipalAdapter.PrincipalViewHolder>() {
//todo #2 para lista
    //Sobreescritos

    //#3 setamos los datos
    private var dataList= mutableListOf<UsuarioModel>()

    fun setListData(data: MutableList<UsuarioModel>){
        dataList=data
    }
    //#1 se configura esto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrincipalViewHolder {
        val view: View =LayoutInflater.from(context).inflate(R.layout.itemlista,parent,false)
        return PrincipalViewHolder(view)
    }
   //todo #6 paso final para msotrar datos en la vista
    override fun getItemCount(): Int {
       return if (dataList.size>0){
           dataList.size
       }else{
           0
       }
    }
    //#4 configuramos la posicion de los elementos
    override fun onBindViewHolder(holder: PrincipalViewHolder, position: Int) {
        val UsuarioM:UsuarioModel=dataList[position]
        holder.bindView(UsuarioM)
    }

    //Metodo personal
    //#2 se cargan los datos
    inner class PrincipalViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
          fun bindView(userModel: UsuarioModel){
              //cargar imagen
             Glide.with(context).load(userModel.imgUrl).into(itemView.circleImageView)
              //cargar texto
              itemView.txtNombreLista.text=userModel.nombre
              itemView.txtDescripcionLista.text=userModel.descripcion
          }
    }
}