package nl.avwie.kotlinx.utils.compose

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset

fun Density.toIntOffset(dpOffset: DpOffset): IntOffset {
    return IntOffset(dpOffset.x.toPx().toInt(), dpOffset.y.toPx().toInt())
}

fun Density.toDpOffset(offset: Offset): DpOffset = DpOffset(offset.x.toDp(), offset.y.toDp())