package nl.avwie.kotlinx.flow.ui.common

import androidx.compose.ui.unit.DpOffset

interface DragEventHandler {
    fun onDragStart(offset: DpOffset)

    fun onDrag(offset: DpOffset)

    fun onDragEnd()
}