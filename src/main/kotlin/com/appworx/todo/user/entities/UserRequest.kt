package com.appworx.todo.user.entities

data class UserRequest(
    val name: String,
    val email: String,
    val password: String,
    val gstNumber: String?,
)

fun UserRequest.toEntity(): User = User(
    name = this.name,
    email = this.email,
    hexPassword = this.password,
    gstNumber = this.gstNumber,
)
