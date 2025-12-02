package iesch.org.practicarrecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.R

class ViewHolder (view: View): RecyclerView.ViewHolder(view){

    fun render(version: String){
        itemView.findViewById<TextView>(R.id.text1).text=version
    }
}