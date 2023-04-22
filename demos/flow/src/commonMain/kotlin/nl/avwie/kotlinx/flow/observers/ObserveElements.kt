package nl.avwie.kotlinx.flow.observers

import kotlinx.coroutines.flow.Flow
import nl.avwie.kotlinx.flow.state.Element
import nl.avwie.kotlinx.flow.store.ElementsStore

class ObserveElements(elementsStore: ElementsStore) {
    val flow : Flow<List<Element>> = elementsStore.elements
}