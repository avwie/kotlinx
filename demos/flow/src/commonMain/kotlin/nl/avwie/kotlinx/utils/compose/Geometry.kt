package nl.avwie.kotlinx.utils.compose

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpRect

fun DpRect.grow(amount: Dp): DpRect = this.copy(
    left = left - amount,
    top = top - amount,
    right = right + amount,
    bottom = bottom + amount
)