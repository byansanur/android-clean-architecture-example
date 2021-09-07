package id.byandev.hanifahapp.presentation.list_coin

import id.byandev.hanifahapp.domain.model.Coin


/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
data class CoinsListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)