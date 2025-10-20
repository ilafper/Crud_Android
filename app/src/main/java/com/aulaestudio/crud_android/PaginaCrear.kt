package com.aulaestudio.crud_android
import android.annotation.SuppressLint
import android.graphics.Region
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log


class PaginaCrear : AppCompatActivity() {
    private val TAG = "CREAR_PERSONAJE_LOG"

    // La inicialización debe ir en el metodo onCreate()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Asigna el layout XML de esta Activity
        setContentView(R.layout.crear_targeta)
//--------------OPCIONES DE RANGO--------------------------------------------------------
        //llamar al campo de las opciones del rango.
        val CampoRango = findViewById<Spinner>(R.id.RangoCrear)
        //array con las opciones a mostrar en el formu
        val rangosOpciones=arrayOf("4 estrellas", "5 estrellas")

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            rangosOpciones
        )

        //estilo por defecto de android studio para las opciones
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //basicamente le dice que use las opciones que definimos
        CampoRango.adapter = adaptador


        //--------------OPCIONES DE RANGO--------------------------------------------------------
        //llamar al campo de las opciones del rango.
        val Region_campo = findViewById<Spinner>(R.id.RegionCrear)
        //array con las opciones a mostrar en el formu
        val lista_Regiones = arrayOf(
            "Herta Space Station",
            "Jarilo-VI",
            "The Xianzhou Luofu",
            "Penacony",
            "Amphoreus",)

        val adaptador2 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            lista_Regiones
        )

        //estilo por defecto de android studio para las opciones
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //basicamente le dice que use las opciones que definimos
        Region_campo.adapter = adaptador2

        //--------------VIA CAMPOS --------------------------------------------------------

        val via_campo = findViewById<Spinner>(R.id.viaCrear)
        //array con las opciones a mostrar en el formu
        val lista_vias = arrayOf("Cacería",
            "Erudición",
            "Destrucción",
            "Conservación",
            "Abundancia",
            "Armonía",
            "Nihilidad",
            "Reminiscencia")

        val adaptador3 = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            lista_vias
        )

        //estilo por defecto de android studio para las opciones
        adaptador3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //basicamente le dice que use las opciones que definimos
        via_campo.adapter = adaptador3


        // 1. Enlazar el botón 'volver' usando su ID del XML
        val botonVolver = findViewById<Button>(R.id.volver)

        // boton de volver, cierra la vista actual y devuelve la anterior.
        botonVolver.setOnClickListener {
            // El metodo finish() cierra la Activity actual y
            // automáticamente regresa a la Activity anterior en la pila (MainActivity).
            finish()
        }

        // obtener el boton que le das para crear un nuevo pj

        val crearBoton= findViewById<Button>(R.id.CrearNuevo)
        //obtener el campo del nombre

        val nuevoNombreTargeta = findViewById<EditText>(R.id.nombreCrear)

        //la configuracion una vez que le haces "click"
        crearBoton.setOnClickListener {
            val nombre = nuevoNombreTargeta.text.toString()
            val rango =  CampoRango.selectedItem.toString()
            val region = Region_campo.selectedItem.toString()
            val via_principal= via_campo.selectedItem.toString()


            //usar log tag que es como el console log

            Log.d(TAG, "Nombre: $nombre")
            Log.d(TAG, "Region: $rango")
            Log.d(TAG, "Region: $region")
            Log.d(TAG, "via_principal: $via_principal")

            guardarTarjeta(nombre, rango, region, via_principal)
        }

    }


    private fun guardarTarjeta(nombre: String, rango: String, region: String, via_principal: String) {
        val tarjeta = TarjetaCreacion(nombre, rango, region, via_principal)
        Log.d(TAG, "Enviando JSON: $tarjeta")
        RetrofitClient.instance.crearTarjeta(tarjeta).enqueue(object : retrofit2.Callback<TarjetaCreacion> {
            override fun onResponse(call: retrofit2.Call<TarjetaCreacion>, response: retrofit2.Response<TarjetaCreacion>) {
                Toast.makeText(
                    this@PaginaCrear,
                    if (response.isSuccessful) " Tarjeta creada" else " NONONONONO (${response.code()})",
                    Toast.LENGTH_SHORT
                ).show()
                if (response.isSuccessful) finish()
            }

            override fun onFailure(call: retrofit2.Call<TarjetaCreacion>, t: Throwable) {
                Toast.makeText(this@PaginaCrear, " NONONONONONOCREA: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}




