@file:OptIn(ExperimentalJsExport::class)

package todo

import kotlinx.coroutines.Dispatchers
import nl.avwie.architecture.todo.TodoMutableViewModel
import nl.avwie.architecture.todo.TodoState
import nl.avwie.architecture.todo.TodoViewModel
import nl.avwie.architecture.todo.TodoViewModelImpl
import react.useState
import util.useCoroutineScope
import util.useFlow

@JsExport()
interface UseTodoViewModelResponse {
    val state: TodoStateJS
    val mutableViewModel: TodoMutableViewModel
}

@JsExport()
fun useTodoViewModel(): UseTodoViewModelResponse {
    val scope = useCoroutineScope(context = Dispatchers.Default)
    val (viewModel) = useState<TodoViewModel>(TodoViewModelImpl(scope))

    val state = useFlow(
        flow = viewModel.state,
        scope = scope,
        initialValue = TodoStateJS.fromState(TodoState.EMPTY),
        transform = { TodoStateJS.fromState(it) }
    )

    return object : UseTodoViewModelResponse {
        override val mutableViewModel: TodoMutableViewModel = viewModel
        override val state: TodoStateJS = state
    }
}
