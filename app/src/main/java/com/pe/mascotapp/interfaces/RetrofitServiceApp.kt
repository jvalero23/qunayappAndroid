package com.pe.mascotapp.interfaces

import com.pe.mascotapp.modelos.request.NegocioSede
import com.pe.mascotapp.modelos.request.SesionUsuario
import com.pe.mascotapp.modelos.request.RegisterUserRequest
import com.pe.mascotapp.modelos.request.SesionUsuarioLogin
import com.pe.mascotapp.utils.Constantes
import com.pe.mascotapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitServiceApp {

    // Instancia de Retrofit
    /*fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constantes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }*/


    private val retrofit = Retrofit.Builder()
        .baseUrl(Constantes.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getLoginUser(correo:String, encriptado:String, callback: (SesionUsuarioLogin?) -> Unit) {
        retrofit.create(Servicios::class.java).getLoginUser(correo,encriptado).enqueue(object :
            Callback<SesionUsuarioLogin> {
            override fun onResponse(call: Call<SesionUsuarioLogin>, response: Response<SesionUsuarioLogin>) {
                // Procesar respuesta exitosa
                Utils.dump(response.body().toString())
                callback(response.body())
            }

            override fun onFailure(call: Call<SesionUsuarioLogin>, t: Throwable) {
                // Procesar error en la petición
                Utils.dump(t.message.toString())
                callback(null)
            }
        })
    }

    fun postRegisterUser(request: RegisterUserRequest, callback: (SesionUsuario?) -> Unit) {
        retrofit.create(Servicios::class.java)
            .postRegisterUser(request)
            .enqueue(object : Callback<SesionUsuario> {
                override fun onResponse(call: Call<SesionUsuario>, response: Response<SesionUsuario>) {
                    Utils.dump(response.body().toString())
                    if (response.isSuccessful) {
                        callback(response.body())
                    }else{
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<SesionUsuario>, t: Throwable) {
                    Utils.dump("Error: ${t.message}")
                    callback(null)
                }
            })
    }

    /*fun getServicios(request: RegisterUserRequest, callback: (SesionUsuario?) -> Unit) {
        retrofit.create(Servicios::class.java)
            .postRegisterUser(request)
            .enqueue(object : Callback<SesionUsuario> {
                override fun onResponse(call: Call<SesionUsuario>, response: Response<SesionUsuario>) {
                    Utils.dump(response.body().toString())
                    if (response.isSuccessful) {
                        callback(response.body())
                    }else{
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<SesionUsuario>, t: Throwable) {
                    Utils.dump("Error: ${t.message}")
                    callback(null)
                }
            })
    }*/

    fun getNegocios(callback: (ArrayList<NegocioSede>?) -> Unit) {
        retrofit.create(Servicios::class.java)
            .getListNegocios()
            .enqueue(object : Callback<ArrayList<NegocioSede>> {
                override fun onResponse(call: Call<ArrayList<NegocioSede>>, response: Response<ArrayList<NegocioSede>>) {
                    Utils.dump(response.body().toString())
                    if (response.isSuccessful) {
                        callback(response.body())
                    }else{
                        callback(null)
                    }
                }

                override fun onFailure(call: Call<ArrayList<NegocioSede>>, t: Throwable) {
                    Utils.dump("Error: ${t.message}")
                    callback(null)
                }
            })
    }
}