package com.example.pizza_shift_2025.authorization_screen.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pizza_shift_2025.R
import com.example.pizza_shift_2025.authorization_screen.presentation.AuthenticationViewModel
import com.example.pizza_shift_2025.common.Constants
import com.example.pizza_shift_2025.common.di.getApplicationComponent
import com.example.pizza_shift_2025.common.ui.theme.brandOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthorizationScreen() {

    val component = getApplicationComponent()
    val viewModel: AuthenticationViewModel = viewModel(factory = component.getViewModelFactory())
    val screenState by viewModel.screenState.collectAsState(AuthenticationScreenState.OtpNotRequested)

    var phoneNumber by rememberSaveable { mutableStateOf(Constants.EMPTY_STRING) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.authorization_screen_title),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.authorization_screen_text),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomOutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = stringResource(id = R.string.authorization_screen_phone_input),
                keyboardType = KeyboardType.Phone
            )

            Spacer(modifier = Modifier.height(24.dp))

            observeViewModel(screenState, viewModel, phoneNumber)
        }
    }
}

@Composable
private fun observeViewModel(
    screenState: AuthenticationScreenState,
    viewModel: AuthenticationViewModel,
    phoneNumber: String
) {
    when (screenState) {

        is AuthenticationScreenState.OtpNotRequested -> {
            OtpNotRequested(
                onRequestOtp = {
                    viewModel.createOtp(phoneNumber)
                })
        }

        is AuthenticationScreenState.Error -> {
            ErrorMessage(state = screenState,
                onRequestCode = {
                    viewModel.createOtp(phoneNumber)
                })
        }

        is AuthenticationScreenState.OtpRequestAllowed -> {
            OtpRequestAllowed(
                onSignIn = {},
                onRequestCode = { viewModel.createOtp(phoneNumber) }
            )
        }

        is AuthenticationScreenState.OtpRequestCooldown -> {
            OtpRequestCooldown(
                state = screenState,
                onSignIn = { otpCode ->
                    viewModel.signIn(
                        phone = phoneNumber,
                        otpCode = otpCode
                    )
                })
        }

        is AuthenticationScreenState.Authorized -> {
            Log.d("AuthorizationScreen", "AUTHORIZED URAAAAAAAAA")
            //Закрыть экран
        }
    }
}

@Composable
private fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = MaterialTheme.colorScheme.tertiary) },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.tertiary,
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
        ),
        modifier = Modifier.fillMaxWidth(),
        singleLine = true
    )
}

@Composable
private fun OtpNotRequested(onRequestOtp: () -> Unit) {
    CustomButton(
        text = stringResource(id = R.string.authorization_screen_send_otp),
        onClick = onRequestOtp
    )
}

@Composable
private fun OtpRequestAllowed(
    onSignIn: () -> Unit,
    onRequestCode: () -> Unit
) {
    var otpCode by rememberSaveable { mutableStateOf(Constants.EMPTY_STRING) }

    CustomOutlinedTextField(
        value = otpCode,
        onValueChange = { otpCode = it },
        label = stringResource(id = R.string.authorization_screen_otp_input),
        keyboardType = KeyboardType.Phone
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomButton(
        text = stringResource(id = R.string.authorization_screen_sign_in),
        onClick = onSignIn
    )

    Spacer(modifier = Modifier.height(16.dp))

    RequestOtpCode(onRequestCode)
}

@Composable
private fun RequestOtpCode(onRequestCode: () -> Unit) {
    TextButton(onClick = onRequestCode) {
        Text(
            text = stringResource(id = R.string.authorization_screen_request_otp),
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp
        )
    }
}


@Composable
private fun OtpRequestCooldown(
    state: AuthenticationScreenState.OtpRequestCooldown,
    onSignIn: (String) -> Unit
) {

    var otpCode by rememberSaveable { mutableStateOf(Constants.EMPTY_STRING) }
    val secondsLeft = state.secondsLeft

    CustomOutlinedTextField(
        value = otpCode,
        onValueChange = { otpCode = it },
        label = stringResource(id = R.string.authorization_screen_otp_input),
        keyboardType = KeyboardType.Phone
    )

    Spacer(modifier = Modifier.height(16.dp))

    CustomButton(
        text = stringResource(id = R.string.authorization_screen_sign_in),
        onClick = {
            onSignIn.invoke(otpCode)
        }
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = stringResource(id = R.string.authorization_screen_cooldown_time, secondsLeft),
        fontSize = 14.sp,
        color = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
private fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = brandOrange),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = text, fontSize = 16.sp, color = Color.White)
    }
}

@Composable
private fun ErrorMessage(state: AuthenticationScreenState.Error, onRequestCode: () -> Unit) {
    val errorMessage = state.message
    Text(
        text = errorMessage,
        color = Color.Red,
        fontSize = 14.sp
    )
    RequestOtpCode(onRequestCode)
}
