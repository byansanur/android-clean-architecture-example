package id.byandev.hanifahapp.data.remote

import id.byandev.hanifahapp.data.remote.dto.CoinDto
import retrofit2.http.GET

interface ApiCoinService {

    @GET("/v1/coins")
    suspend fun getCoin(): List<CoinDto>


//    @GET("orgs/Google/repos")
//    open fun getRepositories(): Single<MutableList<Repo?>?>?
}