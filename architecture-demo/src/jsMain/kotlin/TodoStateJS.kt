@file:OptIn(ExperimentalJsExport::class)

import nl.avwie.architecture.todo.TodoItem
import nl.avwie.architecture.todo.TodoState

@JsExport
@JsName("TodoState")
class TodoStateJS(
    val todoItems: Array<TodoItem>
) {
    companion object {
        fun fromState(state: TodoState): TodoStateJS {
            return TodoStateJS(state.todoItems.toTypedArray())
        }
    }
}