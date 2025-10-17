package com.aulaestudio.crud_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.Button
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adaptadorUsuarios: UserAdapter
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //indicamos que archivo.xml muestre
        setContentView(R.layout.activity_main)


        //
        val miboton = findViewById<Button>(R.id.botonCrear)
        miboton.setOnClickListener {
            val intent = Intent(this@MainActivity, PaginaCrear::class.java)
            startActivity(intent)
        }

        // Configurar el RecyclerView
        recyclerView = findViewById(R.id.listaDeUsuarios)

        // Inicializar el adaptador con una lista vacía
        adaptadorUsuarios = UserAdapter(emptyList())

        // Asignar el LayoutManager (para que se muestren en formato de lista vertical)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Asignar el adaptador
        recyclerView.adapter = adaptadorUsuarios

        // 2. Iniciar la carga de usuarios
        cargarUsuarios()




    }

    private fun cargarUsuarios() {
        // Usar RetrofitClient para obtener la instancia del servicio y hacer la llamada
        RetrofitClient.instance.getUsuarios().enqueue(object : Callback<List<Usuario>> {

            // Se llama cuando la respuesta llega del servidor (exitosa o con error HTTP)
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                if (response.isSuccessful) {

                    val usuariosRecibidos = response.body()
                    if (usuariosRecibidos != null && usuariosRecibidos.isNotEmpty()) {
                        //mensaje
                        Log.d(TAG, "Usuarios cargados con éxito: ${usuariosRecibidos.size} elementos.")
                        // Actualizar los datos en el adaptador
                        adaptadorUsuarios.actualizarDatos(usuariosRecibidos)
                    } else {
                        Log.w(TAG, "Respuesta exitosa pero la lista de usuarios está vacía.")
                        Toast.makeText(this@MainActivity, "No se encontraron usuarios.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    // La respuesta NO fue 2xx (ej: 404, 500)
                    Log.e(TAG, "Error en la respuesta del servidor: ${response.code()}")
                    Toast.makeText(this@MainActivity, "Error: Código ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            // Se llama cuando ocurre un error de red (sin conexión, timeout, etc.)
            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                Log.e(TAG, "Error de conexión/red: ${t.message}", t)
                Toast.makeText(this@MainActivity, "Error de red: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })

    }



}