package nl.avwie.kotlinx.flow.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.benasher44.uuid.Uuid
import com.benasher44.uuid.uuid4
import nl.avwie.kotlinx.flow.observers.ObserversModule
import nl.avwie.kotlinx.flow.state.Element
import nl.avwie.kotlinx.flow.state.FlowState
import nl.avwie.kotlinx.flow.store.ElementsStore
import nl.avwie.kotlinx.flow.store.StoreModule
import nl.avwie.kotlinx.ui.viewModel
import org.kodein.di.DI
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

@Composable()
fun Flow() {
    withDI(
        StoreModule(),
        ObserversModule(),
        UIModule()
    ) {
        val elementsStore : ElementsStore by rememberInstance()

        LaunchedEffect(Unit) {
            elementsStore.addElement(
                Element(
                    id = uuid4(),
                    name ="Test",
                    position = Pair(200, 300)
                )
            )

            elementsStore.addElement(
                Element(
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
fun Flow(viewModel: FlowViewModel) {
    val state by viewModel.state.collectAsState()
    Flow(state)
}

@Composable
fun Flow(
    state: FlowState
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        state.elements.forEach { element ->
            Box(
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .offset { IntOffset(element.position.first, element.position.second) }
                    .background(Color.Blue)
            ){}
        }
    }
}