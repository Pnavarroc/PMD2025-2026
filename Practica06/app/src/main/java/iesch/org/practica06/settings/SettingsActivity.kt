package iesch.org.practica06.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import iesch.org.practica06.R
import iesch.org.practica06.databinding.ActivitySettingsBinding
import iesch.org.practica06.model.SettingsData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


//Me he importado la libreria , y ahora me creo una función de extension que nos permiten a traves
// de un componente crear méto dos o propiedades adicionales sin necesidad de heredar de la clase original.
//Esta función de extension hereda del Context.
val  Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
// Este delegado nos permite crear una única instancia de la base de datos.
//name es el nombre de la base de datos.

class SettingsActivity : AppCompatActivity() {

   companion object{
       const val VOLUME_LEVEL="volume_level"
       const val KEY_DARKMODE="darkmode_enabled"
       const val KEY_BLUETOOTH="bluetooth_enabled"
       const val KEY_VIBRATION="vibration_enabled"
   }
    private lateinit var binding: ActivitySettingsBinding

    private var firstTime : Boolean=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //LLamo a la funcion que tiene los datos guardados

            CoroutineScope(Dispatchers.IO).launch {
                getSettings().filter { firstTime }.collect { datosAlmacenados->
                    //Actualizar la UI en el hilo principal. No se puede tocar la interfaz desde un hilo secundario
                    CoroutineScope(Dispatchers.Main).launch {
                        binding.swDarkmode.isChecked = datosAlmacenados?.darkMode ?:false
                        binding.swBluethood.isChecked = datosAlmacenados?.bluetoothEnabled ?:false
                        binding.swVibracion.isChecked = datosAlmacenados?.vibrationEnabled ?:false
                        binding.rsVolumen.setValues(datosAlmacenados?.volumen?.toFloat())

                        firstTime =!firstTime
                    }

                }
            }

        initUI()


    }

    private fun initUI() {
        binding.rsVolumen.addOnChangeListener { _,value,_ ->
            //llamamos a guardarVolumen desde una corrutina
            //Log.i("Pablo","Guardando valor de volumen: $value")
            CoroutineScope(Dispatchers.IO).launch {
                saveVolume(value.toInt())
            }
            //Con esto almacenamos el valor

        }

        //Creamos el resto de funciones y variables de key
        binding.swDarkmode.setOnCheckedChangeListener {//El primer parametro es el botón
            _,value ->
            //
            if (value){
                enableDarkMode()
            }else disbleDarkMode()


            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_DARKMODE,value)
        }
        }
        binding.swBluethood.setOnCheckedChangeListener {//El primer parametro es el botón
                _,value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_BLUETOOTH,value)
            }
        }
        binding.swVibracion.setOnCheckedChangeListener {//El primer parametro es el botón
                _,value ->
            CoroutineScope(Dispatchers.IO).launch {
                saveOptions(KEY_VIBRATION,value)
            }
        }
    }


    private suspend fun saveVolume(value: Int){
        //Aquí ira el código para guardar datos en el dataStore.
        //No puede ser llamado desde fuera de una corrutina.
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(VOLUME_LEVEL)]=value
        }
    }

    //Funcion para guardar los checks, le paso el key y el valor
    private suspend fun saveOptions (key: String, value : Boolean ){
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)]=value;
        }

    }

    //Necesito una única función que me va a devolver todos los valores
    private fun getSettings(): Flow<SettingsData?> {
        return dataStore.data.map { preferences ->

            SettingsData(
                preferences[intPreferencesKey(VOLUME_LEVEL)] ?:50,
                preferences[booleanPreferencesKey(KEY_DARKMODE)] ?:false,
                preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?:false,
                preferences[booleanPreferencesKey(KEY_VIBRATION)] ?:false
            )


            //Datastore solo permite devolver un unico valor solucio: Entonces lo que haremos será crear
            // un objeto que encapsule todos los valores que necesitamos
        }
    }

    // Me creo las funciones para cambiar el modo a oscuro o claro
    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES )
        delegate.applyDayNight()
    }

    private fun disbleDarkMode(){
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }
}