package com.appworx.todo.user.controller

import com.appworx.todo.user.repo.UserRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController
constructor(
    private val userRepository: UserRepository,
) {

}