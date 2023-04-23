package nl.avwie.kotlinx.flow.store

import nl.avwie.kotlinx.flow.state.IconState
import org.kodein.di.DI
import org.kodein.di.bindSingleton

object StoreModule {
    operator fun invoke(
        initialIconStates: List<IconState> = listOf()
    ) = DI.Module(name = "Store") {

        bindSingleton<IconsStore> {
            IconsStoreImpl(
                initialIconStates = initialIconStates
            )
        }
    }
}