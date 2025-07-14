package com.pe.mascotapp.vistas.entities

import android.os.Parcelable
import com.pe.mascotapp.R
import kotlinx.parcelize.Parcelize

enum class CATEGORYID {
    VACCINE,
    DEWORNING,
    VET,
    ANALYSIS,
    MEDICINE,
    DENTAL,
    WALK,
    TAKESHOWER,
    WATERFOOD,
    //OTHERS,
    FLEATREATMENT,
    ZEAL,
    POOP,
    BEHAVIOR,
    WEIGHT,
    SYMPTOMS

}

sealed class CategoryReminderEntity(
    open val categoryId: CATEGORYID,
    open var isSelected: Boolean,
    open val name: String,
    open val image: Int,
) : Parcelable {
    @Parcelize
    class VaccineReminder : CategoryReminderEntity(
        CATEGORYID.VACCINE,
        false,
        "Vacuna",
        R.drawable.ic_vaccine,
    )

    @Parcelize
    class Deworming : CategoryReminderEntity(
        CATEGORYID.DEWORNING,
        false,
        "Desparasitación",
        R.drawable.ic_bacterias,
    )

    @Parcelize
    class VetReminder : CategoryReminderEntity(
        CATEGORYID.VET,
        false,
        "Veterinario",
        R.drawable.ic_vet,
    )

    @Parcelize
    class AnalysisReminder : CategoryReminderEntity(
        CATEGORYID.ANALYSIS,
        false,
        "Análisis",
        R.drawable.ic_analysis,
    )

    @Parcelize
    class MedicineReminder : CategoryReminderEntity(
        CATEGORYID.MEDICINE,
        false,
        "Medicina",
        R.drawable.ic_medicine,
    )

    @Parcelize
    class DentalReminder : CategoryReminderEntity(
        CATEGORYID.DENTAL,
        false,
        "Profilaxis Dental",
        R.drawable.ic_dental_prophylaxis,
    )

    @Parcelize
    class WalkReminder : CategoryReminderEntity(
        CATEGORYID.WALK,
        false,
        "Paseo",
        R.drawable.ic_walk,
    )

    @Parcelize
    class TakeShowerReminder : CategoryReminderEntity(
        CATEGORYID.TAKESHOWER,
        false,
        "Baño y Corte",
        R.drawable.ic_take_shower,
    )

    @Parcelize
    class WaterFoodReminder : CategoryReminderEntity(
        CATEGORYID.WATERFOOD,
        false,
        "Agua y Comida",
        R.drawable.ic_water_food,
    )

    /*@Parcelize
    class OthersReminder : CategoryReminderEntity(
        CATEGORYID.OTHERS,
        false,
        "Otros",
        R.drawable.ic_others,
    )*/

    @Parcelize
    class Fleatreatment : CategoryReminderEntity(
        CATEGORYID.FLEATREATMENT,
        false,
        "Antipulgas",
        R.drawable.ic_antipulgas,
    )

    @Parcelize
    class Zeal : CategoryReminderEntity(
        CATEGORYID.ZEAL,
        false,
        "Celos",
        R.drawable.ic_celo,
    )

    @Parcelize
    class Poop : CategoryReminderEntity(
        CATEGORYID.POOP,
        false,
        "Caca",
        R.drawable.ic_caca,
    )

    @Parcelize
    class Behavior : CategoryReminderEntity(
        CATEGORYID.BEHAVIOR,
        false,
        "Comportamiento",
        R.drawable.ic_comportamiento,
    )

    @Parcelize
    class Weight : CategoryReminderEntity(
        CATEGORYID.WEIGHT,
        false,
        "Peso",
        R.drawable.ic_paseo,
    )

    @Parcelize
    class Symptoms : CategoryReminderEntity(
        CATEGORYID.SYMPTOMS,
        false,
        "Sintomas",
        R.drawable.ic_sintomas,
    )


    companion object {
        fun getReminder(categoryId: CATEGORYID): CategoryReminderEntity {
            return when (categoryId) {
                CATEGORYID.VACCINE -> VaccineReminder()
                CATEGORYID.ANALYSIS -> AnalysisReminder()
                CATEGORYID.DEWORNING -> Deworming()
                CATEGORYID.VET -> VetReminder()
                CATEGORYID.MEDICINE -> MedicineReminder()
                CATEGORYID.DENTAL -> DentalReminder()
                CATEGORYID.WALK -> WalkReminder()
                CATEGORYID.TAKESHOWER -> TakeShowerReminder()
                CATEGORYID.WATERFOOD -> WaterFoodReminder()
                //CATEGORYID.OTHERS -> OthersReminder()
                CATEGORYID.FLEATREATMENT -> Fleatreatment()
                CATEGORYID.ZEAL -> Zeal()
                CATEGORYID.POOP -> Poop()
                CATEGORYID.BEHAVIOR -> Behavior()
                CATEGORYID.WEIGHT -> Weight()
                CATEGORYID.SYMPTOMS -> Symptoms()


            }
        }

        fun getCategories(): List<CategoryReminderEntity> {
            return listOf(
                VaccineReminder(),
                Deworming(),
                VetReminder(),
                AnalysisReminder(),
                MedicineReminder(),
                DentalReminder(),
                WalkReminder(),
                TakeShowerReminder(),
                WaterFoodReminder(),
                //OthersReminder(),
                Fleatreatment(),
                Zeal(),
                Poop(),
                Behavior(),
                Weight(),
                Symptoms()
            )
        }
    }
}
