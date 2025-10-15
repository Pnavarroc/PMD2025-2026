package org.iesch.a03_menu_principal.apirazas.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.iesch.a03_menu_principal.databinding.ItemDogBinding

//Esta clase recibirá la vista que vamos a pintar
class DogViewHolder (view: View): RecyclerView.ViewHolder(view){
    //Creamos el mét odo que recibirá una imagen por cada celda que tenemos que pintar

    private val binding = ItemDogBinding.bind(view)
    fun render(imagen : String){
        //A traves de la libreria Picasso mostraremos la imagen a partir de la url es decir, pinta la vista
        Picasso.get().load(imagen).into(binding.ivDog)


    }
}