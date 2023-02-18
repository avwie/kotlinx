package nl.avwie.kotlinx.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import org.kodein.di.compose.rememberFactory
import org.kodein.di.compose.rememberInstance

@Composable
inline fun <reified T : IViewModel> viewModel(): T {
    val viewModel: T by rememberInstance()

    DisposableEffect(Unit) {
        onDispose {
            viewModel.dispose()
        }
    }

    return viewModel
}

@Composable
inline fun <reified T, reified A> viewModel(arg: A): T where T : Any, T : IViewModel, A : Any {
    val viewModelFactory: (A) -> T by rememberFactory()
    val viewModel: T = remember(arg) { viewModelFactory(arg) }

    DisposableEffect(arg) {
        onDispose {
            viewModel.dispose()
        }
    }

    return viewModel
}