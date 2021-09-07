package id.byandev.hanifahapp.data.repository

import id.byandev.hanifahapp.data.remote.ApiCoinService
import id.byandev.hanifahapp.data.remote.dto.CoinDto
import id.byandev.hanifahapp.domain.repository.CoinRepository
import javax.inject.Inject


/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
class CoinRepositoryImpl @Inject constructor(
    private val apiCoinService: ApiCoinService
) : CoinRepository{
    override suspend fun getCoin(): List<CoinDto> {
        return apiCoinService.getCoin()
    }
}