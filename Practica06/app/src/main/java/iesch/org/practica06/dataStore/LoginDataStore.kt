package iesch.org.practica06.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val  Context.loginDataStore: DataStore<Preferences> by preferencesDataStore(name = "loginDB")

class LoginDataStore(private  val context: Context) {

    companion object{
        private val EMAIL_KEY = stringPreferencesKey("email_key")
        private val PASSWORD_KEY = stringPreferencesKey("password_key")
        private val LOGGED_KEY = booleanPreferencesKey("logged_key")
    }

    val email: Flow<String?> = context.loginDataStore.data.map { preferences ->
        preferences [EMAIL_KEY] }
    val password: Flow<String?> = context.loginDataStore.data.map { preferences ->
        preferences[PASSWORD_KEY] }
    val isLogged: Flow<Boolean> = context.loginDataStore.data.map { preferences ->
        preferences[LOGGED_KEY] ?: false }


    suspend fun saveLogin(email: String, password: String) {
        context.loginDataStore.edit { preferences ->
            preferences[EMAIL_KEY] = email
            preferences[PASSWORD_KEY] = password
            preferences[LOGGED_KEY] = true
        }

    }

    suspend fun logout() {
        context.loginDataStore.edit { it.clear() }
    }
}