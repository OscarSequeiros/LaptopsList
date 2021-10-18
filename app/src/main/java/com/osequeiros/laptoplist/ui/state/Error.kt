package com.osequeiros.laptoplist.ui.state

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.osequeiros.laptoplist.R

@Composable
fun ErrorTemplate(retryAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.padding(bottom = 60.dp),
            painter = painterResource(id = R.drawable.ic_error_loading),
            contentDescription = stringResource(id = R.string.error_image),
            alignment = Alignment.Center
        )
        Button(
            border = null,
            onClick = { retryAction() },
            content = {
                Text(
                    text = stringResource(id = R.string.retry_action),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.subtitle1
                )
            }
        )
    }
}