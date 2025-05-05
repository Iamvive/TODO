package com.appworx.todo.repo

import com.appworx.todo.database.entities.Todo
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TodoRepository: MongoRepository<Todo, ObjectId> {
    fun findByOwnerId(ownerId: ObjectId): List<Todo>
}