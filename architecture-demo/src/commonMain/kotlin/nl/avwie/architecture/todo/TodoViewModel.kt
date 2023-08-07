package nl.avwie.architecture.todo

import com.benasher44.uuid.uuid4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.avwie.architecture.ViewModel

interface TodoWriteViewModel {
    fun addTodoItem(name: String)
    fun toggleTodoItem(id: String)
    fun removeTodoItem(id: String)
}

interface TodoViewModel : ViewModel<TodoState>, TodoWriteViewModel
class TodoViewModelImpl(
    override val scope: CoroutineScope? = null
) : TodoViewModel {

    private val _state = MutableStateFlow(TodoState.EMPTY)
    override val state: StateFlow<TodoState> = _state.asStateFlow()

    override fun addTodoItem(name: String) = _state.update {
        val item = TodoItem(uuid4().toString(),  name, false)
        TodoState(it.todoItems + item)
    }

    override fun toggleTodoItem(id: String) = _state.update {
        val items = it.todoItems.map { item ->
            if (item.id == id) item.copy(done = !item.done) else item
        }
        TodoState(items)
    }

    override fun removeTodoItem(id: String) = _state.update {
        val items = it.todoItems.filter { item -> item.id != id }
        TodoState(items)
    }
}
