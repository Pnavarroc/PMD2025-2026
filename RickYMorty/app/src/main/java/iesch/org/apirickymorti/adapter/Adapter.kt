package iesch.org.apirickymorti.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iesch.org.apirickymorti.R
import iesch.org.apirickymorti.databinding.ItemPersonajeBinding

import iesch.org.apirickymorti.model.Character

class Adapter(val lista: List<Character>, val onClick: (Character)-> Unit
): RecyclerView.Adapter<ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemPersonajeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val personaje = lista[position]
        holder.render(personaje,onClick)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}



