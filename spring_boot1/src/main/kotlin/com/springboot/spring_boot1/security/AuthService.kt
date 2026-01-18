package com.springboot.spring_boot1.security

import com.springboot.spring_boot1.database.repository.UserRepository
import io.jsonwebtoken.security.Password
import jakarta.validation.constraints.Email
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val jwtService: JwtService,
    private val userRepository: UserRepository,
    private val hashEncoder: HashEncoder
) {
    fun register(email: String,password: String): User{
        return  userRepository.save(
            User(
                email = email,
                hashedPassword = hashEncoder.encode(password)
            )
        )
    }
}