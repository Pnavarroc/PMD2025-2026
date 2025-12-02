package iesch.org.examenprueba.Datastore

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "ajustes_usuario")

class PreferencesManager(private val context: Context) {

    companion object {
        val NOMBRE = stringPreferencesKey("nombre")
        val EDAD = intPreferencesKey("edad")
        val MODO_OSCURO = booleanPreferencesKey("modo_oscuro")
        val NOTIFICACIONES = booleanPreferencesKey("notificaciones")
    }

    // GUARDAR NOMBRE
    suspend fun guardarNombre(nombre: String) {
        context.dataStore.edit { prefs ->
            prefs[NOMBRE] = nombre
        }
    }

    // GUARDAR EDAD
    suspend fun guardarEdad(edad: Int) {
        context.dataStore.edit { prefs ->
            prefs[EDAD] = edad
        }
    }

    // GUARDAR MODO OSCURO
    suspend fun guardarModoOscuro(activado: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[MODO_OSCURO] = activado
        }
    }

    // GUARDAR NOTIFICACIONES
    suspend fun guardarNotificaciones(activado: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[NOTIFICACIONES] = activado
        }
    }

    // LEER TODOS
    val leerDatos: Flow<UserSettings> = context.dataStore.data
        .map { prefs ->
            UserSettings(
                nombre = prefs[NOMBRE] ?: "",
                edad = prefs[EDAD] ?: 0,
                modoOscuro = prefs[MODO_OSCURO] ?: false,
                notificaciones = prefs[NOTIFICACIONES] ?: false
            )
        }
}

data class UserSettings(
    val nombre: String,
    val edad: Int,
    val modoOscuro: Boolean,
    val notificaciones: Boolean
)
