package com.pe.mascotapp.modelos.request
import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class NegocioSede(
    @SerializedName("id_negocio") val idNegocio: Int,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("fechaRegistro") val fechaRegistro: String,
    @SerializedName("nombre_comercial") val nombreComercial: String,
    @SerializedName("tipo_id") val tipoId: Int,
    @SerializedName("identificacion") val identificacion: String,
    @SerializedName("razonSocial") val razonSocial: String,
    @SerializedName("sedes") val sedes: ArrayList<Sede>
) : Serializable
