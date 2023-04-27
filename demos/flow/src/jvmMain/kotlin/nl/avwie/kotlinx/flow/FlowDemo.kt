package nl.avwie.kotlinx.flow

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.singleWindowApplication
import com.benasher44.uuid.uuid4
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.state.IconType
import nl.avwie.kotlinx.flow.store.StoreModule
import nl.avwie.kotlinx.flow.ui.flow.Flow

fun main() {
    singleWindowApplication {
        Flow(
            storeModule = StoreModule(
                initialIconStates = listOf(
                    IconState(uuid4(), "Start", DpOffset(100.dp, 100.dp), IconType.Start, selected = true),
                    IconState(uuid4(), "Some activity", DpOffset(200.dp, 200.dp), IconType.Action),
                    IconState(uuid4(), "End", DpOffset(300.dp, 300.dp), IconType.End),
                )
            ),
        )
    }
}