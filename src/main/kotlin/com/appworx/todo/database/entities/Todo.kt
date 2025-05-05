package com.appworx.todo.database.entities

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(value = "Todo")
data class Todo(
    val title: String,
    val description: String,
    val createdAt: Instant,
    val color: Long,
    val ownerId: ObjectId,
    val priority: Priority,
    @Id val id: ObjectId = ObjectId.get()
) {

    enum class Priority {
        PO,
        P1,
        P2,
        P3,
        P4,
    }
}
