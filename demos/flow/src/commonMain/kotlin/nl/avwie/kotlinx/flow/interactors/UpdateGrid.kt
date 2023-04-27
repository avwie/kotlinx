package nl.avwie.kotlinx.flow.interactors

import nl.avwie.kotlinx.flow.store.GridStore

interface UpdateGrid {
    operator fun invoke(update: (GridStore.GridDefinition) -> GridStore.GridDefinition)
}

class UpdateGridImpl(
    private val gridStore: GridStore
) : UpdateGrid {
    override fun invoke(update: (GridStore.GridDefinition) -> GridStore.GridDefinition) {
        gridStore.updateGridDefinition(update)
    }
}