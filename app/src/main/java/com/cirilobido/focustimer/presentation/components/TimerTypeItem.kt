package com.cirilobido.focustimer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cirilobido.focustimer.presentation.theme.FocusTimerTheme

@Composable
fun TimerTypeItem(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    onTap: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(FocusTimerTheme.dimens.paddingSmall)
            .clickable { onTap() },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            style = MaterialTheme.typography.bodyMedium,
            color = textColor
        )
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun TimerTypeItemPreview() {
    FocusTimerTheme {
        TimerTypeItem(
            text = "Focus",
            textColor = MaterialTheme.colorScheme.primary
        )
    }
}