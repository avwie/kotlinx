package nl.avwie.kotlinx.flow.store

import com.benasher44.uuid.Uuid
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.avwie.kotlinx.flow.state.Icon

class IconsStore(
    initialIcons: List<Icon> = listOf()
) {
    private val _elements = MutableStateFlow(initialIcons.toPersistentList())
    val elements = _elements.asStateFlow()

    fun addElement(icon: Icon) = _elements.update { it.add(icon) }
    fun removeElement(id: Uuid) = _elements.update { list ->
        list.firstOrNull { element -> element.id == id }?.let { element ->
            list.remove(element)
        } ?: list
    }

    fun updateElement(icon: Icon, update: (Icon) -> Icon) = _elements.update { list ->
        list.indexOfFirst { it.id == icon.id }.takeIf { it >= 0 }?.let { index ->
            list.set(index, update(icon))
        } ?: list
    }
}