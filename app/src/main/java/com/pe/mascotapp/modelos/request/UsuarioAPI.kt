package com.pe.mascotapp.modelos.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UsuarioAPI(
    @SerializedName("id_usuario") val idUsuario: Int?=null,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("apellidop") val apellidoPaterno: String,
    @SerializedName("apellidom") val apellidoMaterno: String,
    @SerializedName("correo") val correo: String,
    @SerializedName("id_sexo") val idSexo: Int,
    @SerializedName("id_identificacion") val idIdentificacion: Int,
    @SerializedName("numeroidentificacion") val numeroIdentificacion: String,
    @SerializedName("fechanacimiento") val fechaNacimiento: String,
    @SerializedName("id_estado") val idEstado: Int,
    @SerializedName("id_perfil_usuario") val idPerfilUsuario: Int

): Serializable