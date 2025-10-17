package com.aulaestudio.crud_android
import android.annotation.SuppressLint
import android.graphics.Region
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity


class PaginaCrear : AppCompatActivity() {

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
        val rangosOpciones=arrayOf("4 estrellas", "5 Estrellas")

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
            "Estación Espacial Herta",
            "Jarilo-VI (Belobog)",
            "Xianzhou Luofu",
            "Penacony",
            "Colonipen", // Mencionado en el juego, parte de Penacony
        )

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
        val lista_vias = arrayOf(
            "Cacería",
            "Erudición",
            "Destrucción",
            "Conservación",
            "Abundancia",
            "Armonía",
            "Nihilidad",
            "Reminiscencia"
        )

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
    }
}