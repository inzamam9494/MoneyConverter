package com.example.moneyconverter.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// step 3
data class ConverterState(
    val userData: String = "",
    val isMoney: Boolean = false,
    val result: String = "",
    val selectCurrency: String = ""
)


// step 2
class MyViewModel : ViewModel() {
    // step 4
    private val _uiState = MutableStateFlow(ConverterState())
    val uiStored: StateFlow<ConverterState> = _uiState

    // step 5

    fun currencyChange(selectCurrency: String){
        _uiState.value = _uiState.value.copy(selectCurrency = selectCurrency)
    }

    private fun convertMoney() {
// step 6
        val num = _uiState.value.userData.toIntOrNull()?: return

        val result = if (_uiState.value.selectCurrency == "Dollar") {
            // rupees convert to dollar
            "$ ${num * 0.012}"
        } else if(_uiState.value.selectCurrency == "Yuan") {
            // rupees convert to Yuan
            "¥ ${num * 0.088.toFloat()}"
        }else{
            ""
        }
// step 7 update the state
        _uiState.value = _uiState.value.copy(result = result.toString())
    }

// step 8
    fun updateDropdownState(isMoney: Boolean){
        _uiState.value = _uiState.value.copy(isMoney = isMoney)
    }

// step 9
    fun onUserSubmit(){
        convertMoney()
    }

// step 10
    fun onTextChange(userData: String){
        _uiState.value = _uiState.value.copy(userData = userData)
    }

//  step 11

}