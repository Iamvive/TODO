package com.appworx.todo.controllers

import com.appworx.todo.database.entities.Todo
import com.appworx.todo.objectext.getOrGenerateObjectId
import com.appworx.todo.repo.TodoRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.*
import java.time.Instant

//POST http://localhost:8080/todo
//GET http://localhost:8080/todo?ownerId=12321
//GET http://localhost:8080/todo
//DELETE http://localhost:8080/todo?123
//PUT http://localhost:8080/todo

@RestController
@RequestMapping("/todo")
class TodoController(
    private val todoRepository: TodoRepository,
) {
    data class TodoRequest(
        val id: String?,
        val title: String,
        val desc: String,
        val color: Long,
        val priority: Todo.Priority,
    )

    data class TodoResponse(
        val id: String,
        val title: String,
        val desc: String,
        val color: Long,
        val createdAt: Instant,
    )

    @PostMapping
    fun saveTodo(
        @RequestBody body: TodoRequest,
    ): TodoResponse {
        println("Here it came inside saveTodo with body: $body")
        val todo = todoRepository.save(body.toEntity())
        return todo.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String,
    ): List<TodoResponse> {
        println("Here it came inside findByOwnerId with ownerId: $ownerId")
        val todos = todoRepository
            .findByOwnerId(ownerId = ownerId.getOrGenerateObjectId())
            .map { it.toResponse() }
        println("Here it came inside findByOwnerId with todos: $todos")
        return todos
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(
        @PathVariable(required = true) id: String,
    ) {
        println("Here it came inside deleteById with id: $id")
        todoRepository.deleteById(id.getOrGenerateObjectId())
    }

    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable(required = true) id: String,
        @RequestBody(required = true) body: TodoRequest,
    ): TodoResponse {
        println("Here it came inside updateTodo with id: $id and body: $body")
        val existingTodo = todoRepository.findById(id.getOrGenerateObjectId()).orElse(null)
        val updatedTodo = existingTodo.copy(
            title = body.title,
            description = body.desc,
            color = body.color,
            priority = body.priority,
        )
        val savedTodo = todoRepository.save(updatedTodo)
        return savedTodo.toResponse()
    }

    @GetMapping("/all")
    fun getAllTodo(): List<TodoResponse> {
        println("Here it came inside getAllTodo")
        val todos = todoRepository
            .findAll()
            .map { it.toResponse() }
        println("Here it came inside getAllTodo with todos: ${todos.size}")
        return todos
    }

    private fun TodoRequest.toEntity() = Todo(
        id = id.getOrGenerateObjectId(),
        title = title,
        description = desc,
        color = color,
        createdAt = Instant.now(),
        ownerId = ObjectId(),
        priority = priority,
    )

    private fun Todo.toResponse() = TodoResponse(
        id = id.toHexString(),
        title = title,
        desc = description,
        color = color,
        createdAt = Instant.now(),
    )
}