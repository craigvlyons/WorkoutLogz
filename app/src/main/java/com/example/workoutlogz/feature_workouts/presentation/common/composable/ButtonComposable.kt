/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.workoutlogz.ui.theme.Shapes

@Composable
fun BasicTextButton(text: String, modifier: Modifier, action: () -> Unit) {
    TextButton(
        onClick = action,
        modifier = modifier.clip(shape = Shapes.large)
    ) { Text(text = text) }
}

@Composable
fun BasicButton(text: String, modifier: Modifier, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = modifier.clip(shape = Shapes.large),
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}

@Composable
fun ActionIconButton(
    onClick: () -> Unit,
    @DrawableRes icon: Int,
) {
    IconButton(onClick = onClick, modifier = Modifier.clip(shape = Shapes.large)) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Custom Icon",
            modifier = Modifier.size(24.dp), // Adjust the size as needed
            tint = MaterialTheme.colors.secondaryVariant // green
        )
    }
}

@Composable
fun ActionIconTextButton(
    onClick: () -> Unit,
    @DrawableRes icon: Int,
    title: String,
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .background(MaterialTheme.colors.primaryVariant)
            // .clip(shape = RoundedCornerShape(90))
            .padding(all = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Custom Icon",
            modifier = Modifier.size(24.dp), // Adjust the size as needed
            tint = MaterialTheme.colors.secondaryVariant // green
        )
        Text(
            text = title,
            style = MaterialTheme.typography.h1,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
fun ActionConfirmButton(text: String, action: () -> Unit, enable: Boolean) {
    Button(
        onClick = action,
        modifier = Modifier
            .height(40.dp)
            .clip(shape = RoundedCornerShape(90)),
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.onPrimary,
        ),
        enabled = enable
    ) {
        Text(
            text = text,
        )
    }
}
@Composable
fun DialogConfirmButton(text: String, action: () -> Unit) {
    Button(
        onClick = action,
        modifier = Modifier
            .height(40.dp)
            .clip(shape = RoundedCornerShape(90)),
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
        ),
    ) {
        Text(text = text)
    }
}

@Composable
fun DialogCancelButton(text: String, action: () -> Unit) {
    Button(
        onClick = action,
        colors =
        ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondaryVariant,
            contentColor = MaterialTheme.colors.primary
        ),
        modifier = Modifier
            .height(40.dp)
            .clip(shape = RoundedCornerShape(90))
    ) {
        Text(text = text)
    }
}

@Composable
fun CancelTextButton(text: String, modifier: Modifier = Modifier, action: () -> Unit) {
    TextButton(onClick = action, modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.subtitle1,
            color = Color.Red
        )
    }
}

@Preview
@Composable
fun PreviewButtons() {
    Column {
        BasicTextButton(text = "Basic text Button", modifier = Modifier) { }
        BasicButton(text = "Basic Button", modifier = Modifier) { }
        ActionIconButton(
            onClick = { /*TODO*/ },
            icon = com.example.workoutlogz.R.drawable.circle_check
        )
        ActionIconTextButton(
            onClick = { },
            icon = com.example.workoutlogz.R.drawable.circle_check,
            title = "Action icon button"
        )
        DialogConfirmButton(text = "Confirm") { }
        DialogCancelButton(text = "Cancel") { }
        CancelTextButton(text = "Cancel", modifier = Modifier) { }
        ActionConfirmButton(text = "ADD Confirm", action = { /*TODO*/ }, enable = true)
    }
}