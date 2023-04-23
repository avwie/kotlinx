package nl.avwie.kotlinx.flow.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.benasher44.uuid.uuid4
import nl.avwie.kotlinx.flow.interactors.InteractorsModule
import nl.avwie.kotlinx.flow.interactors.MoveElement
import nl.avwie.kotlinx.flow.observers.ObserversModule
import nl.avwie.kotlinx.flow.state.Element as FlowElement
import nl.avwie.kotlinx.flow.state.FlowState
import nl.avwie.kotlinx.flow.store.ElementsStore
import nl.avwie.kotlinx.flow.store.StoreModule
import nl.avwie.kotlinx.ui.viewModel
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

@Composable()
fun Flow() {
    withDI(
        StoreModule(),
        ObserversModule(),
        InteractorsModule(),
        UIModule()
    ) {
        val elementsStore : ElementsStore by rememberInstance()

        LaunchedEffect(Unit) {
            elementsStore.addElement(
                FlowElement(
                    id = uuid4(),
                    name ="Test",
                    position = Pair(200, 300)
                )
            )

            elementsStore.addElement(
                FlowElement(
                    id = uuid4(),
                    name ="Test 2",
                    position = Pair(500, 100)
                )
            )
        }

        Flow(viewModel = viewModel())
    }
}

@Composable
fun Flow(
    viewModel: FlowViewModel
) {
    val state by viewModel.state.collectAsState()
    Flow(
        state = state,
        moveElement = viewModel.moveElement
    )
}

@Composable
fun Flow(
    state: FlowState,
    moveElement: MoveElement
) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(Color.White)
    }

    state.elements.forEach { element ->
        Element(
            element = element,
            moveElement = moveElement
        )
    }
}