package nl.avwie.kotlinx.ui

import androidx.compose.runtime.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@Composable
fun rememberFrameTimeFLow(): Flow<Long> {
    val flow = remember { MutableSharedFlow<Long>() }

    LaunchedEffect(Unit) {
        while (true) {
            withFrameNanos { frameTimeNanos ->
                launch {
                    flow.emit(frameTimeNanos)
                }
            }
        }
    }

    return flow.conflate()
}