package org.iesch.challengepablonavarrocamacho

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import org.iesch.challengepablonavarrocamacho.databinding.ActivityActividadesDetectadasBinding
import org.iesch.challengepablonavarrocamacho.model.LabelAdapter

class ActividadesDetectadasActivity : AppCompatActivity() {
    companion object{
        const val FOTO_KEY= "foto"
    }
    lateinit var binding: ActivityActividadesDetectadasBinding

    // 1. Creamos una lista para guardar los resultados (String)
    private val listaResultados = mutableListOf<String>()
    // 2.adaptador sencillo (lo definimos abajo)
    private lateinit var adapter: LabelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityActividadesDetectadasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = LabelAdapter(listaResultados)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val bundle=intent.extras!!
        if (bundle == null) {
            Toast.makeText(this, "No se recibieron datos", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val bitmapDirectory = bundle.getString(FOTO_KEY)
        val bitmap= BitmapFactory.decodeFile(bitmapDirectory)
        if ( bitmap!=null ){
            binding.imageView.setImageBitmap(bitmap)
        }


        // 3. Programar el botón de analizar
        binding.button2.setOnClickListener {
            analizarImagen(bitmap)
        }

    }
    private fun analizarImagen(bitmap: android.graphics.Bitmap) {

        //Procesamos la imagen para poder trabajar con ella
        val imagen = InputImage.fromBitmap(bitmap, 0)
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        labeler.process(imagen)
            .addOnSuccessListener { labels ->
                listaResultados.clear()
                for (label in labels) {
                    val text = label.text
                    val confidence = label.confidence*100
                    val index = label.index
                    listaResultados.add("$text :Porcentaje de acierto ${confidence.toInt()} %")
                }
                adapter.notifyDataSetChanged() // Refrescar la lista en pantalla

                if (labels.isEmpty()) {
                    Toast.makeText(this, "No se detectó nada", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al analizar: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}