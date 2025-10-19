// archivo: Usuario.kt
package com.aulaestudio.crud_android

import android.os.Parcelable



data class Usuario(
    val _id: String?,
    val nombre: String,
    val rango: String,
    val region: String,
    val via_principal: String
)

data class TarjetaCreacion(
    val nombre: String,
    val rango: String,
    val region: String,
    val via: String
)