package nl.avwie.kotlinx.flow

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.window.singleWindowApplication
import com.benasher44.uuid.uuid4
import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.store.StoreModule
import nl.avwie.kotlinx.flow.ui.flow.Flow

fun main() {
    singleWindowApplication {
        Flow(
            storeModule = StoreModule(
                initialIconStates = listOf(
                    IconState(uuid4(), "Foo", Offset(100f, 100f), selected = true),
                    IconState(uuid4(), "Bar", Offset(200f, 200f)),
                    IconState(uuid4(), "Baz", Offset(300f, 300f)),
                )
            ),
        )
    }
}