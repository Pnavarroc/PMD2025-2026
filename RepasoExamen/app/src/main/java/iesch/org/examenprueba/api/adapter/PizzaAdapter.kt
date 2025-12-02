package iesch.org.examenprueba.api.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import iesch.org.examenprueba.api.model.Pizza
import iesch.org.examenprueba.databinding.ItemPizzaBinding

class PizzaAdapter(val lista: List<Pizza>, val onClick: (Pizza)-> Unit):
    RecyclerView.Adapter<PizzaViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PizzaViewHolder {
        val binding = ItemPizzaBinding.inflate(
            LayoutInflater.from(
                parent.context),
            parent,
            false
        )
        return PizzaViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: PizzaViewHolder,
        position: Int
    ) {
        holder.render(lista[position], onClick)
    }

    override fun getItemCount(): Int {
        return lista.size
    }
}