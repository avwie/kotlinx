package nl.avwie.kotlinx.flow.state

import com.benasher44.uuid.Uuid
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class FlowState(
    val elements: List<Element>
)

data class Element (
    val id: Uuid,
    val name: String,
    val position: Pair<Int, Int>
)