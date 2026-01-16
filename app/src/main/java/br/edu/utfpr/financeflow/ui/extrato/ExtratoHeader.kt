package br.edu.utfpr.financeflow.ui.extrato

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.financeflow.R
import br.edu.utfpr.financeflow.model.TipoLancamento
import br.edu.utfpr.financeflow.ui.theme.FinanceFlowTheme

@Composable
fun ExtratoHeader(
    saldo: Double,
    totalCredito: Double,
    totalDebito: Double
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.current_balance_label_extrato_screen),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )

        Text(
            text = "R$ %.2f".format(saldo),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            IndicadorValor(
                valor = totalCredito,
                tipo = TipoLancamento.CREDITO
            )

            IndicadorValor(
                valor = totalDebito,
                tipo = TipoLancamento.DEBITO
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExtratoHeaderPreview() {
    FinanceFlowTheme {
        ExtratoHeader(
            saldo = 12450.0,
            totalCredito = 2400.0,
            totalDebito = 850.0
        )
    }
}
