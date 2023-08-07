@file:OptIn(ExperimentalJsExport::class)
package nl.avwie.architecture.todo

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

data class TodoState(
    val todoItems: List<TodoItem>
) {
    companion object {
        val EMPTY = TodoState(emptyList())
    }
}

@JsExport
data class TodoItem(val id: String, val name: String, val done: Boolean)