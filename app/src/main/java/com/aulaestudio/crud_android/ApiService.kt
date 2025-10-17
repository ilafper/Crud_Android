package com.aulaestudio.crud_android

import retrofit2.Call

import retrofit2.http.GET

interface ApiService {
    @GET("usuarios")
    fun getUsuarios(): Call<List<Usuario>>

}
