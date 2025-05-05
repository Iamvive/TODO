package com.appworx.todo.user.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "User")
data class User(
    @Id val id: ObjectId = ObjectId.get(),
    val name: String,
    val email: String,
    val hexPassword: String,
    val gstNumber: String?,
)
