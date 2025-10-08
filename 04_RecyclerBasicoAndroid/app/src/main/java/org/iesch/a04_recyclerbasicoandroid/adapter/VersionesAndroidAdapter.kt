package org.iesch.a04_recyclerbasicoandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.R
import android.widget.TextView
import android.widget.Toast

/*
Creamos el adaptador:
El adaptador es el puente entre nuestros datos y el RecyclerView.
Se encarga de:
    1- Crear las vistas para cada elemento. - onCreateViewHolder
    2- Enlazar los datos con las vistas. - onBindViewHolder
    3- Saber cuantos elementos hay. - getItemCount
   */

class VersionesAndroidAdapter(val listaVersiones: List<String>) : RecyclerView.Adapter<AndroidVersionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AndroidVersionViewHolder {
        //Crea una nueva vista , cuando es necesario.
        //Infla el layout para cada item.
        val layoutInflater= LayoutInflater.from(parent.context);
        return AndroidVersionViewHolder(layoutInflater.inflate(R.layout.simple_list_item_1,parent,false));
    }

    override fun onBindViewHolder(
        holder: AndroidVersionViewHolder,
        position: Int
    ) {
        //Este es el metodo que pinta los atributos.
        val nombre_version = listaVersiones[position];
        holder.render(nombre_version);
    }

    //Este metodo devuelve el numero total de elementos
    override fun getItemCount(): Int =listaVersiones.size;
}

class AndroidVersionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

//Aqui iría el código para pintar los atributos
//Metodo de conveniencia para usar los datos.
    fun render(version: String){
        itemView.findViewById<TextView>(R.id.text1).text=version;


        //Añadir el Listener para tomar el control
        itemView.setOnClickListener {
            Toast.makeText(itemView.context, version, Toast.LENGTH_LONG).show();

        }
    }




}



















