package nl.avwie.kotlinx.flow.ui.flow

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import nl.avwie.kotlinx.flow.interactors.InteractorsModule
import nl.avwie.kotlinx.flow.interactors.MoveIcon
import nl.avwie.kotlinx.flow.observers.ObserversModule
import nl.avwie.kotlinx.flow.state.FlowState
import nl.avwie.kotlinx.flow.store.StoreModule
import nl.avwie.kotlinx.flow.ui.UIModule
import nl.avwie.kotlinx.flow.ui.icons.Icon
import nl.avwie.kotlinx.ui.viewModel
import org.kodein.di.DI
import org.kodein.di.compose.withDI

@Composable fun Flow(
    storeModule: DI.Module = StoreModule(),
    observersModule: DI.Module = ObserversModule(),
    interactorsModule: DI.Module = InteractorsModule(),
    uiModule: DI.Module = UIModule()
) = withDI(
    storeModule,
    observersModule,
    interactorsModule,
    uiModule
) {
   Flow(
       viewModel = viewModel()
   )
}

@Composable
fun Flow(
    viewModel: FlowViewModel
) {
    val state by viewModel.state.collectAsState()
    Flow(
        state = state,
        moveIcon = viewModel.moveIcon
    )
}

@Composable
fun Flow(
    state: FlowState,
    moveIcon: MoveIcon
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(Color.White)
    }

    state.icons.forEach { element ->
        Icon(
            icon = element,
            moveIcon = moveIcon
        )
    }
}