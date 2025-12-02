package iesch.org.examenprueba.api.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pizza(
    val id: Int,
    val category: String,
    val name: String,
    val topping: List<String>?, //Puede que no haya toppings
    val price: Int,
    val rank: Int?
) : Parcelable