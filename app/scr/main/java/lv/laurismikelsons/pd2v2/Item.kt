package lv.laurismikelsons.pd2v2

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName ("id")
    val id: Int,
    @SerializedName ("setup")
    val firstString: String,
    @SerializedName ("punchline")
    val secondString: String
)