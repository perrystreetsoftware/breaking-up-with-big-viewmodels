package com.perrystreet.nobigviewmodels.utils

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * A utility function to guard a condition and execute a return statement if the condition is false.
 *
 * @param condition The condition to check.
 * @param returnStatement The lambda to execute if the condition is false.
 */
@OptIn(ExperimentalContracts::class)
inline fun guard(
    condition: Boolean,
    returnStatement: () -> Unit,
) {
    contract {
        returns() implies condition
    }

    if (!condition) {
        returnStatement()
    }
}

@OptIn(ExperimentalContracts::class)
inline fun <reified R> guard(
    value: Any,
    returnStatement: () -> Unit,
) {
    contract {
        returns() implies (value is R)
    }

    if (value !is R) {
        returnStatement()
    }
}
