package nl.avwie.kotlinx.flow.ui.flow

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import nl.avwie.kotlinx.flow.interactors.InteractorsModule
import nl.avwie.kotlinx.flow.observers.ObserversModule
import nl.avwie.kotlinx.flow.state.FlowState
import nl.avwie.kotlinx.flow.state.IconState
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
        onDragIcon = { icon, offset -> viewModel.dragIcon(icon, offset) },
        onBackgroundClick = { viewModel.selectIcon(null) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Flow(
    state: FlowState,
    onDragIcon: (IconState, Offset) -> Unit = { _, _ -> },
    onBackgroundClick: () -> Unit = {}
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .onClick { onBackgroundClick() }
    ) {
        drawRect(Color.White)
    }

    state.iconStates.forEach { element ->
        Icon(
            iconState = element,
            onDragIcon = { offset -> onDragIcon(element, offset) }
        )
    }
}