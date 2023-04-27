package nl.avwie.kotlinx.flow.observers

import kotlinx.coroutines.flow.StateFlow
import nl.avwie.kotlinx.flow.store.GridStore

interface ObserveGridDefinition {
    val flow: StateFlow<GridStore.GridDefinition>
}

class ObserveGridDefinitionImpl(
    gridStore: GridStore
) : ObserveGridDefinition {
    override val flow: StateFlow<GridStore.GridDefinition> = gridStore.gridDefinition
}