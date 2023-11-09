package com.cirilobido.focustimer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cirilobido.focustimer.presentation.theme.FocusTimerTheme

@Composable
fun InformationItem(
    modifier: Modifier = Modifier,
    text: String,
    label: String
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            label,
            modifier = Modifier
                .fillMaxWidth(),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun InformationItemPreview() {
    FocusTimerTheme {
        InformationItem(
            text = "35",
            label = "today round"
        )
    }
}