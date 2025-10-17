package com.aulaestudio.crud_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Recibe la lista de usuarios. La hacemos 'var' y no 'val' para poder actualizarla.
class UserAdapter(private var usuarios: List<Usuario>) :
    RecyclerView.Adapter<UserAdapter.UsuarioViewHolder>() {

    // Clase interna que contiene las referencias a las vistas de un solo ítem.
    inner class UsuarioViewHolder(vista: View) : RecyclerView.ViewHolder(vista) {
        val nombre_pj: TextView = vista.findViewById(R.id.nombre)
        val rango_pj: TextView = vista.findViewById(R.id.rango)
        val region_pj: TextView = vista.findViewById(R.id.region)
        val via_pj: TextView = vista.findViewById(R.id.via)
    }

    // Crea nuevos ViewHolders (infla el layout item_usuario.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(vista)
    }

    // Reemplaza el contenido de las vistas con los datos del usuario en la posición dada.
    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.nombre_pj.text = "${usuario.nombre}"
        holder.rango_pj.text = "${usuario.rango}"
        holder.region_pj.text = "${usuario.region}"
        holder.via_pj.text = "${usuario.via_principal}"
    }

    // Retorna el tamaño de la lista de usuarios
    override fun getItemCount(): Int = usuarios.size

    // Función para actualizar la lista de datos y refrescar la vista.
    fun actualizarDatos(nuevosUsuarios: List<Usuario>) {
        usuarios = nuevosUsuarios
        notifyDataSetChanged() // Notifica al RecyclerView que los datos han cambiado
    }

}