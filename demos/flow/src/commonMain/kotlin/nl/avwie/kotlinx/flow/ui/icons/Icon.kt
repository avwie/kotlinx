package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
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
    onDrag: (DpOffset) -> Unit = { }
) {
    val density = LocalDensity.current

    Canvas(
        modifier = Modifier
            .size(width = iconState.type.size.width, height = iconState.type.size.height)
            .offset { toIntOffset(iconState.position) }
            .onClick { onClick() }
            .onDrag { offset -> onDrag(density.toDpOffset(offset)) }
            .zIndex(if (iconState.selected) 1f else 0f)
    ) {
        when (iconState.type) {
            IconType.Start -> StartIcon(iconState)
            IconType.Action -> ActionIcon(iconState)
            IconType.End -> EndIcon(iconState)
        }
    }

    if (iconState.selected) {
        val selectorRect = DpRect(
            origin = iconState.position,
            size = iconState.type.size
        ).grow(4.dp)

        SelectionBox(
            modifier = Modifier
                .size(selectorRect.size)
                .offset { IntOffset(x = selectorRect.left.toPx().toInt(), y = selectorRect.top.toPx().toInt()) }
        )
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