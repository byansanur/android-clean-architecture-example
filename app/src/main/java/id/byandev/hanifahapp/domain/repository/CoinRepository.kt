package id.byandev.hanifahapp.domain.repository

import id.byandev.hanifahapp.data.remote.dto.CoinDto


/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
interface CoinRepository {

    suspend fun getCoin() : List<CoinDto>
}