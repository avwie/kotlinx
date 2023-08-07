@file:OptIn(ExperimentalJsExport::class)
package todo

import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import nl.avwie.architecture.ViewModel
import nl.avwie.architecture.todo.TodoViewModel
import nl.avwie.architecture.todo.TodoMutableViewModel
import util.Publisher
import util.PublisherImpl

@JsExport
class TodoViewModelJS(
    private val actualViewModel: TodoViewModel,
) :
    Publisher<TodoStateJS> by PublisherImpl(),
    TodoMutableViewModel by actualViewModel,
    ViewModel by actualViewModel
{
    init {
        scope.launch {
            actualViewModel.state.onEach { state ->
                publish(TodoStateJS.fromState(state))
            }
        }
    }
}