package nl.avwie.kotlinx.flow.observers

import androidx.compose.ui.unit.DpRect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import nl.avwie.kotlinx.flow.store.SelectorStore

interface ObserveSelectionBox {
    val selectionEvents : Flow<SelectorStore.Event>
    val selectionBox: StateFlow<DpRect?>
}

class ObserveSelectionBoxImpl(
    private val store: SelectorStore
) : ObserveSelectionBox {
    override val selectionEvents: Flow<SelectorStore.Event> = store.events
    override val selectionBox: StateFlow<DpRect?> = store.selectionBox
}
