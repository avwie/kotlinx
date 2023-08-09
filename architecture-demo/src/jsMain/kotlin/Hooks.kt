@file:OptIn(ExperimentalJsExport::class)

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import nl.avwie.architecture.todo.TodoMutableViewModel
import nl.avwie.architecture.todo.TodoState
import nl.avwie.architecture.todo.TodoViewModel
import nl.avwie.architecture.todo.TodoViewModelImpl
import react.useEffect
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
    val (viewModel, setViewModel ) = useState<TodoViewModel>(TodoViewModelImpl(CoroutineScope(Dispatchers.Default)))

    val state = useFlow(viewModel.state, TodoStateJS.fromState(TodoState.EMPTY)) {
        TodoStateJS.fromState(it)
    }

    return object : UseTodoViewModelResponse {
        override val mutableViewModel: TodoMutableViewModel = viewModel
        override val state: TodoStateJS = state
    }
}
