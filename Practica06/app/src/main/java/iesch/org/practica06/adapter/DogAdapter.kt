package iesch.org.practica06.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iesch.org.practica06.adapter.DogViewHolder

import iesch.org.practica06.R


//Nuestro adaptador recibirá una lista de Strings, le tendremos que pasar el ViewHolder
class DogAdapter (val images : List<String>) : RecyclerView.Adapter<DogViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogViewHolder {
        //Aquí tendremos que inflar el layout del item que nos hemos creado para cada respuesta
        val layotInflater= LayoutInflater.from(parent.context)
        return DogViewHolder(layotInflater.inflate(R.layout.item_dog, parent,false))
    }

    override fun onBindViewHolder(
        holder: DogViewHolder,
        position: Int
    ) {
        //- Crearemos el item que será la imagen y la posición que tenga
        //Luego llamamos al holder y le pasams el item
        val item = images[position]
        holder.render(item)
    }

    override fun getItemCount(): Int { //Devolverá el tamaño de la vista que tengamos
        return images.size
    }

}