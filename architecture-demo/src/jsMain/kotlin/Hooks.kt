@file:OptIn(ExperimentalJsExport::class)

import nl.avwie.architecture.Interactor
import nl.avwie.architecture.ViewModel
import nl.avwie.architecture.todo.*
import react.useState
import util.useFlow

@JsExport()
interface UseViewModelResponse<S, I> {
    val state: S
    val interactor: I
}

@JsExport()
fun useTodoViewModel() = useViewModel(
    builder = { TodoViewModelImpl() },
    initialState = TodoStateJS.fromState(TodoState.EMPTY),
    transform = { TodoStateJS.fromState(it) }
)

fun <V, S, I, S2> useViewModel(
    builder: () -> V,
    initialState: S2,
    transform: (S) -> S2
):  UseViewModelResponse<S2, I>
    where V : ViewModel<S, I>, I : Interactor
{
    val (viewModel,  ) = useState { builder() }

    val state = useFlow(viewModel.asStateHolder().state, initialState) {
        transform(it)
    }

    return object : UseViewModelResponse<S2, I> {
        override val interactor: I = viewModel.asInteractor()
        override val state: S2 = state
    }
}