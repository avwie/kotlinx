package nl.avwie.kotlinx.flow.ui.flow

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import nl.avwie.kotlinx.flow.interactors.InteractorsModule
import nl.avwie.kotlinx.flow.observers.ObserversModule
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
    val icons by viewModel.icons.state.collectAsState()

    Flow(
        icons = icons,
        onIconClick = { icon -> viewModel.icons.select(icon) },
        onIconDrag = { icon, offset -> viewModel.icons.drag(icon, offset) },
        onBackgroundClick = { viewModel.icons.deselect() }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Flow(
    icons: List<IconState>,
    onIconClick: (IconState) -> Unit = { },
    onIconDrag: (IconState, DpOffset) -> Unit = { _, _ -> },
    onBackgroundClick: () -> Unit = {}
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .onClick { onBackgroundClick() }
            .onDrag(
                onDragStart = { _ -> },
                onDrag = { _ -> },
                onDragEnd = {}
            )
    ) {
        drawRect(Color.White)
    }

    icons.forEach { element ->
        Icon(
            iconState = element,
            onClick = { onIconClick(element) },
            onDrag = { offset -> onIconDrag(element, offset) }
        )
    }
}