package nl.avwie.kotlinx.flow.store

import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface SelectorStore {
    sealed interface Event {
        data class Finished(val rect: DpRect) : Event
    }

    val events: Flow<Event>

    val selectionBox: StateFlow<DpRect?>

    fun dragStart(offset: DpOffset)

    fun drag(offset: DpOffset)

    fun dragEnd()
}

class SelectorStoreImpl(
    private val scope: CoroutineScope
) : SelectorStore {

    private var start: DpOffset? = null
    private var end: DpOffset? = null

    private val _events = MutableSharedFlow<SelectorStore.Event>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    override val events: Flow<SelectorStore.Event> = _events

    private val _selectionBox = MutableStateFlow(null as DpRect?)

    override val selectionBox: StateFlow<DpRect?> = _selectionBox

    override fun dragStart(offset: DpOffset) {
        start = offset
        end = offset
        updateRect()
    }

    override fun drag(offset: DpOffset) {
        end = end?.plus(offset)
        updateRect()
    }

    override fun dragEnd() {
        val rect = _selectionBox.value
        if (rect != null) scope.launch { _events.emit(SelectorStore.Event.Finished(rect)) }

        start = null
        end = null
        updateRect()
    }

    private fun updateRect() = _selectionBox.update {
        if (start == null || end == null) return@update null

        DpRect(
            left = min(start!!.x, end!!.x),
            right = max(start!!.x, end!!.x),
            top = min(start!!.y, end!!.y),
            bottom = max(start!!.y, end!!.y)
        )
    }
}