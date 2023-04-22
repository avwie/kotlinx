package nl.avwie.kotlinx.flow.store

import com.benasher44.uuid.Uuid
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.avwie.kotlinx.flow.state.Element

class ElementsStore {
    private val _elements = MutableStateFlow(persistentListOf<Element>())
    val elements = _elements.asStateFlow()

    fun addElement(element: Element) = _elements.update { it.add(element) }
    fun removeElement(id: Uuid) = _elements.update { list ->
        list.firstOrNull { element -> element.id == id }?.let { element ->
            list.remove(element)
        } ?: list
    }
}