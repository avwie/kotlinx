package nl.avwie.kotlinx.flow.ui.flow

import nl.avwie.kotlinx.flow.ui.icons.IconsViewModel
import nl.avwie.kotlinx.ui.ViewModel

class FlowViewModel(
    val icons: IconsViewModel
) : ViewModel() {

    override fun dispose() {
        icons.dispose()
        super.dispose()
    }
}