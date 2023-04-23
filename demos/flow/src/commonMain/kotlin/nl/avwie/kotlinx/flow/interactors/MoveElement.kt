package nl.avwie.kotlinx.flow.interactors

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.IntOffset
import nl.avwie.kotlinx.flow.state.Element
import nl.avwie.kotlinx.flow.store.ElementsStore

interface MoveElement {
    operator fun invoke(element: Element, offset: IntOffset)
    operator fun invoke(element: Element, offset: Offset, density: Float)
}

class MoveElementImpl(
    private val elementsStore: ElementsStore
) : MoveElement {
    override operator fun invoke(element: Element, offset: IntOffset) {
        elementsStore.updateElement(element) {
            it.copy(
                position = Pair(
                    it.position.first + offset.x,
                    it.position.second + offset.y
                )
            )
        }
    }

    override operator fun invoke(element: Element, offset: Offset, density: Float) {
        invoke(element, IntOffset(offset.x.toInt() / density.toInt(), offset.y.toInt() / density.toInt()))
    }
}