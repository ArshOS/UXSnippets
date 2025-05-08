package com.innobles.uxcomponents.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innobles.uxcomponents.ui.theme.WhiteGreekVilla
import com.innobles.uxcomponents.ui.theme.WhiteGreekVillaBar
import com.innobles.uxcomponents.ui.theme.WhiteGreekVillaItem

@Composable
fun IMEActionsComposable() {
    Scaffold(
        topBar = { TopBarComposable() },
        content = { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                FormComposable()
            }
        },
        bottomBar = { BottomBarComposable() },
        containerColor = WhiteGreekVilla
    )
}


@Composable
fun FormComposable() {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    var emailError by remember { mutableStateOf(false) }
    var emailErrorMessage by remember { mutableStateOf<String?>(null) }

    var passwordVisible by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var passwordErrorMessage by remember { mutableStateOf<String?>(null) }

    val isFormValid = !emailError && !passwordError &&
            email.text.isNotBlank() && password.text.isNotBlank()

    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteGreekVilla)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Email Field with IME Next
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = !isValidEmail(it.text)
                emailErrorMessage = if (emailError) "Invalid email address" else null
            },
            label = { Text("Enter email") },
            isError = emailError,
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            )
        )

        if (emailError && emailErrorMessage != null) {
            Text(
                text = emailErrorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field with IME Done
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = it.text.length < 6
                passwordErrorMessage = if (passwordError) "Password must be at least 6 characters" else null
            },
            label = { Text("Enter password") },
            isError = passwordError,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon = if (passwordVisible) Icons.Filled.Done else Icons.Filled.Lock
                val desc = if (passwordVisible) "Hide password" else "Show password"
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = icon, contentDescription = desc, tint = WhiteGreekVillaBar)
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    // Optional: Trigger submit here if desired
                }
            )
        )

        if (passwordError && passwordErrorMessage != null) {
            Text(
                text = passwordErrorMessage!!,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Submit button
        Button(
            onClick = { },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                containerColor = WhiteGreekVillaBar,
                contentColor = WhiteGreekVillaItem,
                disabledContainerColor = Color.LightGray,
                disabledContentColor = Color.Gray
            )
        ) {
            Text("Submit")
        }
    }
}




private fun isValidEmail(email: String): Boolean {
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
    IMEActionsComposable()
}