package com.pe.mascotapp.vistas.event_history.main.ui

import android.net.Uri

data class EventHistoryData(
    val id: String,
    val title: String,
    val description: String,
    val img: Uri? = null,
    val startTime: String,
    val endTime: String,
    val date: String
)

private val sampleImageUri = Uri.parse("android.resource://com.pe.mascotapp/drawable/perro1")

val sampleDataEvent = listOf(
    // Eventos para Lunes, julio 21
    EventHistoryData(
        id = "1",
        title = "Comportamiento",
        description = "Ladró intensamente cuando el cartero se acercó a la puerta, luego se calmó.",
        startTime = "11:35",
        endTime = "12:35",
        date = "Lunes, julio 21",
        img = sampleImageUri
    ),
    EventHistoryData(
        id = "2",
        title = "Comportamiento",
        description = "Estuvo muy juguetón con los niños en el parque.",
        startTime = "14:00",
        endTime = "15:00",
        date = "Lunes, julio 21",
        img = sampleImageUri
    ),
    EventHistoryData(
        id = "3",
        title = "Comportamiento",
        description = "Ladró al ver pasar a otros perros.",
        startTime = "15:30",
        endTime = "16:00",
        date = "Lunes, julio 21",
        img = sampleImageUri
    ),
    EventHistoryData(
        id = "4",
        title = "Comportamiento",
        description = "Se mostró muy amigable con los visitantes.",
        startTime = "17:00",
        endTime = "18:00",
        date = "Lunes, julio 21",
        img = sampleImageUri
    ),

    // Eventos para Martes, julio 22
    EventHistoryData(
        id = "5",
        title = "Comportamiento",
        description = "Se mostró ansioso durante la tormenta.",
        startTime = "09:00",
        endTime = "09:30",
        date = "Martes, julio 22",
        img = sampleImageUri
    ),
    EventHistoryData(
        id = "6",
        title = "Comportamiento",
        description = "Jugó con su juguete favorito durante horas.",
        startTime = "10:00",
        endTime = "11:00",
        date = "Martes, julio 22",
        img = sampleImageUri
    ),

    // Eventos para Miércoles, julio 23
    EventHistoryData(
        id = "7",
        title = "Comportamiento",
        description = "Salió a pasear y conoció a otros perros.",
        startTime = "16:00",
        endTime = "17:00",
        date = "Miércoles, julio 23",
        img = sampleImageUri
    ),
    EventHistoryData(
        id = "8",
        title = "Comportamiento",
        description = "Se quedó mirando la lluvia desde la ventana.",
        startTime = "17:00",
        endTime = "17:30",
        date = "Miércoles, julio 23",
        img = sampleImageUri
    ),

    // Eventos para Jueves, julio 24
    EventHistoryData(
        id = "9",
        title = "Comportamiento",
        description = "Se escondió debajo de la cama cuando oyó truenos.",
        startTime = "08:30",
        endTime = "09:00",
        date = "Jueves, julio 24",
        img = sampleImageUri
    ),

    // Eventos para Viernes, julio 25
    EventHistoryData(
        id = "10",
        title = "Comportamiento",
        description = "Estuvo jugando con su pelota favorita.",
        startTime = "11:30",
        endTime = "12:30",
        date = "Viernes, julio 25",
        img = sampleImageUri
    ),
    EventHistoryData(
        id = "11",
        title = "Comportamiento",
        description = "No quería salir y se escondió debajo de la cama.",
        startTime = "09:30",
        endTime = "10:00",
        date = "Viernes, julio 25",
        img = sampleImageUri
    ),

    // Eventos para Sábado, julio 26
    EventHistoryData(
        id = "12",
        title = "Comportamiento",
        description = "Se echó a dormir en su cama favorita.",
        startTime = "21:00",
        endTime = "22:00",
        date = "Sábado, julio 26",
        img = sampleImageUri
    ),
    EventHistoryData(
        id = "13",
        title = "Comportamiento",
        description = "Descubrió un nuevo camino en el parque.",
        startTime = "16:00",
        endTime = "17:00",
        date = "Sábado, julio 26",
        img = sampleImageUri
    ),

    // Evento para Domingo, julio 27
    EventHistoryData(
        id = "14",
        title = "Comportamiento",
        description = "Se asustó con los fuegos artificiales.",
        startTime = "20:00",
        endTime = "20:30",
        date = "Domingo, julio 27",
        img = sampleImageUri
    ),

    // Evento para Lunes, julio 28
    EventHistoryData(
        id = "15",
        title = "Comportamiento",
        description = "Se mostró muy tranquilo durante la visita al veterinario.",
        startTime = "09:00",
        endTime = "09:30",
        date = "Lunes, julio 28",
        img = sampleImageUri
    )
)