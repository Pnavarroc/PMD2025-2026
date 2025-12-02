package iesch.org.practica06.theme

import android.graphics.Color
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig

object ThemeManager {

    fun getColor(key: String): Int {
        val remoteConfig = Firebase.remoteConfig
        val colorHex = remoteConfig.getString(key)

        return try {
            Color.parseColor(colorHex)
        } catch (e: Exception) {
            Log.e("THEME", "Color inválido: $colorHex")
            Color.WHITE
        }
    }
}
