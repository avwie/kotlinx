package util

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import react.useEffect
import react.useState
import kotlin.coroutines.CoroutineContext

fun useCoroutineScope(context: CoroutineContext, ref: Any? = null): CoroutineScope {
    val (scope, ) = useState(CoroutineScope(context))
    println("Creating scope: $ref")

    useEffect(Unit, ref) {
        cleanup {
            println("Cleaning scope: $ref")
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
    println("Creating flow: ${flow.hashCode()}")
    val (state, setState) = useState(initialValue)
    val scope = useCoroutineScope(Dispatchers.Default, flow.hashCode())

    useEffect(Unit) {
        scope.launch {
            flow.collect {
                setState(transform(it))
            }
        }
    }

    return state
}