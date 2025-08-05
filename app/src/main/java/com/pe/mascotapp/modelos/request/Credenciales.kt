package com.pe.mascotapp.modelos.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Credenciales(
    @SerializedName("encriptado") val encriptado: String
) : Serializable
