package com.springboot.spring_boot1.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

//hash("hello") -> 77388sdczx7tzx7z8xz9

@Document("users")
data class user(
    val email: String,
    val hashedPassword: String,
    @Id val id: ObjectId = ObjectId()
)
