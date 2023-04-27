package nl.avwie.kotlinx.flow.store

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface GridStore {
    data class GridDefinition(
        val rowHeight: Dp,
        val columnWidth: Dp
    ) {
        companion object {
            val DEFAULT = GridDefinition(120.dp, 180.dp)
        }
    }

    val gridDefinition: StateFlow<GridDefinition>

    fun updateGridDefinition(update: (GridDefinition) -> GridDefinition)
}

class GridStoreImpl : GridStore {

    private val _gridDefinition = MutableStateFlow(GridStore.GridDefinition.DEFAULT)

    override val gridDefinition: StateFlow<GridStore.GridDefinition> = _gridDefinition.asStateFlow()

    override fun updateGridDefinition(update: (GridStore.GridDefinition) -> GridStore.GridDefinition) = _gridDefinition.update {
        update(it)
    }
}