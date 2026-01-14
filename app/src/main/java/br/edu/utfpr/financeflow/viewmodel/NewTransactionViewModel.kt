package br.edu.utfpr.financeflow.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class NewTransactionViewModel: ViewModel() {

    var amount:String by  mutableStateOf("")
        private set
    var transaction:String by mutableStateOf("")
        private set
    var description:String by mutableStateOf("")
        private set
    var date:Long? by mutableStateOf(System.currentTimeMillis())
        private set



    fun setOnChangeAmount(newAmount:String){
        this.amount = newAmount
    }

    fun setOnChangeDescription(newDescription:String){
        this.description = newDescription
    }

    fun setOnChangeDate(newDate:Long?){
        this.date = newDate
    }
}