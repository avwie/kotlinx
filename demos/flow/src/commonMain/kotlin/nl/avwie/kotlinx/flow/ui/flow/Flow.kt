package nl.avwie.kotlinx.flow.ui.flow

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.onClick
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpRect
import nl.avwie.kotlinx.flow.interactors.InteractorsModule
import nl.avwie.kotlinx.flow.observers.ObserversModule
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.GridStore
import nl.avwie.kotlinx.flow.store.StoreModule
import nl.avwie.kotlinx.flow.ui.UIModule
import nl.avwie.kotlinx.flow.ui.background.Background
import nl.avwie.kotlinx.flow.ui.common.DragEventHandler
import nl.avwie.kotlinx.flow.ui.grid.Grid
import nl.avwie.kotlinx.flow.ui.icons.Icon
import nl.avwie.kotlinx.flow.ui.icons.IconEventHandler
import nl.avwie.kotlinx.flow.ui.icons.Icons
import nl.avwie.kotlinx.flow.ui.selector.Selector
import nl.avwie.kotlinx.ui.viewModel
import nl.avwie.kotlinx.utils.compose.onDrag
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
    val selectionBox by viewModel.selector.state.collectAsState()
    val grid by viewModel.grid.state.collectAsState()

    Flow(
        icons = icons,
        grid = grid,
        selectionBox = selectionBox,
        iconEventHandler = viewModel.icons,
        backgroundDragEventHandler = viewModel.selector,
        onBackgroundClick = { viewModel.icons.deselect() }
    )
}

@Composable
fun Flow(
    icons: List<IconState> = listOf(),
    selectionBox: DpRect? = null,
    grid: GridStore.GridDefinition? = null,
    iconEventHandler: IconEventHandler? = null,
    backgroundDragEventHandler: DragEventHandler? = null,
    onBackgroundClick: () -> Unit = {}
) {
    Background(
        backgroundDragEventHandler = backgroundDragEventHandler,
        onBackgroundClick = onBackgroundClick
    )

    Grid(grid = grid)

    Icons(
        icons = icons,
        iconEventHandler = iconEventHandler
    )

    Selector(rect = selectionBox)
}