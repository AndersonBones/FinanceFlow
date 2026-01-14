package br.edu.utfpr.financeflow
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.edu.utfpr.financeflow.ui.theme.FinanceFlowTheme
import br.edu.utfpr.financeflow.ui.transactions.NewTransaction
import java.text.NumberFormat


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Surface(modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize()
                    .wrapContentHeight(Alignment.Top)
                ) {

                    NewTransaction.Main(modifier = Modifier.fillMaxSize())

                }

            }
        }
    }
}


@Composable
fun FinanceHeader(modifier: Modifier = Modifier){
    val title:String = stringResource(R.string.app_name)
    val positiveBalance:Double = 20000.00
    val negativeBalance:Double = 7500.00

    Column(modifier = modifier
        .border(1.dp, color = Color.Green)
        .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FinanceTitle(title)
        FinanceCurrentBalance(Modifier)
        FinanceFlowTotal(
            modifier= Modifier.padding(top = 16.dp),
            positiveBalance = positiveBalance,
            negativeBalance = negativeBalance
        )
    }
}


@Composable
fun FinanceCurrentBalance(modifier: Modifier = Modifier, currentBalance:Double=12500.0) {
    val formatter = NumberFormat.getCurrencyInstance(Locale.current.platformLocale)

    Column(modifier = Modifier
        .border(1.dp, color = Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {

        Text("Current Balance") // subtitulo do componente

        Text(
            "${formatter.format(currentBalance)}",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        ) // Saldo atual
    }
}



@Composable
fun FinanceTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        fontSize = 24.sp,
        modifier = modifier.border(1.dp, color = Color.Red),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun FinanceFlowTotal(modifier: Modifier = Modifier, positiveBalance:Double, negativeBalance:Double) {

    val formatter = NumberFormat.getCurrencyInstance(Locale.current.platformLocale)

    Row (modifier = modifier.padding(4.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)){
        Text(
            "+ ${formatter.format(positiveBalance)}",
            modifier = Modifier.border(1.dp, color = Color.Green, shape = RoundedCornerShape(8.dp)).padding(4.dp)
        )
        Text(
            "- ${formatter.format(negativeBalance)}",
            modifier = Modifier.border(1.dp, color = Color.Red, shape = RoundedCornerShape(8.dp)).padding(4.dp)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun FinanceFlowTotalPreview() {
    FinanceFlowTheme {
        FinanceFlowTotal(modifier = Modifier, 20000.00, 7500.00)
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceTitlePreview() {
    FinanceFlowTheme {
        FinanceTitle("FinanceFlow")
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceCurrentBalancePreview() {
    FinanceFlowTheme {
        FinanceCurrentBalance(modifier = Modifier)
    }
}

