package nl.avwie.kotlinx.flow.ui.icons

import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.state.IconState

interface IconEventHandler {
    fun onIconClick(icon: IconState)
    fun onIconDrag(icon: IconState, offset: DpOffset)
}