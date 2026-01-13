package br.edu.utfpr.financeflow.ui.extrato

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.financeflow.model.Movimentacao
import br.edu.utfpr.financeflow.ui.theme.FinanceFlowTheme

@Composable
fun ExtratoScreen(
    movimentacoes: List<Movimentacao>,
    saldo: Double,
    totalCredito: Double,
    totalDebito: Double,
    onAddClick: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar lançamento"
                )
            }
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            ExtratoHeader(
                saldo = saldo,
                totalCredito = totalCredito,
                totalDebito = totalDebito
            )

            Divider()

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(movimentacoes) { movimentacao ->
                    ExtratoItem(movimentacao)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExtratoScreenPreview() {
    FinanceFlowTheme {
        ExtratoScreen(
            movimentacoes = fakeMovimentacoes,
            saldo = 2670.0,
            totalCredito = 4300.0,
            totalDebito = 1630.0,
            onAddClick = {}
        )
    }
}
