package com.pe.mascotapp.modelos.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sede(
    @SerializedName("id_negocio_sede") val idNegocioSede: Int,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("fecharegistro") val fechaRegistro: String,
    @SerializedName("id_ubigeo") val idUbigeo: Int,
    @SerializedName("id_negocio") val idNegocio: Int,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("nombre_sede") val nombreSede: String
) : Serializable