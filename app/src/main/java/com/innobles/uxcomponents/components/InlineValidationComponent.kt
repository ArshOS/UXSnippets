package com.innobles.uxcomponents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innobles.uxcomponents.ui.theme.WhiteGreekVilla

@Composable
fun InlineValidationComposable() {
    Scaffold(
        topBar = { TopBarComposable() },
        content = { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                ValidatingTextField()
            }
        },
        bottomBar = { BottomBarComposable() },
        containerColor = WhiteGreekVilla
    )
}


@Composable
fun ValidatingTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    var isError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteGreekVilla)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                isError = !isValidEmail(it.text)
                if (isError) {
                    errorMessage = "Invalid input"
                } else {
                    errorMessage = null
                }
            },
            label = { Text("Enter email") },
            isError = isError
        )
        if (isError) {
            Text(
                text = errorMessage ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return email.matches(emailRegex)
}


@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 800
)
@Composable
private fun GreetingPreview() {
    InlineValidationComposable()
}