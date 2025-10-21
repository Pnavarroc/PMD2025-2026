package org.iesch.a07_fragmentos.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.iesch.a07_fragmentos.R
import kotlin.math.log

const val NAME_BUNDLE = "name_bundle"
 const val ADDRESS_BUNDLE = "address_bundle"
class PrimerFragment : Fragment() {
    private var nombre: String? = null
    private var direccion: String? = null
    //Este méto do se llama cuando la vista ha sido cargada
    //Cuando instanciamos el fragment llegamos a onCreate() y aquí le preguntamos si hay algún argumento.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nombre = it.getString(NAME_BUNDLE)
            direccion = it.getString(ADDRESS_BUNDLE)

            Log.i("nombre", nombre.orEmpty())


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_primer, container, false)
    }
    //Los fragments se suelen instanciar a través de un mèt odo publico llamado newInstance
    //Lo que pongamos aqui suele ser los parametros que quiero que reciba.
    companion object {
        @JvmStatic
        fun newInstance(nombre: String, direccion: String) =
            PrimerFragment().apply {
                //cojo el atributo argumentos de este fragment y le paso un tipo bundle.
                arguments = Bundle().apply {
                    putString(NAME_BUNDLE, nombre)
                    putString(ADDRESS_BUNDLE, direccion)
                }
            }
    }
}