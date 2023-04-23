package nl.avwie.kotlinx.utils.coroutines

import kotlinx.coroutines.flow.*

fun <T, R> StateFlow<T>.mapSync(transform: (T) -> R): StateFlow<R> = object : StateFlow<R> {

    override val replayCache: List<R> get() = listOf(value)

    override suspend fun collect(collector: FlowCollector<R>): Nothing {
        this@mapSync.collect {
            collector.emit(transform(it))
        }
    }

    private var lastUpstreamValue = this@mapSync.value

    override var value: R = transform(lastUpstreamValue)
        private set
        get() {
            val currentUpstreamValue: T = this@mapSync.value
            if (currentUpstreamValue == lastUpstreamValue) return field
            field = transform(currentUpstreamValue)
            lastUpstreamValue = currentUpstreamValue
            return field
        }
}