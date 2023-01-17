package com.rochapires.coffeebrewer.features.feature_onboarding.presentation.coffee_quantity

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Preview
@Composable
fun CoffeeQuantityScreen(
    viewModel: CoffeeQuantityViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        Text(
            text = "Do you want to set a default quantity for coffee beans?\n\n" +
                    "You can always change this later" ,
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
        )

        Spacer(modifier = Modifier.height(30.dp))

        var textState by remember { mutableStateOf("") }
        val focusManager = LocalFocusManager.current
        TextField(
            value = textState,
            modifier = Modifier
                .background(color = Color.Transparent)
                .fillMaxWidth(),
            onValueChange = { newText ->
                textState = newText
            },
            label = {
                Text(text = "Coffee quantity")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if(textState.isNotBlank()) {
                        //TODO: disable done button until valid input
                        viewModel.onEvent(CoffeeQuantityEvent.DoneInserting(textState.toDouble()))
                        focusManager.clearFocus()
                    }
                }
            ),

        )
    }
}