package br.edu.utfpr.financeflow.model

import com.google.firebase.firestore.DocumentId

enum class TipoLancamento {
    CREDITO,
    DEBITO
}

data class Movimentacao(
    @DocumentId
    val _id: String? = null,
    val valorLancamento: Double = 0.0,
    val tipoLancamento: TipoLancamento = TipoLancamento.DEBITO,
    val descricao: String = "",
    val data: Long = 0L
)