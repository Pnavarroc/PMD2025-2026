package iesch.org.examenprueba.api.adapter


import androidx.recyclerview.widget.RecyclerView
import iesch.org.examenprueba.api.model.Pizza
import iesch.org.examenprueba.databinding.ItemPizzaBinding

class PizzaViewHolder(val binding: ItemPizzaBinding)
    : RecyclerView.ViewHolder(binding.root) {

    fun render(pizza: Pizza, onClick: (Pizza) -> Unit) {

        binding.tvNombrePizza.text = pizza.name
        binding.tvCategoria.text = pizza.category
        binding.tvPrecio.text = "Precio: ${pizza.price}€"

        // Ranking a estrellas
        val estrellas = "*".repeat(pizza.rank ?: 0)
        binding.tvRanking.text = estrellas

        // Toppings: lista → string
        val toppingsTexto = if (pizza.topping != null)
            pizza.topping.joinToString(", ")
        else
            "Sin toppings"

        binding.tvToppings.text = toppingsTexto

        binding.root.setOnClickListener {
            onClick(pizza)
        }
    }
}
