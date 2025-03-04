package cz.mendelu.xzirchuk.project.models

import com.google.gson.annotations.SerializedName

data class APITemperature (
    var current              : Current?      = Current())

data class Current (

    @SerializedName("time") var time          : String = "",
    @SerializedName("temperature_2m") var temperature_2m : Double? = null

)