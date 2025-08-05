package com.pe.mascotapp.interfaces

import com.pe.mascotapp.modelos.request.SesionUsuario
import com.pe.mascotapp.modelos.request.RegisterUserRequest
import com.pe.mascotapp.modelos.request.SesionUsuarioLogin
import retrofit2.Call
import retrofit2.http.*

interface Servicios {

    /* GET ALL */

    @GET("login")
    fun getLoginUser(@Query("correo") correo:String, @Query("encriptado") encriptado:String): Call<SesionUsuarioLogin>

    @POST("registerUserMascota")
    fun postRegisterUser(
        @Body request: RegisterUserRequest
    ): Call<SesionUsuario>}