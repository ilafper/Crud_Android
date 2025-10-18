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
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    //
    private lateinit var adaptadorUsuarios: UserAdapter
    // funcion principal, se encarga de cargar la vista(html) que en nuestro caso es el activity.xml
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //que ocupe todo
        enableEdgeToEdge()

        // Botón para crear nueva tarjeta y redirigir a una nueva vista html
        findViewById<Button>(R.id.botonCrear).setOnClickListener {
            startActivity(Intent(this, PaginaCrear::class.java))
        }

        // mesaje de alerta para confirmar borrar la tarjeta
        adaptadorUsuarios = UserAdapter(emptyList()) { usuario ->
            // Abrir diálogo de confirmación
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Confirmar eliminación")
                .setMessage("¿Deseas borrar la tarjeta de ${usuario.nombre}?")

                .setPositiveButton("Sí") { dialog, _ ->
                    borrarTarjeta(usuario._id!!)
                    dialog.dismiss()
                }
                .setNegativeButton("No") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        // buscar el contenedor en donde iran las targetas.
        findViewById<RecyclerView>(R.id.listaDeUsuarios).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = adaptadorUsuarios
        }

        // Cargar usuarios iniciales
        cargarUsuarios()
    }


    override fun onResume() {
        super.onResume()
        cargarUsuarios() // recarga lista al volver de PaginaCrear
    }

    //cargar los usuarios
    private fun cargarUsuarios() {

        RetrofitClient.instance.getUsuarios().enqueue(object : Callback<List<Usuario>> {
            override fun onResponse(call: Call<List<Usuario>>, response: Response<List<Usuario>>) {
                //meter la respuesta del la pai en una variable
                val usuarios = response.body().orEmpty()

                //mostrar todos los usuarios en el log
                for (usuario in usuarios) {
                    Log.d(
                        "Carga de usuarios",
                        "id=${usuario._id}, Nombre=${usuario.nombre}, Rango=${usuario.rango}, Región=${usuario.region}, Vía=${usuario.via_principal}"
                    )
                }


                if (usuarios.isNotEmpty()) adaptadorUsuarios.actualizarDatos(usuarios)
                else Toast.makeText(this@MainActivity, "No se encontraron usuarios.", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<List<Usuario>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error de red: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun borrarTarjeta(id: String) {
        RetrofitClient.instance.borrarTargeta(id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Tarjeta eliminada", Toast.LENGTH_SHORT).show()
                    cargarUsuarios() // refresca la lista
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error de red: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }






}
