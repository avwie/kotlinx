package nl.avwie.kotlinx.flow.state

import androidx.compose.ui.geometry.Offset
import com.benasher44.uuid.Uuid

data class IconState (
    val id: Uuid,
    val name: String,
    val position: Offset,
    val selected: Boolean = false
)