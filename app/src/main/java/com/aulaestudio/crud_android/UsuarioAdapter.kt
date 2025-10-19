package com.aulaestudio.crud_android

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Recibe la lista de usuarios. La hacemos 'var' y no 'val' para poder actualizarla.
class UserAdapter(
    private var usuarios: List<Usuario>,
    private val onEliminarClick: (Usuario) -> Unit // callback al pulsar "borrar"

) : RecyclerView.Adapter<UserAdapter.SimpleViewHolder>() {

    class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val nombre: TextView = view.findViewById(R.id.nombre)
        val rango: TextView = view.findViewById(R.id.rango)
        val region: TextView = view.findViewById(R.id.region)
        val via: TextView = view.findViewById(R.id.via)
        val botonEliminar: TextView = view.findViewById(R.id.botonEliminar)
        val botonModificar: TextView = view.findViewById(R.id.modificarTargeta)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SimpleViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_usuario, parent, false))

    override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.nombre.text = usuario.nombre
        holder.rango.text = usuario.rango
        holder.region.text = usuario.region
        holder.via.text = usuario.via_principal

        // Al pulsar el botón de borrar
        holder.botonEliminar.setOnClickListener {
            Log.d("ID A BORRA DE LA TARGETA", "ID a borarr = ${usuario._id}")
            onEliminarClick(usuario)
        }

        //Botón modificar → abrir pantalla ModificarActivity
        holder.botonModificar.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, Modificar::class.java)

            // Enviamos todos los datos al intent
            intent.putExtra("_id", usuario._id)
            intent.putExtra("nombre", usuario.nombre)
            intent.putExtra("rango", usuario.rango)
            intent.putExtra("region", usuario.region)
            intent.putExtra("via_principal", usuario.via_principal)

            Log.d("adadasdasd", "onBindViewHolder: ")
            context.startActivity(intent)
        }






    }

    override fun getItemCount() = usuarios.size

    fun actualizarDatos(nuevos: List<Usuario>) {
        usuarios = nuevos
        notifyDataSetChanged()
    }

}
