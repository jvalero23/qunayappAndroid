package com.pe.mascotapp.modelos.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SesionUsuarioLogin(
    @SerializedName("usuario") val usuario: UsuarioAPI? = null,
    @SerializedName("mascota") val mascota: List<Mascota>? = null,
    @SerializedName("status") val status: Int? = null,
    @SerializedName("message") val message: String? = null
) : Serializable
