package nl.avwie.kotlinx.flow.ui.common

interface ClickEventHandler {
    fun onClick()
}

interface TargetedClickEventHandler<T> {
    fun onClick(target: T)
}