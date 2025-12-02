package iesch.org.practica06.dataStore


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.usuarioDataStore by preferencesDataStore(name = "usuarioDB")

class UsuarioDataStore(private val context: Context) {

    companion object {
        private val NOMBRE_KEY = stringPreferencesKey("nombre")
        private val EDAD_KEY = stringPreferencesKey("edad")
        private val DARK_KEY = booleanPreferencesKey("modo_oscuro")
        private val NOTI_KEY = booleanPreferencesKey("notificaciones")
    }

    val nombre: Flow<String?> = context.usuarioDataStore.data.map { it[NOMBRE_KEY] }
    val edad: Flow<String?> = context.usuarioDataStore.data.map { it[EDAD_KEY] }
    val modoOscuro: Flow<Boolean> = context.usuarioDataStore.data.map { it[DARK_KEY] ?: false }
    val notificaciones: Flow<Boolean> = context.usuarioDataStore.data.map { it[NOTI_KEY] ?: true }

    suspend fun guardarNombre(nombre: String) {
        context.usuarioDataStore.edit { it[NOMBRE_KEY] = nombre }
    }

    suspend fun guardarEdad(edad: String) {
        context.usuarioDataStore.edit { it[EDAD_KEY] = edad }
    }

    suspend fun guardarModoOscuro(estado: Boolean) {
        context.usuarioDataStore.edit { it[DARK_KEY] = estado }
    }

    suspend fun guardarNotificaciones(estado: Boolean) {
        context.usuarioDataStore.edit { it[NOTI_KEY] = estado }
    }
}
