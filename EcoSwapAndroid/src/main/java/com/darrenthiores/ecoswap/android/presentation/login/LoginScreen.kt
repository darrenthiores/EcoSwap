package com.darrenthiores.ecoswap.android.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.presentation.components.textfields.DefaultTextField
import com.darrenthiores.ecoswap.android.presentation.components.textfields.PasswordTextField
import com.darrenthiores.ecoswap.android.theme.Blue
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.android.theme.SubHeadlineB
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR
import com.darrenthiores.ecoswap.android.theme.Title2B
import com.darrenthiores.ecoswap.presentation.login.LoginEvent
import com.darrenthiores.ecoswap.presentation.login.LoginState

@Composable
fun LoginScreen(
    state: LoginState,
    onEvent: (LoginEvent) -> Unit,
    onSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 24.dp,
                vertical = 56.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(128.dp),
            painter = painterResource(id = R.drawable.ecoswap_logo),
            contentDescription = stringResource(id = R.string.ecoswap_logo),
            contentScale = ContentScale.FillHeight
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(id = R.string.login_title),
            style = Title2B
        )

        Text(
            text = stringResource(id = R.string.login_headline),
            style = SubHeadlineR.copy(
                color = Color.Gray
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        DefaultTextField(
            text = state.email,
            onTextChange = {
                onEvent(LoginEvent.OnEmailChange(it))
            },
            placeholder = stringResource(id = R.string.email_placeholder)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PasswordTextField(
            text = state.password,
            onTextChange = {
                onEvent(LoginEvent.OnPasswordChange(it))
            },
            placeholder = stringResource(id = R.string.password_placeholder),
            isVisible = state.showPassword,
            onToggle = {
                onEvent(LoginEvent.ToggleShowPassword)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            Text(
                modifier = Modifier
                    .clickable {

                    },
                text = stringResource(id = R.string.forget_password),
                style = FootnoteR.copy(
                    color = Blue
                )
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        DefaultButton(
            modifier = Modifier
                .fillMaxWidth(),
            label = stringResource(id = R.string.sign_in),
            onClick = {
                onEvent(LoginEvent.Login)
            },
            disabled = state.isLoading || state.email.isEmpty() ||
            state.password.isEmpty()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.dont_have_acc),
                style = SubHeadlineR
            )

            Text(
                modifier = Modifier
                    .clickable {
                        onSignUp()
                    },
                text = stringResource(id = R.string.sign_up),
                style = SubHeadlineB.copy(
                    color = Blue
                )
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    EcoSwapTheme {
        LoginScreen(
            state = LoginState(),
            onEvent = {  },
            onSignUp = {  }
        )
    }
}