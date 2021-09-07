package id.byandev.hanifahapp.domain.use_case

import id.byandev.hanifahapp.common.Resource
import id.byandev.hanifahapp.data.remote.dto.toCoin
import id.byandev.hanifahapp.domain.model.Coin
import id.byandev.hanifahapp.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
class GetCoinUseCase @Inject constructor(
    private val coinRepository: CoinRepository
) {

    operator fun invoke() : Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = coinRepository.getCoin().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(coins))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<Coin>>("Couldn't reach server. Check your internet connection."))
        }
    }
}