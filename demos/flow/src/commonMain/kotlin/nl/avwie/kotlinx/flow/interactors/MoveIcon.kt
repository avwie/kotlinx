package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import nl.avwie.kotlinx.flow.state.Icon
import nl.avwie.kotlinx.flow.store.IconsStore

interface MoveIcon {
    operator fun invoke(icon: Icon, offset: IntOffset)
    operator fun invoke(icon: Icon, offset: Offset, density: Float)
}

class MoveIconImpl(
    private val iconsStore: IconsStore
) : MoveIcon {
    override operator fun invoke(icon: Icon, offset: IntOffset) {
        iconsStore.updateElement(icon) {
            it.copy(
                position = Pair(
                    it.position.first + offset.x,
                    it.position.second + offset.y
                )
            )
        }
    }

    override operator fun invoke(icon: Icon, offset: Offset, density: Float) {
        invoke(icon, IntOffset(offset.x.toInt() / density.toInt(), offset.y.toInt() / density.toInt()))
    }
}