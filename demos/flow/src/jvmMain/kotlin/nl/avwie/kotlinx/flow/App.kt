package nl.avwie.kotlinx.flow

import androidx.compose.ui.window.singleWindowApplication
import com.benasher44.uuid.uuid4
import nl.avwie.kotlinx.flow.state.Icon
import nl.avwie.kotlinx.flow.store.StoreModule
import nl.avwie.kotlinx.flow.ui.flow.Flow

fun main() {
    singleWindowApplication {
        Flow(
            storeModule = StoreModule(
                initialIcons = listOf(
                    Icon(uuid4(), "Foo", 100 to 100),
                    Icon(uuid4(), "Bar", 200 to 200),
                    Icon(uuid4(), "Baz", 300 to 300),
                )
            ),
        )
    }
}