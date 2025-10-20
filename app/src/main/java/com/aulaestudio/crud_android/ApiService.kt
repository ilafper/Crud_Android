package com.aulaestudio.crud_android

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("usuarios")
    fun getUsuarios(): Call<List<Usuario>>

    @POST("crear")
    fun crearTarjeta(
        @Body tarjeta: TarjetaCreacion
    ): Call<TarjetaCreacion>

    @DELETE("usuarios/{id}")
    fun borrarTargeta (@Path("id") id: String): Call<Void>


    @PUT("modificar/{id}")
    fun modificarTargeta(
        @Path("id") id: String,
        @Body targetaModi: datosModificar
    ): Call<datosModificar>


}
