package br.edu.utfpr.financeflow.ui.extrato

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.financeflow.model.Movimentacao
import br.edu.utfpr.financeflow.model.TipoLancamento
import br.edu.utfpr.financeflow.ui.extrato.ui.theme.FundoCredito
import br.edu.utfpr.financeflow.ui.extrato.ui.theme.FundoDebito
import br.edu.utfpr.financeflow.ui.extrato.ui.theme.VerdeCredito
import br.edu.utfpr.financeflow.ui.extrato.ui.theme.VermelhoDebito
import br.edu.utfpr.financeflow.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatarData(timestamp: Long): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return sdf.format(Date(timestamp))
}
@Composable
fun ExtratoItem(movimentacao: Movimentacao) {

    val cor = if (movimentacao.tipoLancamento == TipoLancamento.CREDITO)
        VerdeCredito
    else
        VermelhoDebito

    val fundo = if (movimentacao.tipoLancamento == TipoLancamento.CREDITO)
        FundoCredito
    else
        FundoDebito

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = fundo
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {
                Text(
                    text = movimentacao.descricao,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = formatarData(movimentacao.data),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }

            Text(
                text = if (movimentacao.tipoLancamento == TipoLancamento.CREDITO)
                    "+ R$ %.2f".format(movimentacao.valorLancamento)
                else
                    "- R$ %.2f".format(movimentacao.valorLancamento),
                color = cor,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExtratoItemPreview() {
    FinanceFlowTheme {
        ExtratoItem(
            movimentacao = fakeMovimentacoes.first()
        )
    }
}
