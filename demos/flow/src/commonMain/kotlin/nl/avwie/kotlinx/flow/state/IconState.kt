package nl.avwie.kotlinx.flow.state

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.Uuid

data class IconState (
    val id: Uuid,
    val name: String,
    val position: DpOffset,
    val type: IconType,
    val selected: Boolean = false
) {
    val centroid: DpOffset = position + DpOffset(type.size.width / 2, type.size.height / 2)
    val rect: DpRect = DpRect(position, type.size)
}

sealed interface IconType {
    val size: DpSize
    object Start : IconType {
        override val size = DpSize(80.dp, 30.dp)
    }
    object Action : IconType {
        override val size = DpSize(130.dp, 80.dp)
    }

    object End : IconType {
        override val size = DpSize(80.dp, 30.dp)
    }
}