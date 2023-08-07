package nl.avwie.architecture.todo

data class TodoState(
    val todoItems: List<TodoItem>
) {
    companion object {
        val EMPTY = TodoState(emptyList())
    }
}

data class TodoItem(val id: String, val name: String, val done: Boolean)