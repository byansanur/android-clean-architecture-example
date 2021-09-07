package id.byandev.hanifahapp.data.remote.dto

import com.google.gson.annotations.SerializedName
import id.byandev.hanifahapp.domain.model.Coin


/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
data class CoinDto(
    val id: String,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_new")
    val isNew: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        isActive = isActive,
        name = name,
        rank = rank,
        symbol = symbol
    )
}