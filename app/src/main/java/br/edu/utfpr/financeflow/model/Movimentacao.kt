package br.edu.utfpr.financeflow.model

enum class TipoLancamento {
    CREDITO,
    DEBITO
}

data class Movimentacao(
    val id: String,
    val valorLancamento: Double,
    val tipoLancamento: TipoLancamento,
    val descricao: String,
    val data: Long
)