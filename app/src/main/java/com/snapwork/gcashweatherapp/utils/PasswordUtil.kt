package com.snapwork.gcashweatherapp.utils

import java.security.MessageDigest

object PasswordUtil {

    fun hashPassword(password: String): String {

        val bytes =
            MessageDigest.getInstance("SHA-256")
                .digest(password.toByteArray())

        return bytes.joinToString("") {
            "%02x".format(it)
        }
    }
}