package org.iesch.a05_recyclerintermedio

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.iesch.a05_recyclerintermedio.adapter.AndroidVersionAdapter
import org.iesch.a05_recyclerintermedio.databinding.ActivityMainBinding
import org.iesch.a05_recyclerintermedio.modelo.AndroidVersion
import org.iesch.a05_recyclerintermedio.provider.AndroidVersionProvider

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;

    private lateinit var adapter: AndroidVersionAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        //Optenemos la lista de versiones android
        val versionesAndroid= AndroidVersionProvider.androidVersionsList

        // Configuramos el adaptador
        adapter= AndroidVersionAdapter(versionesAndroid)

        // Configurar RecyclerView usando binding
        binding.recyclerviewAndroid.layoutManager= LinearLayoutManager(this)
        binding.recyclerviewAndroid.adapter=adapter



    }
}