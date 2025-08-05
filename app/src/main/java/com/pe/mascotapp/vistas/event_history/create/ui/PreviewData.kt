package com.pe.mascotapp.vistas.event_history.create.ui

import android.net.Uri

data class Pet(
    val id: String,
    val name: String,
    val type: String,
    val imageUri: Uri?
)

val sampleDataPet = listOf(
    Pet(
        id = "1",
        name = "Firulais",
        type = "Perro",
        imageUri = Uri.parse("android.resource://com.pe.mascotapp/drawable/perro1")
    ),
    Pet(
        id = "2",
        name = "Rex",
        type = "Perro",
        imageUri = Uri.parse("android.resource://com.pe.mascotapp/drawable/perro1")
    ),
    Pet(
        id = "3",
        name = "Luna",
        type = "Gato",
        imageUri = Uri.parse("android.resource://com.pe.mascotapp/drawable/perro1")
    ),
)