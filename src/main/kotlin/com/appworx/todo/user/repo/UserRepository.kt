package com.appworx.todo.user.repo

import com.appworx.todo.user.entities.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, ObjectId> {
    fun findByEmail(email: String): User?
    fun findByName(name: String): User?
    fun findByGstNumber(gstNumber: String): User?
}