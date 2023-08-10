package nl.avwie.architecture

interface ViewModel<S, I> : StateHolder<S> {
    fun asStateHolder(): StateHolder<S> = this
    @Suppress("UNCHECKED_CAST")
    fun asInteractor(): I = this as I
}