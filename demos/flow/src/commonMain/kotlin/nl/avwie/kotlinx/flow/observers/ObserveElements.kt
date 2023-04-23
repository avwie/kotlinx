package nl.avwie.kotlinx.flow.observers

import kotlinx.coroutines.flow.Flow
import nl.avwie.kotlinx.flow.state.Icon
import nl.avwie.kotlinx.flow.store.IconsStore

class ObserveElements(iconsStore: IconsStore) {
    val flow : Flow<List<Icon>> = iconsStore.elements
}