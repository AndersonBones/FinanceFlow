package br.edu.utfpr.financeflow.ui.extrato

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.edu.utfpr.financeflow.model.TipoLancamento
import br.edu.utfpr.financeflow.ui.theme.FinanceFlowTheme
import br.edu.utfpr.financeflow.ui.theme.VerdeCredito
import br.edu.utfpr.financeflow.ui.theme.VermelhoDebito

@Composable
fun IndicadorValor(
    valor: Double,
    tipo: TipoLancamento
) {
    val cor = if (tipo == TipoLancamento.CREDITO)
        VerdeCredito
    else
        VermelhoDebito

    Card(
        colors = CardDefaults.cardColors(
            containerColor = cor.copy(alpha = 0.15f)
        ),
        shape = RoundedCornerShape(50)
    ) {
        Text(
            text = if (tipo == TipoLancamento.CREDITO)
                "+ R$ %.2f".format(valor)
            else
                "- R$ %.2f".format(valor),
            color = cor,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IndicadorValorCreditoPreview() {
    FinanceFlowTheme {
        IndicadorValor(
            valor = 2400.0,
            tipo = TipoLancamento.CREDITO
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IndicadorValorDebitoPreview() {
    FinanceFlowTheme {
        IndicadorValor(
            valor = 850.0,
            tipo = TipoLancamento.DEBITO
        )
    }
}
