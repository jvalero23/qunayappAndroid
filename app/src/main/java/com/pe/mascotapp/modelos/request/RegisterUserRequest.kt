package com.pe.mascotapp.modelos.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RegisterUserRequest(
    @SerializedName("usuario") val usuario: UsuarioAPI,
    @SerializedName("credenciales") val credenciales: Credenciales,
    @SerializedName("mascota") val mascota: List<Mascota>
) : Serializable