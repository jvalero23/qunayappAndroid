package com.pe.mascotapp.modelos.request

import com.google.gson.annotations.SerializedName

data class SesionUsuario(
    @SerializedName("usuario") val usuario: UsuarioAPI,
    @SerializedName("credenciales") val credenciales: Credenciales,
    @SerializedName("mascota") val mascotas: List<Mascota>,
    @SerializedName("status") val status: Int,
    @SerializedName("message") val message: String
)
