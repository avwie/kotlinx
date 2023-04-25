package nl.avwie.kotlinx.flow.state

import androidx.compose.ui.unit.DpOffset
import com.benasher44.uuid.Uuid

data class IconState (
    val id: Uuid,
    val name: String,
    val position: DpOffset,
    val selected: Boolean = false
)