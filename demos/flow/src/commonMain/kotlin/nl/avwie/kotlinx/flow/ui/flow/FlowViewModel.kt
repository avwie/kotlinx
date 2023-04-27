package nl.avwie.kotlinx.flow.ui.flow

import nl.avwie.kotlinx.flow.ui.grid.GridViewModel
import nl.avwie.kotlinx.flow.ui.icons.IconsViewModel
import nl.avwie.kotlinx.flow.ui.selector.SelectorViewModel
import nl.avwie.kotlinx.ui.ViewModel

class FlowViewModel(
    val icons: IconsViewModel,
    val selector: SelectorViewModel,
    val grid: GridViewModel,
) : ViewModel() {
    override fun dispose() {
        icons.dispose()
        selector.dispose()
        grid.dispose()
        super.dispose()
    }
}