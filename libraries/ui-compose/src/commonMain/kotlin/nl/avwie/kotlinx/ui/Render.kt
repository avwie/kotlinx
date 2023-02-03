package nl.avwie.kotlinx.ui

import androidx.compose.runtime.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch

@Composable
fun rememberFrameTimeFLow(): Flow<Long> {
    val flow = remember { MutableSharedFlow<Long>() }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameMillis { frameTimeMillis ->
                launch {
                    flow.emit(frameTimeMillis)
                }
            }
        }
    }

    return flow.conflate()
}