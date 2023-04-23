package nl.avwie.kotlinx.flow.store

import com.benasher44.uuid.Uuid
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.avwie.kotlinx.flow.state.IconState

interface IconsStore {
    val icons: StateFlow<List<IconState>>
    fun addIcon(iconState: IconState)
    fun removeIcon(id: Uuid)
    fun updateIcon(iconState: IconState, update: (IconState) -> IconState)
}

class IconsStoreImpl(
    initialIconStates: List<IconState> = listOf()
) : IconsStore {
    private val _elements = MutableStateFlow(initialIconStates.toPersistentList())
    override val icons = _elements.asStateFlow()

    override fun addIcon(iconState: IconState) = _elements.update { it.add(iconState) }
    override fun removeIcon(id: Uuid) = _elements.update { list ->
        list.firstOrNull { element -> element.id == id }?.let { element ->
            list.remove(element)
        } ?: list
    }

    override fun updateIcon(iconState: IconState, update: (IconState) -> IconState) = _elements.update { list ->
        list.indexOfFirst { it.id == iconState.id }.takeIf { it >= 0 }?.let { index ->
            list.set(index, update(list[index]))
        } ?: list
    }
}