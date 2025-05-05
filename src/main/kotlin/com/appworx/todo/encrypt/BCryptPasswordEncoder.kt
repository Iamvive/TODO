package com.appworx.todo.encrypt

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class BCryptPasswordEncoder : PasswordEncoder {
    private val bEncrypt by lazy { BCryptPasswordEncoder() }

    override fun encrypt(text: String): String =
        bEncrypt.encode(text)

    override fun match(text: String, encryptedText: String): Boolean =
        bEncrypt.matches(text, encryptedText)
}