package iesch.org.practicarrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.R
import androidx.recyclerview.widget.RecyclerView

class Adapter (val listaVersiones: List<String>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.simple_list_item_1, parent,false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val nombreVersion = listaVersiones[position]
        holder.render(nombreVersion)
    }

    override fun getItemCount(): Int {
        return listaVersiones.size;
    }
}