package com.cirilobido.focustimer.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cirilobido.focustimer.presentation.theme.FocusTimerTheme

@Composable @Stable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color,
    buttonColor: Color,
    onTap: () -> Unit = {},
) {
    Button(
        modifier = modifier.height(FocusTimerTheme.dimens.buttonHeightNormal),
        shape = RoundedCornerShape(FocusTimerTheme.dimens.roundedShapeNormal),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor
        ),
        onClick = { onTap() },
    ) {
        Text(
            text,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.bodyLarge,
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
fun CustomButtonPreview() {
    FocusTimerTheme {
        CustomButton(
            text = "Start",
            textColor = MaterialTheme.colorScheme.surface,
            buttonColor = MaterialTheme.colorScheme.primary
        )
    }
}