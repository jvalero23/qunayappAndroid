package com.pe.mascotapp.vistas.event_history.main.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pe.mascotapp.R
import com.pe.mascotapp.colorMediumBlue
import com.pe.mascotapp.vistas.extension.debugPlaceholder
import com.pe.mascotapp.vistas.ui.theme.MascotappTheme

@Composable
fun EventHistoryItem(
    startTime: String,
    endTime: String,
    onClickSeeAll: () -> Unit,
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
    showSeparator: Boolean = false,
) {
    Row(
        modifier = modifier
    ) {
        TimerSection(
            startTime = startTime,
            endTime = endTime
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column {
            DetailSection(
                title = "Comportamiento",
                description = "ladró intensamente cuando el cartero se acercó a la puerta, luego se calmo y test con test de test",
                imageUri = imageUri,
                onClickSeeAll = onClickSeeAll
            )
            if (showSeparator) {
                HorizontalDivider(color = colorMediumBlue)
            }
        }
    }
}

@Composable
private fun DetailSection(
    title: String,
    onClickSeeAll: () -> Unit,
    description: String,
    modifier: Modifier = Modifier,
    imageUri: Uri? = null,
) {
    Row(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUri)
                .build(),
            contentDescription = null,
            placeholder = debugPlaceholder(R.drawable.perro1),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = title,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp,
                lineHeight = 19.36.sp,
                color = colorMediumBlue
            )
            Text(
                text = description,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
                lineHeight = 15.73.sp,
                color = colorMediumBlue
            )
            Text(
                text = "Ver todo",
                fontSize = 12.sp,
                fontWeight = FontWeight.W800,
                lineHeight = 10.sp,
                color = colorMediumBlue,
                modifier = Modifier
                    .minimumInteractiveComponentSize()
                    .clickable(onClick = onClickSeeAll)
            )
        }
    }
}

@Composable
private fun TimerSection(
    startTime: String,
    endTime: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = colorMediumBlue,
                    shape = RoundedCornerShape(2.5.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            // Todo: aqui como manejan las variaciones de este icono?
            Icon(
                painter = painterResource(R.drawable.ic_paseo),
                tint = Color.White,
                contentDescription = null,
                modifier = Modifier.padding(6.4.dp)
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = startTime,
            fontSize = 16.sp,
            fontWeight = FontWeight.W600,
            lineHeight = 19.36.sp
        )
        Text(
            text = endTime,
            fontSize = 11.sp,
            fontWeight = FontWeight.W300,
            lineHeight = 13.31.sp
        )
    }
}

@Preview
@Composable
private fun EventHistoryItemPreview() {
    MascotappTheme {
        EventHistoryItem(
            showSeparator = true,
            startTime = "11:35",
            endTime = "13:05",
            onClickSeeAll = {}
        )
    }
}