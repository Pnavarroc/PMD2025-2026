package org.iesch.a05_recyclerintermedio.adapter

import android.health.connect.datatypes.units.Length
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import org.iesch.a05_recyclerintermedio.databinding.ItemAndroidVersionBinding
import org.iesch.a05_recyclerintermedio.modelo.AndroidVersion

class AndroidVersionViewHolder (val binding: ItemAndroidVersionBinding): RecyclerView.ViewHolder(binding.root){
    //Me configuro el viewHolder

    fun render(androidVersion: AndroidVersion){
        //Asignar los datos a las vistas usando el binding
        binding.tvNombreVersion.text=androidVersion.nombre
        binding.tvCodigoNombre.text=androidVersion.codigo
        binding.tvDetalles.text="API ${androidVersion.apiLevel} - ${androidVersion.anioLanzamiento}"

        itemView.setOnClickListener {
            Toast.makeText(itemView.context ,androidVersion.nombre, Toast.LENGTH_SHORT).show()
        }

    }
}