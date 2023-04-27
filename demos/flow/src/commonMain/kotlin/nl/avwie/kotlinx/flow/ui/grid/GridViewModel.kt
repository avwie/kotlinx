package nl.avwie.kotlinx.flow.ui.grid

import nl.avwie.kotlinx.flow.observers.ObserveGridDefinition
import nl.avwie.kotlinx.ui.ViewModel

class GridViewModel(
    observeGridDefinition: ObserveGridDefinition,
) : ViewModel() {
    val state = observeGridDefinition.flow
}