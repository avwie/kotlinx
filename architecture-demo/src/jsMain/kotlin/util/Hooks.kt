package util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import react.useEffect
import react.useState
import kotlin.coroutines.CoroutineContext

fun useCoroutineScope(context: CoroutineContext): CoroutineScope {
    val (scope, ) = useState<CoroutineScope>(CoroutineScope(context))

    useEffect {
        cleanup {
            scope.cancel()
        }
    }

    return scope
}

fun <T, U> useFlow(
    flow: Flow<T>,
    scope: CoroutineScope,
    initialValue: U,
    transform: (T) -> U
): U {
    val (job, setJob) = useState<Job>()
    val (state, setState) = useState(initialValue)

    useEffect(flow, scope, initialValue, transform) {
        val newJob = scope.launch {
            flow.collect {
                setState(transform(it))
            }
        }
        setJob(newJob)
        cleanup {
            job?.cancel()
        }
    }

    return state
}

fun <T> useFlow(flow: Flow<T>, scope: CoroutineScope): T? = useFlow(flow, scope, null) { it }
fun <T> useFlow(flow: Flow<T>, scope: CoroutineScope, initialValue: T): T = useFlow(flow, scope, initialValue) { it }
fun <T, U> useFlow(flow: Flow<T>, scope: CoroutineScope, transform: (T) -> U): U? = useFlow(flow, scope, null, transform)