package nl.avwie.kotlinx.flow.observers

import androidx.compose.ui.unit.DpRect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import nl.avwie.kotlinx.flow.store.SelectorStore

interface ObserveSelector {
    val selectorEvents : Flow<SelectorStore.Event>
    val selectorBox: StateFlow<DpRect?>
}

class ObserveSelectorImpl(
    private val store: SelectorStore
) : ObserveSelector {
    override val selectorEvents: Flow<SelectorStore.Event> = store.events
    override val selectorBox: StateFlow<DpRect?> = store.selectionBox
}
