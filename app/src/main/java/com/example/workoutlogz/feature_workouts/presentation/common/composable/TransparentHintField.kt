package com.example.workoutlogz.feature_workouts.presentation.common.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TransparentHintField(
    text: String,
    hint: String,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            //.padding(10.dp)
            .border(0.5.dp, Color.DarkGray, RoundedCornerShape(size = 16.dp))
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .onFocusChanged { onFocusChange(it) },
            )
        }
        if (isHintVisible) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterStart),
                text = hint,
                style = TextStyle(fontSize = 20.sp),
                color = MaterialTheme.colors.secondary,
            )
        }
    }
}

@Composable
fun SearchHintField(
    text: String,
    hint: String,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(0.5.dp, Color.DarkGray, RoundedCornerShape(size = 16.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(start = 5.dp)
                .height(35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(6.dp))
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .onFocusChanged { onFocusChange(it) },
            )
        }
        if (isHintVisible) {
            Text(
                modifier = Modifier
                    .padding(start = 35.dp)
                    .align(Alignment.CenterStart),
                text = hint,
                style = TextStyle(fontSize = 20.sp),
                color = Color.LightGray,
            )
        }
    }
}

@Composable
fun CommentHintField(
    text: String,
    hint: String,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit,
    modifier: Modifier = Modifier,
    minLines: Int = 3
) {
    Box(
        modifier = modifier
            //.padding(10.dp)
            .border(0.5.dp, Color.DarkGray, RoundedCornerShape(size = 16.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = text,
                onValueChange = onValueChange,
                singleLine = singleLine,
                textStyle = textStyle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .onFocusChanged { onFocusChange(it) },
                minLines = minLines
            )
        }
        if (isHintVisible) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterStart),
                text = hint,
                style = TextStyle(fontSize = 20.sp),
                color = MaterialTheme.colors.secondary,
            )
        }
    }
}

@Preview
@Composable
fun previewTextFields() {
    Column {
        TransparentHintField(
            text = "text area",
            hint = "type something...",
            onValueChange = {},
            textStyle = MaterialTheme.typography.h1,
            onFocusChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        SearchHintField(
            text = "text area",
            hint = "type something...",
            onValueChange = {},
            textStyle = MaterialTheme.typography.h1,
            onFocusChange = {})
        Spacer(modifier = Modifier.height(16.dp))
        CommentHintField(
            text = "comment box",
            hint = "comment box...",
            isHintVisible = true,
            onValueChange = {},
            textStyle = MaterialTheme.typography.h1,
            onFocusChange = {})
    }
}