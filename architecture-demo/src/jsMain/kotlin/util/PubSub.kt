@file:OptIn(ExperimentalJsExport::class)
package util

@JsExport()
interface Publisher<T> {
    fun subscribe(subscriber: Subscriber<T>): Subscription
    fun publish(event: T)
}

@JsExport()
class PublisherImpl<T> : Publisher<T> {
    private val subscribers = mutableListOf<Subscriber<T>>()

    override fun subscribe(subscriber: Subscriber<T>): Subscription {
        subscribers.add(subscriber)
        return Subscription { subscribers.remove(subscriber) }
    }

    override fun publish(event: T) {
        subscribers.forEach { it.onEvent(event) }
    }
}

@JsExport()
fun interface Subscriber<T> {
    fun onEvent(event: T)
}

@JsExport()
fun interface Subscription {
    fun unsubscribe()
}