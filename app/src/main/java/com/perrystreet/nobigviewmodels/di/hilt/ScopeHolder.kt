package com.perrystreet.nobigviewmodels.di.hilt

/** Hilt */

/*
@Singleton
class ScopeHolder @Inject constructor() {
    private val activeScopes = mutableMapOf<String, Any>()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> getOrCreateMediator(
        scopeId: String,
        factory: () -> T,
    ): T =
        activeScopes.getOrPut(scopeId) {
            factory()
        } as T

    fun releaseScope(scopeId: String) {
        activeScopes.remove(scopeId)
    }
}
*/
