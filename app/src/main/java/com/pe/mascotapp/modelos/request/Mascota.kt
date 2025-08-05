package com.pe.mascotapp.modelos.request

import com.google.gson.annotations.SerializedName

data class Mascota(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apodo") val apodo: String,
    @SerializedName("id_sexo_mascota") val idSexoMascota: Int,
    @SerializedName("id_identificacion_mascota") val idIdentificacionMascota: Int,
    @SerializedName("numeroidentificacion") val numeroIdentificacion: String,
    @SerializedName("fechanacimiento") val fechaNacimiento: String,
    @SerializedName("fechaadopcion") val fechaAdopcion: String,
    @SerializedName("id_especie") val idEspecie: Int,
    @SerializedName("id_rango_pesos") val idRangoPesos: Int
)