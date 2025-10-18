package com.aulaestudio.crud_android

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
    }

    override fun getItemCount() = usuarios.size

    fun actualizarDatos(nuevos: List<Usuario>) {
        usuarios = nuevos
        notifyDataSetChanged()
    }
}
