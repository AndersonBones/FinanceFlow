package br.edu.utfpr.financeflow.ui.transactions

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.edu.utfpr.financeflow.R
import br.edu.utfpr.financeflow.viewmodel.NewTransactionViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DatePicker {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Main(
        modifier: Modifier = Modifier,
        viewModel: NewTransactionViewModel = viewModel()
    ) {
        var showDatePicker by remember { mutableStateOf(false) }


        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = viewModel.date ?: System.currentTimeMillis()
        )

        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateText = viewModel.date?.let { dateFormatter.format(Date(it)) } ?: ""


        Column(modifier = modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = dateText,
                onValueChange = { },
                label = { Text("Data") },
                placeholder = { Text("DD/MM/YYYY") },
                readOnly = true,
                trailingIcon = {
                    IconButton(onClick = { showDatePicker = true }) {
                        Icon(Icons.Default.DateRange, contentDescription = stringResource(R.string.select_date_labe_date_picker))
                    }
                },
                modifier = Modifier.fillMaxWidth(),

                interactionSource = remember { androidx.compose.foundation.interaction.MutableInteractionSource() }
            )


            if (showDatePicker) {
                DatePickerDialog(
                    onDismissRequest = { showDatePicker = false },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.setOnChangeDate(datePickerState.selectedDateMillis)
                            showDatePicker = false
                        }) {
                            Text(stringResource(R.string.confirm_label_date_picker))
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDatePicker = false }) {
                            Text(stringResource(R.string.cancel_label_date_picker))
                        }
                    }
                ) {

                    DatePicker(state = datePickerState)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DatePickerPreview() {
    MaterialTheme {

        Column(Modifier.padding(16.dp)) {
            DatePicker.Main()
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}