package com.aulaestudio.crud_android

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Modificar : AppCompatActivity() {
    //cargar las opciones del formualrio
    private lateinit var nombreModificar: EditText
    private lateinit var rangoModificar: Spinner
    private lateinit var regionModificar: Spinner
    private lateinit var viaModificar: Spinner
    private lateinit var botonActualizar: Button
    private lateinit var botonVolver: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modificar)

        nombreModificar = findViewById(R.id.nombreModificar)
        rangoModificar = findViewById(R.id.RangoModificar)
        regionModificar = findViewById(R.id.RegionModificar)
        viaModificar = findViewById(R.id.viaModificar)
        botonActualizar = findViewById(R.id.botonModificar)
        botonVolver = findViewById(R.id.volver)

        // Configurar las opciones de los opctions
        valoresOpciones()

        // 2. Recibir los datos del Intent
        val id = intent.getStringExtra("_id")
        val nombre = intent.getStringExtra("nombre")
        val rango = intent.getStringExtra("rango")
        val region = intent.getStringExtra("region")
        val via = intent.getStringExtra("via_principal")

        Log.d("Datos recibidos modificar", "${nombre} ${rango} ${region} ${via}")

        // Mostrar los datos en la pantalla
        nombreModificar.setText(nombre)
        setSpinnerSelection(rangoModificar, rango)
        setSpinnerSelection(regionModificar, region)
        setSpinnerSelection(viaModificar, via)

        // Botón para volver
        botonVolver.setOnClickListener {
            finish()
        }


        /*
        botonActualizar.setOnClickListener {
            val nuevoNombre = nombreModificar.text.toString()
            val nuevoRango = rangoModificar.selectedItem.toString()
            val nuevaRegion = regionModificar.selectedItem.toString()
            val nuevaVia = viaModificar.selectedItem.toString()

            Toast.makeText(this, "Actualizado $nuevoNombre", Toast.LENGTH_SHORT).show()

            // Aquí puedes llamar a tu API o actualizar en la base de datos usando el ID
        }
        */
    }

    private fun valoresOpciones() {
        // Opciones para Rango
        val opcionesCalidad = arrayOf("4 estrellas", "5 Estrellas")
        val rangoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesCalidad)
        rangoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rangoModificar.adapter = rangoAdapter

        // Opciones para Región
        val listaRegiones = arrayOf("Estación Espacial Herta", "Jarilo-VI (Belobog)", "Xianzhou Luofu", "Penacony", "Amphoreus")
        val regionAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRegiones)
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        regionModificar.adapter = regionAdapter

        // Opciones para Vía
        val listaVias = arrayOf("Cacería", "Erudición", "Destrucción", "Conservación", "Abundancia", "Armonía", "Nihilidad", "Reminiscencia")
        val viaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaVias)
        viaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viaModificar.adapter = viaAdapter
    }

    private fun setSpinnerSelection(spinner: Spinner, value: String?) {
        if (value == null) return
        val adapter = spinner.adapter as ArrayAdapter<String>
        val position = adapter.getPosition(value)
        if (position >= 0) {
            spinner.setSelection(position)
        }
    }

}