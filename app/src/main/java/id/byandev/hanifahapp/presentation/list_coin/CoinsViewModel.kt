package id.byandev.hanifahapp.presentation.list_coin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.byandev.hanifahapp.WahhaabApplication
import id.byandev.hanifahapp.common.Resource
import id.byandev.hanifahapp.domain.use_case.GetCoinUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
                    if (WahhaabApplication.hasNetwork()) {
                        _state.value = CoinsListState(error = result.message ?: "An unexpected error occured")
                    } else {
                        _state.value = CoinsListState(error = "no internet connection")
                    }
                }
                is Resource.Loading -> {
                    _state.value = CoinsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}