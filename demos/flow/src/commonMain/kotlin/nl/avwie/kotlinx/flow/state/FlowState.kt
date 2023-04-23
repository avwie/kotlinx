package nl.avwie.kotlinx.flow.state

import com.benasher44.uuid.Uuid

data class FlowState(
    val icons: List<Icon>
)

data class Icon (
    val id: Uuid,
    val name: String,
    val position: Pair<Int, Int>
)