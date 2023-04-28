package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.state.IconType
import nl.avwie.kotlinx.flow.ui.common.SelectionBox
import nl.avwie.kotlinx.utils.compose.grow
import nl.avwie.kotlinx.utils.compose.toDpOffset
import nl.avwie.kotlinx.utils.compose.toIntOffset

@OptIn(ExperimentalFoundationApi::class)
@Composable fun Icon(
    iconState: IconState,
    onClick: () -> Unit = { },
    onDragStart: (DpOffset) -> Unit = { },
    onDrag: (DpOffset) -> Unit = { },
    onDragEnd: () -> Unit = { },
) {
    val density = LocalDensity.current

    Box(
        modifier = Modifier
            .offset { toIntOffset(iconState.position) }
            .onDrag(
                onDragStart = { offset -> onDragStart(density.toDpOffset(offset)) },
                onDrag = { offset -> onDrag(density.toDpOffset(offset)) },
                onDragEnd = { onDragEnd() }
            )
            .onClick { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Canvas(
                modifier = Modifier
                    .size(width = iconState.type.size.width, height = iconState.type.size.height)
            ) {
                when (iconState.type) {
                    IconType.Start -> StartIcon(iconState)
                    IconType.Action -> ActionIcon(iconState)
                    IconType.End -> EndIcon(iconState)
                }
            }

            BasicText(
                text = iconState.name,
                style = TextStyle(
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center
                )
            )
        }

        if (iconState.selected) {
            val selectorRect = DpRect(
                origin = iconState.position,
                size = iconState.type.size
            ).grow(4.dp)

            SelectionBox(
                modifier = Modifier
                    .size(selectorRect.size)
                    .offset { IntOffset(x = -4.dp.toPx().toInt(), y = -4.dp.toPx().toInt()) }
            )
        }
    }
}

fun DrawScope.StartIcon(iconState: IconState) {
    drawRoundRect(
        color = Color(0xffaaffaa),
        cornerRadius = CornerRadius(size.height / 2f),
        size = size,
        style = Fill
    )

    drawRoundRect(
        color = Color(0xff00cc00),
        cornerRadius = CornerRadius(size.height / 2f),
        size = size,
        style = Stroke()
    )
}

fun DrawScope.ActionIcon(iconState: IconState) {
    drawRoundRect(
        color = Color(0xff666666),
        cornerRadius = CornerRadius(size.height / 8f),
        size = size,
        style = Stroke()
    )

    drawRoundRect(
        color = Color(0xffefefef),
        cornerRadius = CornerRadius(size.height / 8f),
        size = size
    )
}

fun DrawScope.EndIcon(iconState: IconState) {
    drawRoundRect(
        color = Color(0xffeecccc),
        cornerRadius = CornerRadius(size.height / 2f),
        size = size
    )

    drawRoundRect(
        color = Color(0xffcc0000),
        cornerRadius = CornerRadius(size.height / 2f),
        size = size,
        style = Stroke()
    )
}