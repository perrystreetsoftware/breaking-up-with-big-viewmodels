package com.perrystreet.nobigviewmodels.utils

import java.lang.IllegalArgumentException

data class Optional<out T>(
    val value: T?,
) {
    val isEmpty: Boolean get() = value == null
    val isPresent: Boolean get() = value != null

    companion object {
        fun <T> empty() = Optional<T>(null)

        fun <T> of(value: T?) = Optional(value)

        fun <T> ofNull() = Optional<T>(null)
    }
}

fun <T> T?.asOptional(): Optional<T> {
    if (this is Optional<*>) {
        throw IllegalArgumentException("Cannot wrap an Optional<T> with another Optional")
    }

    return Optional(this)
}

inline fun <T : Any> Optional<T>.ifPresent(block: (T) -> Unit) = value?.let(block)

inline fun <T : Any> Optional<T>.getOrElse(block: () -> T): T = if (isEmpty) block() else value!!

fun <T : Any> Optional<T>.getOrElse(default: T): T = if (isEmpty) default else value!!
