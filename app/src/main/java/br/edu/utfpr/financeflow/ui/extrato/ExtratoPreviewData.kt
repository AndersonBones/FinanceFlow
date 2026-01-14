package br.edu.utfpr.financeflow.ui.extrato

import br.edu.utfpr.financeflow.model.Movimentacao
import br.edu.utfpr.financeflow.model.TipoLancamento

val fakeMovimentacoes = listOf(
    Movimentacao(
        descricao = "Salário",
        valorLancamento = 3500.0,
        tipoLancamento = TipoLancamento.CREDITO,
        data = System.currentTimeMillis()
    ),
    Movimentacao(
        descricao = "Aluguel",
        valorLancamento = 1200.0,
        tipoLancamento = TipoLancamento.DEBITO,
        data = System.currentTimeMillis()
    ),
    Movimentacao(
        descricao = "Mercado",
        valorLancamento = 430.0,
        tipoLancamento = TipoLancamento.DEBITO,
        data = System.currentTimeMillis()
    ),
    Movimentacao(
        descricao = "Freela",
        valorLancamento = 800.0,
        tipoLancamento = TipoLancamento.CREDITO,
        data = System.currentTimeMillis()
    )
)
