package com.codigo.mobilecodetest.util

class LiveDataResolver(private val validators: List<LiveDataValidator>) {
    fun isValid(): Boolean {
        for (validator in validators) {
            if (!validator.isValid()) return false
        }
        return true
    }
}