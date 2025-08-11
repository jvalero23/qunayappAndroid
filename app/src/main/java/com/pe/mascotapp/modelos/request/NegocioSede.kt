package com.pe.mascotapp.modelos.request
import com.google.gson.annotations.SerializedName

data class NegocioSede(
    @SerializedName("id_negocio") val idNegocio: Int,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("fechaRegistro") val fechaRegistro: String,
    @SerializedName("nombre_comercial") val nombreComercial: String,
    @SerializedName("tipo_id") val tipoId: Int,
    @SerializedName("identificacion") val identificacion: String,
    @SerializedName("razonSocial") val razonSocial: String,
    @SerializedName("id_negocio_sede") val idNegocioSede: Int,
    @SerializedName("fecharegistro") val fechaRegistroSede: String,
    @SerializedName("id_ubigeo") val idUbigeo: Int,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("nombre_sede") val nombreSede: String
)