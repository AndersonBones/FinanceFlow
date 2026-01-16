package br.edu.utfpr.financeflow.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.utfpr.financeflow.database.MovimentacaoDatabaseHandler
import br.edu.utfpr.financeflow.model.Movimentacao
import br.edu.utfpr.financeflow.model.TipoLancamento
import kotlinx.coroutines.launch

class NewTransactionViewModel: ViewModel() {

    private val db: MovimentacaoDatabaseHandler = MovimentacaoDatabaseHandler.getInstance()
    var amount:String by  mutableStateOf("")
        private set
    var transaction: TipoLancamento by mutableStateOf(TipoLancamento.DEBITO)
        private set
    var description:String by mutableStateOf("")
        private set
    var date:Long by mutableStateOf(System.currentTimeMillis())
        private set

    fun setOnChangeTransaction(newAmount:String, newTransaction: TipoLancamento){
        this.transaction = newTransaction
    }
    fun setOnChangeAmount(newAmount:String){
        this.amount = newAmount
    }

    fun setOnChangeDescription(newDescription:String){
        this.description = newDescription
    }

    fun setOnChangeDate(newDate:Long){
        this.date = newDate
    }

    fun resetFields() {
        amount = ""
        description = ""
        date = System.currentTimeMillis()
        transaction = TipoLancamento.DEBITO
    }

    fun setNewTransaction(transaction: Movimentacao, onSuccess: () -> Unit){
        viewModelScope.launch {
            db.inserir(transaction)
            onSuccess()
        }
    }
}