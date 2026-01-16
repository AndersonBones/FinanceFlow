package br.edu.utfpr.financeflow.ui.transactions

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.utfpr.financeflow.R
import br.edu.utfpr.financeflow.model.Movimentacao
import br.edu.utfpr.financeflow.model.TipoLancamento
import br.edu.utfpr.financeflow.ui.extrato.ui.theme.BlueOcean
import br.edu.utfpr.financeflow.ui.extrato.ui.theme.WhiteBackground
import br.edu.utfpr.financeflow.viewmodel.NewTransactionViewModel
import java.util.UUID

object NewTransaction {
    @Composable
    fun Main(
        modifier: Modifier = Modifier,
        viewModel: NewTransactionViewModel = viewModel(),
        onNavigateToExtrato: () -> Unit,
        onNavigateToDeveloper: () -> Unit = {}
    ) {
        Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Header(modifier = Modifier)
            AmountTransaction(
                modifier = Modifier,
                viewModel
            )
            TransactionOption(modifier = Modifier, viewModel)

            DatePicker.Main(
                modifier = Modifier,
                viewModel = viewModel
            )

            DescriptionTransaction(modifier = Modifier, viewModel)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ListTransactions(onClick = onNavigateToExtrato)
                SaveTransactionButton(
                    modifier = Modifier,
                    viewModel = viewModel
                )
            }
        }
    }

    @Composable
    fun Header(
        modifier: Modifier = Modifier,
        title: String = stringResource(R.string.title_activity_add_transaction)
    ) {
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = title,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    @Composable
    fun ListTransactions(
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueOcean,
            ),
            modifier = modifier.height(55.dp)
        ) {
            Text(
                text = stringResource(R.string.list_transactions_button_add_transaction)
            )
        }
    }

    @Composable
    fun AmountTransaction(
        modifier: Modifier = Modifier,
        viewModel: NewTransactionViewModel
    ) {
        Column(
            modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.amount_label_add_transaction),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            TextField(
                modifier = Modifier
                    .background(color = WhiteBackground)
                    .fillMaxWidth()
                    .border(1.dp, color = Color.Transparent, shape = RoundedCornerShape(5.dp)),
                value = viewModel.amount,
                onValueChange = { newValue -> viewModel.setOnChangeAmount(newValue) },
                placeholder = {
                    Text(
                        text = stringResource(R.string.amount_label_cipher),
                        fontSize = 64.sp
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BlueOcean,
                    cursorColor = BlueOcean,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                singleLine = true
            )
        }
    }

    @Composable
    fun TransactionOption(modifier: Modifier = Modifier, viewModel: NewTransactionViewModel) {
        Row(
            modifier
                .border(
                    1.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(5.dp)
                )
                .background(
                    color = WhiteBackground,
                    shape = RoundedCornerShape(5.dp)
                )
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            val isDebito = viewModel.transaction == TipoLancamento.DEBITO
            Button(
                onClick = { viewModel.setOnChangeTransaction(viewModel.amount, newTransaction = TipoLancamento.DEBITO) },
                modifier = Modifier.weight(1f).height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isDebito) BlueOcean else Color.LightGray
                )
            ) {
                Text(text = stringResource(R.string.debit_transaction_option_add_transaction))
            }

            Button(
                onClick = { viewModel.setOnChangeTransaction(viewModel.amount, newTransaction = TipoLancamento.CREDITO) },
                modifier = Modifier.weight(1f).height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (!isDebito) BlueOcean else Color.LightGray
                )
            ) {
                Text(text = stringResource(R.string.credit_transaction_option_add_transaction))
            }
        }
    }

    @Composable
    fun DescriptionTransaction(
        modifier: Modifier = Modifier,
        viewModel: NewTransactionViewModel
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Text(
                text = stringResource(R.string.enter_description_add_transaction),
                fontWeight = FontWeight.Bold
            )
            TextField(
                modifier = modifier
                    .fillMaxWidth()
                    .background(color = WhiteBackground),
                value = viewModel.description,
                onValueChange = { newValue -> viewModel.setOnChangeDescription(newDescription = newValue) },
                maxLines = 4,
                minLines = 1,
                placeholder = {
                    Text(text = stringResource(R.string.hint_description_add_transaction))
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = BlueOcean,
                    cursorColor = BlueOcean,
                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )
        }
    }

    @Composable
    fun SaveTransactionButton(
        modifier: Modifier = Modifier,
        viewModel: NewTransactionViewModel
    ) {
        val context = LocalContext.current
        Button(
            onClick = {
                val valor = viewModel.amount.toDoubleOrNull() ?: 0.0
                val newTransaction = Movimentacao(
                    _id = UUID.randomUUID().toString(),
                    valorLancamento = valor,
                    tipoLancamento = viewModel.transaction,
                    descricao = viewModel.description,
                    data = viewModel.date
                )
                viewModel.setNewTransaction(newTransaction) {
                    Toast.makeText(context, context.getString(R.string.msg_confirm_new_transaction), Toast.LENGTH_SHORT).show()
                    viewModel.resetFields()
                }
            },
            modifier = modifier.height(55.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BlueOcean
            )
        ) {
            Text(
                text = stringResource(R.string.save_transaction_add_transaction)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainPreview() {
    NewTransaction.Main(
        onNavigateToExtrato = {}
    )
}
