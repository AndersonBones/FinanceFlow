package br.edu.utfpr.financeflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.utfpr.financeflow.database.MovimentacaoDatabaseHandler
import br.edu.utfpr.financeflow.model.Movimentacao
import br.edu.utfpr.financeflow.model.TipoLancamento
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExtratoViewModel : ViewModel() {

    private val database = MovimentacaoDatabaseHandler.getInstance()

    private val _movimentacoes = MutableStateFlow<List<Movimentacao>>(emptyList())
    val movimentacoes: StateFlow<List<Movimentacao>> = _movimentacoes.asStateFlow()

    private val _saldo = MutableStateFlow(0.0)
    val saldo: StateFlow<Double> = _saldo.asStateFlow()

    private val _totalCredito = MutableStateFlow(0.0)
    val totalCredito: StateFlow<Double> = _totalCredito.asStateFlow()

    private val _totalDebito = MutableStateFlow(0.0)
    val totalDebito: StateFlow<Double> = _totalDebito.asStateFlow()

    init {
        carregarMovimentacoes()
    }

    fun carregarMovimentacoes() {
        viewModelScope.launch {
            val lista = database.listar()
            _movimentacoes.value = lista
            calcularTotais(lista)
        }
    }

    fun inserir(movimentacao: Movimentacao) {
        viewModelScope.launch {
            database.inserir(movimentacao)
            carregarMovimentacoes()
        }
    }

    private fun calcularTotais(lista: List<Movimentacao>) {
        val credito = lista
            .filter { it.tipoLancamento == TipoLancamento.CREDITO }
            .sumOf { it.valorLancamento }

        val debito = lista
            .filter { it.tipoLancamento == TipoLancamento.DEBITO }
            .sumOf { it.valorLancamento }

        _totalCredito.value = credito
        _totalDebito.value = debito
        _saldo.value = credito - debito
    }
}
