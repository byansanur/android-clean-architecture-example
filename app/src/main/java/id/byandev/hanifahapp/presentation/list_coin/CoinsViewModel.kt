package id.byandev.hanifahapp.presentation.list_coin

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import id.byandev.hanifahapp.common.Resource
import id.byandev.hanifahapp.domain.model.Coin
import id.byandev.hanifahapp.domain.use_case.GetCoinUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


/**
 * Created by byansanur on 9/7/2021.
 * ratbyansanur81@gmail.com
 */
@HiltViewModel
class CoinsViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
) : ViewModel() {

    private val _state = MutableLiveData<CoinsListState>()
    val state : LiveData<CoinsListState> = _state

    val ls = MutableLiveData<Resource<List<Coin>>>()

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinUseCase().onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinsListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = CoinsListState(error = result.message ?: "An unexpected error occured")
                }
                is Resource.Loading -> {
                    _state.value = CoinsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}