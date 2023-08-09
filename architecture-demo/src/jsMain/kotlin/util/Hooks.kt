package util

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import react.useEffect
import react.useState
import kotlin.coroutines.CoroutineContext

fun useCoroutineScope(context: CoroutineContext, ref: Any = Unit): CoroutineScope {
    val (scope, setScope) = useState(CoroutineScope(context))

    useEffect(Unit) {
        setScope { CoroutineScope(context) }
        cleanup {
            scope.cancel()
        }
    }
    return scope
}

fun <T, U> useFlow(
    flow: Flow<T>,
    initialValue: U,
    transform: (T) -> U
): U {
    val (state, setState) = useState(initialValue)
    val scope = useCoroutineScope(Dispatchers.Default)

    useEffect(scope) {
        scope.launch {
            flow.collect {
                setState(transform(it))
            }
        }
    }

    return state
}