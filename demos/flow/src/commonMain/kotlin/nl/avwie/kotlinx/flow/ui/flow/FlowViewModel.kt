package nl.avwie.kotlinx.flow.ui.flow

import androidx.compose.ui.unit.DpRect
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nl.avwie.kotlinx.flow.observers.ObserveSelectionBox
import nl.avwie.kotlinx.flow.store.SelectorStore
import nl.avwie.kotlinx.flow.ui.icons.IconsViewModel
import nl.avwie.kotlinx.flow.ui.selector.SelectorViewModel
import nl.avwie.kotlinx.ui.ViewModel

class FlowViewModel(
    val icons: IconsViewModel,
    val selector: SelectorViewModel,
) : ViewModel() {
    override fun dispose() {
        icons.dispose()
        selector.dispose()
        super.dispose()
    }
}