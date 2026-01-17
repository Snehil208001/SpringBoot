package com.springboot.spring_boot1.database.repository

import org.bson.types.ObjectId
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<SecurityProperties.User, ObjectId> {
    fun findByEmail(email: String): SecurityProperties.User?
}