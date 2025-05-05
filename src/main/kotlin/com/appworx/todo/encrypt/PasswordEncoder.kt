package com.appworx.todo.encrypt

interface PasswordEncoder {
    fun encrypt(text: String): String
    fun match(text: String, encryptedText: String): Boolean
}