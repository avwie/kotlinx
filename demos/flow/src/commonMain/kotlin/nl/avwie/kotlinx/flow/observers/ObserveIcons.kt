package nl.avwie.kotlinx.flow.observers

import kotlinx.coroutines.flow.StateFlow
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.IconsStore
import nl.avwie.kotlinx.utils.coroutines.mapSync

interface ObserveIcons {
    val flow: StateFlow<List<IconState>>

    fun filter(predicate: (IconState) -> Boolean): StateFlow<List<IconState>> {
        return flow.mapSync { icons ->
            icons.filter(predicate)
        }
    }
}

class ObserveIconsImpl(iconsStore: IconsStore) : ObserveIcons {
   override val flow : StateFlow<List<IconState>> = iconsStore.icons
}