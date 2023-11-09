package com.cirilobido.focustimer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cirilobido.focustimer.presentation.theme.FocusTimerTheme

/**
 * This text time can be rescaled to adjust the font size
 * If the screen is too short, the user may see some flicker
 * while rescaling the size
 */
@Composable @Stable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.displayLarge,
) {
    var timeTextStyle by remember { mutableStateOf(textStyle) }
    Text(
        text,
        modifier = modifier
            .fillMaxWidth(),
        softWrap = false,
        style = timeTextStyle,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                timeTextStyle = textStyle.copy(
                    fontSize = timeTextStyle.fontSize * 0.95
                )
            }
        }
    )
}

@Preview(
    name = "Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun AutoResizedTextPreview() {
    FocusTimerTheme {
        AutoResizedText(
            modifier = Modifier.fillMaxWidth(),
            text = "Full Text",
            textStyle = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )
    }
}