package objects

import com.google.gson.annotations.SerializedName

data class Schedulse(
        @SerializedName("days") val days: List<Day>
)