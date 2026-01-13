package br.edu.utfpr.financeflow.ui.extrato

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.utfpr.financeflow.viewmodel.ExtratoViewModel

@Composable
fun ExtratoRoute(
    onAddClick: () -> Unit
) {
    val viewModel: ExtratoViewModel = viewModel()

    val movimentacoes by viewModel.movimentacoes.collectAsState()
    val saldo by viewModel.saldo.collectAsState()
    val totalCredito by viewModel.totalCredito.collectAsState()
    val totalDebito by viewModel.totalDebito.collectAsState()

    ExtratoScreen(
        movimentacoes = movimentacoes,
        saldo = saldo,
        totalCredito = totalCredito,
        totalDebito = totalDebito,
        onAddClick = onAddClick
    )
}
