package iesch.org.practica06.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class SuperHeroe(
    val nombre: String,
    val alterEgo: String,
    val bio: String,
    val power: Float
) : Parcelable;