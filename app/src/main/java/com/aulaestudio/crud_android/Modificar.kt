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
        //
        botonActualizar = findViewById(R.id.botonModificar)
        botonVolver = findViewById(R.id.volver)

        // Configurar las opciones de los opctions
        valoresOpciones()

        // Recibir los datos del Intent
        val id = intent.getStringExtra("_id")
        val nombre = intent.getStringExtra("nombre")
        val rango = intent.getStringExtra("rango")
        val region = intent.getStringExtra("region")
        val via = intent.getStringExtra("via_principal")

        Log.d("Datos recibidos modificar", "${nombre} ${rango} ${region} ${via}")

        // Mostrar los datos en la pantalla.
        nombreModificar.setText(nombre)
        setSpinnerSelection(rangoModificar, rango)
        setSpinnerSelection(regionModificar, region)
        setSpinnerSelection(viaModificar, via)

        // Botón para volver
        botonVolver.setOnClickListener {
            finish()
        }



        botonActualizar.setOnClickListener {
            val idTargeta= id.toString()
            val nuevoNombre = nombreModificar.text.toString()
            val nuevoRango = rangoModificar.selectedItem.toString()
            val nuevaRegion = regionModificar.selectedItem.toString()
            val nuevaVia = viaModificar.selectedItem.toString()
            Log.d("datos a modificar", "id:${id} nombrenuevo: ${nuevoNombre} , nuevaCalidad ${nuevoRango}  NUEVAREGION:${nuevaRegion} NUEVA VIA: ${nuevaVia}" )
            //Toast.makeText(this, "Actualizado $nuevoNombre", Toast.LENGTH_SHORT).show()

            modificarTargeta(idTargeta,nuevoNombre,nuevoRango,nuevaRegion,nuevaVia);

        }

    }


    private fun valoresOpciones() {
        //
        val opcionesCalidad = arrayOf("4 estrellas", "5 estrellas")
        val rangoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opcionesCalidad)
        rangoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        rangoModificar.adapter = rangoAdapter

        // Opciones para Región
        val listaRegiones = arrayOf(
            "Herta Space Station",
            "Jarilo-VI",
            "The Xianzhou Luofu",
            "Penacony",
            "Amphoreus",)


        val regionAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaRegiones)
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        regionModificar.adapter = regionAdapter

        // Opciones para Vía
        val listaVias = arrayOf("Cacería",
            "Erudición",
            "Destrucción",
            "Conservación",
            "Abundancia",
            "Armonía",
            "Nihilidad",
            "Reminiscencia")
        val viaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaVias)
        viaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        viaModificar.adapter = viaAdapter
    }


    private fun setSpinnerSelection(spinner: Spinner, value: String?) {
        if (value == null) return
        val adapter = spinner.adapter as ArrayAdapter<String>
        // Buscar posición ignorando mayúsculas/minúsculas
        val position = (0 until adapter.count).firstOrNull {
            adapter.getItem(it)?.equals(value, ignoreCase = true) == true
        } ?: 0  // si no encuentra, seleccionar 0
        spinner.setSelection(position)
    }




    private fun modificarTargeta(id: String, nombre: String, rango: String, region: String, via_principal: String) {
        val targetaModi = datosModificar(id, nombre, rango, region,via_principal)
        Log.d("TARJETA PARA ENVIAR DATOS A MODIFICAR", "Enviando JSON: $targetaModi")
        RetrofitClient.instance.modificarTargeta(id, targetaModi).enqueue(object : retrofit2.Callback<datosModificar> {
            override fun onResponse(call: retrofit2.Call<datosModificar>, response: retrofit2.Response<datosModificar>) {
                Toast.makeText(
                    this@Modificar,
                    if (response.isSuccessful) " Targeta modificada" else " NONONONONO (${System.out.println(response.code())})",
                    Toast.LENGTH_SHORT
                ).show()
                if (response.isSuccessful) finish()
            }

            override fun onFailure(call: retrofit2.Call<datosModificar>, t: Throwable) {
                Toast.makeText(this@Modificar, " NONONONONONOCREA: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}