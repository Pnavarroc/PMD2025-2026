package org.iesch.a05_recyclerintermedio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.iesch.a05_recyclerintermedio.databinding.ItemAndroidVersionBinding
import org.iesch.a05_recyclerintermedio.modelo.AndroidVersion

//Me creo el adaptador
class AndroidVersionAdapter(val versionesAndroid: List<AndroidVersion>) : RecyclerView.Adapter<AndroidVersionViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AndroidVersionViewHolder {
        //Inflar el layout para cada item
        //Le tengo que decir al item, lo que teng que hacer
        val binding = ItemAndroidVersionBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AndroidVersionViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: AndroidVersionViewHolder,
        position: Int
    ) {
        holder.render(versionesAndroid[position])
    }

    override fun getItemCount(): Int = versionesAndroid.size;

}