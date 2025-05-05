package com.appworx.todo.objectext

import org.bson.types.ObjectId

fun String?.getOrGenerateObjectId(): ObjectId = this?.let { ObjectId(it) } ?: ObjectId.get()