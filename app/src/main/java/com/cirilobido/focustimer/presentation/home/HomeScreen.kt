package com.cirilobido.focustimer.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.cirilobido.focustimer.ui.theme.FocusTimerTheme
import com.cirilobido.focustimer.R

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(FocusTimerTheme.dimens.paddingMedium)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                modifier = Modifier.size(FocusTimerTheme.dimens.iconSizeNormal),
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
        AutoResizedText(
            text = "focus\ntimer",
            textStyle = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        Row {
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot()
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary)
            Spacer(modifier = Modifier.width(FocusTimerTheme.dimens.spacerNormal))
            CircleDot(color = MaterialTheme.colorScheme.tertiary)
        }
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        TimerSession()
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerSmall))
        TimerTypeSession()
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerLarge))
        ButtonSession()
        Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        InformationSession(modifier = Modifier.weight(1f))
    }
}

/**
 * This text time can be rescaled to adjust the font size
 * If the screen is too short, the user may see some flicker
 * while rescaling the size
 */
@Composable
fun AutoResizedText(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.displayLarge
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

@Composable
fun CircleDot(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier
            .size(FocusTimerTheme.dimens.iconSizeSmall)
            .clip(CircleShape)
            .background(color)
    )
}

@Composable
fun TimerSession(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier
                    .size(FocusTimerTheme.dimens.iconSizeNormal)
                    .border(
                        FocusTimerTheme.dimens.borderNormal,
                        MaterialTheme.colorScheme.primary,
                        CircleShape
                    )
                    .padding(FocusTimerTheme.dimens.paddingSmall),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_minus),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }
        AutoResizedText(
            modifier = Modifier
                .fillMaxWidth()
                .weight(6f)
                .align(Alignment.CenterVertically),
            text = "25:00",
            textStyle = MaterialTheme.typography.displayLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                modifier = Modifier
                    .size(FocusTimerTheme.dimens.iconSizeNormal)
                    .border(
                        FocusTimerTheme.dimens.borderNormal,
                        MaterialTheme.colorScheme.primary,
                        CircleShape
                    )
                    .padding(FocusTimerTheme.dimens.paddingSmall),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            Spacer(modifier = Modifier.height(FocusTimerTheme.dimens.spacerMedium))
        }
    }
}

@Composable
fun TimerTypeSession(modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(FocusTimerTheme.dimens.spacerLarge),
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(FocusTimerTheme.dimens.paddingNormal),
        horizontalArrangement = Arrangement.spacedBy(FocusTimerTheme.dimens.paddingNormal)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(FocusTimerTheme.dimens.paddingSmall),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Focus Time",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(FocusTimerTheme.dimens.paddingSmall),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Short Break",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(FocusTimerTheme.dimens.paddingSmall),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Long Break",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Composable
fun ButtonSession(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.height(FocusTimerTheme.dimens.buttonHeightNormal),
            shape = RoundedCornerShape(FocusTimerTheme.dimens.roundedShapeNormal),
            onClick = { },
        ) {
            Text(
                "Start",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.surface
            )
        }
        Button(
            modifier = Modifier.height(FocusTimerTheme.dimens.buttonHeightNormal),
            shape = RoundedCornerShape(FocusTimerTheme.dimens.roundedShapeNormal),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            onClick = { },
        ) {
            Text(
                "Reset",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun InformationSession(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    "1",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "today rounds",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    "2h 35m",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "today time",
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun HomeScreenPreview() {
    FocusTimerTheme {
        HomeScreen()
    }
}