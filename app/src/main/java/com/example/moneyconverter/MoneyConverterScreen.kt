package com.example.moneyconverter


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneyconverter.ui.theme.MoneyConverterTheme
import com.example.moneyconverter.ui.theme.MyViewModel

//   step 1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoneyConvertScreen(
    vm: MyViewModel = viewModel()
) {

    val uiStored = vm.uiStored.collectAsState()



    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = "Money Converter",
                style = MaterialTheme.typography.titleLarge
            )
        })
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {}
    }

// User Input text filed box
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(value = uiStored.value.userData,
            onValueChange = { vm.onTextChange(it) },
            label = { Text(text = "₹") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.padding(10.dp))

//  Drop down Menu Bar      
        ExposedDropdownMenuBox(expanded = uiStored.value.isMoney,
            onExpandedChange = { vm.updateDropdownState(it) }) {
            OutlinedTextField(
                value = uiStored.value.selectCurrency,
                onValueChange = { vm.currencyChange(it) }, readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = uiStored.value.isMoney) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(expanded = uiStored.value.isMoney,
                onDismissRequest = { vm.updateDropdownState(isMoney = false) }) {
                DropdownMenuItem(text = { Text(text = "Dollar") },
                    onClick = {
                        vm.currencyChange("Dollar")
                    })
                DropdownMenuItem(text = { Text(text = "Yuan") },
                    onClick = {
                        vm.currencyChange("Yuan")})
            }

        }
        Spacer(modifier = Modifier.padding(10.dp))

//   Convert Button
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center) {
            Button(onClick = { vm.onUserSubmit() }) {
                Text(text = "Change")
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Text(text = uiStored.value.result,
                style = MaterialTheme.typography.headlineLarge)
        }





    }
}

@Preview
@Composable
fun MoneyConverterPreview() {
    MoneyConverterTheme {
        MoneyConvertScreen()
    }
}