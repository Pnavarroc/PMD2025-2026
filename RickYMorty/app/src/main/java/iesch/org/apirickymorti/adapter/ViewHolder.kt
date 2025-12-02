package iesch.org.apirickymorti.adapter

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import iesch.org.apirickymorti.databinding.ItemPersonajeBinding
import iesch.org.apirickymorti.model.Character

class ViewHolder(val binding: ItemPersonajeBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(personaje: Character, onClick: (Character) -> Unit) {
        binding.tvNombre.text= personaje.name

        Picasso.get()
            .load(personaje.image)
            .into(binding.ivFoto)

        binding.root.setOnClickListener {
            onClick(personaje)
        }
        }
    }

