package nl.avwie.architecture

interface ViewModel<S, I> : StateHolder<S> where I : Interactor {
    fun asStateHolder(): StateHolder<S> = this
    @Suppress("UNCHECKED_CAST")
    fun asInteractor(): I = this as I
}