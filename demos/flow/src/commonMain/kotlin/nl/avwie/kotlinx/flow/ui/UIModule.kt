package nl.avwie.kotlinx.flow.ui

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

object UIModule {
    operator fun invoke() = DI.Module(name = "UI") {
        bindSingleton {
            FlowViewModel(
                observeElements = instance(),
                moveElement = instance()
            )
        }
    }
}