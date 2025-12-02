package iesch.org.practicarformulariosfoto

import android.R
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
//5- Me creo el objeto super heroe y lo hago parcelizable osea se puede serializar.
@Parcelize
data class Persona(
    val nombre: String,
    val apellidos: String,
    val numeroTelefono: String
) : Parcelable