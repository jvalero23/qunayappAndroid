package com.pe.mascotapp.vistas.extension

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

/**
 * This helps to preview images in Compose Preview, specially for async image composable.
 */
@Composable
fun debugPlaceholder(@DrawableRes debugPreview: Int) =
    if (LocalInspectionMode.current) {
        painterResource(id = debugPreview)
    } else {
        null
    }

/**
 * This function is used to provide a placeholder image for asynchronous image loading in Compose Preview.
 *
 * @param vector The ImageVector that should be used to create the placeholder painter.
 * @return A painter created from the provided ImageVector if in LocalInspectionMode, null otherwise.
 * */
@Composable
fun debugPlaceholder(vector: ImageVector) =
    if (LocalInspectionMode.current) {
        rememberVectorPainter(image = vector)
    } else {
        null
    }

@Composable
fun debugBorder(color: Color) = if (LocalInspectionMode.current) BorderStroke(
    width = 1.dp, color = color
) else null