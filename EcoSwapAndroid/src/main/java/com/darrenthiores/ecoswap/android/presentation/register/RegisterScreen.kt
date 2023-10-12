package com.darrenthiores.ecoswap.android.presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.darrenthiores.ecoswap.android.R
import com.darrenthiores.ecoswap.android.presentation.components.buttons.DefaultButton
import com.darrenthiores.ecoswap.android.presentation.components.textfields.DefaultTextField
import com.darrenthiores.ecoswap.android.presentation.components.textfields.PasswordTextField
import com.darrenthiores.ecoswap.android.theme.Blue
import com.darrenthiores.ecoswap.android.theme.EcoSwapTheme
import com.darrenthiores.ecoswap.android.theme.FootnoteB
import com.darrenthiores.ecoswap.android.theme.FootnoteR
import com.darrenthiores.ecoswap.android.theme.SubHeadlineB
import com.darrenthiores.ecoswap.android.theme.SubHeadlineR
import com.darrenthiores.ecoswap.android.theme.Title2B
import com.darrenthiores.ecoswap.presentation.register.RegisterEvent
import com.darrenthiores.ecoswap.presentation.register.RegisterState
import timber.log.Timber

@Composable
fun RegisterScreen(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onSignIn: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 56.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
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
                text = stringResource(id = R.string.register_headline),
                style = SubHeadlineR.copy(
                    color = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            DefaultTextField(
                text = state.username,
                onTextChange = {
                    onEvent(RegisterEvent.OnUsernameChange(it))
                },
                placeholder = stringResource(id = R.string.username_placeholder)
            )

            Spacer(modifier = Modifier.height(16.dp))

            DefaultTextField(
                text = state.email,
                onTextChange = {
                    onEvent(RegisterEvent.OnEmailChange(it))
                },
                placeholder = stringResource(id = R.string.email_placeholder)
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                text = state.password,
                onTextChange = {
                    onEvent(RegisterEvent.OnPasswordChange(it))
                },
                placeholder = stringResource(id = R.string.password_placeholder),
                isVisible = state.showPassword,
                onToggle = {
                    onEvent(RegisterEvent.ToggleShowPassword)
                }
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            val tosTag = stringResource(id = R.string.tos_tag)
            val ppTag = stringResource(id = R.string.pp_tag)
            val text =  buildAnnotatedString {
                append(
                    stringResource(id = R.string.text_before_tos)
                )

                pushStringAnnotation(
                    tag = stringResource(id = R.string.tos_tag),
                    annotation = stringResource(id = R.string.tos)
                )

                withStyle(FootnoteB.toSpanStyle()) {
                    append(
                        stringResource(id = R.string.tos)
                    )
                }

                pop()

                append(
                    stringResource(id = R.string.text_before_pp)
                )

                pushStringAnnotation(
                    tag = stringResource(id = R.string.pp_tag),
                    annotation = stringResource(id = R.string.pp)
                )

                withStyle(FootnoteB.toSpanStyle()) {
                    append(
                        stringResource(id = R.string.pp)
                    )
                }

                pop()
            }

            ClickableText(
                text = text,
                style = FootnoteR
                    .copy(
                        textAlign = TextAlign.Center
                    ),
                onClick = { offset ->
                    text.getStringAnnotations(
                        tag = tosTag,
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let { _ ->
                        Timber.d("term clicked")
                    }

                    text.getStringAnnotations(
                        tag = ppTag,
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let { _ ->
                        Timber.d("policy clicked")
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth(),
                label = stringResource(id = R.string.create_acc),
                onClick = {
                    onEvent(RegisterEvent.Register)
                },
                disabled = state.isLoading || state.username.isEmpty() ||
                        state.email.isEmpty() || state.password.isEmpty() ||
                state.emailError != null || state.passwordError != null
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.have_acc),
                    style = SubHeadlineR
                )

                Text(
                    modifier = Modifier
                        .clickable {
                            onSignIn()
                        },
                    text = stringResource(id = R.string.sign_in),
                    style = SubHeadlineB.copy(
                        color = Blue
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    EcoSwapTheme {
        RegisterScreen(
            state = RegisterState(),
            onEvent = {  },
            onSignIn = {  }
        )
    }
}