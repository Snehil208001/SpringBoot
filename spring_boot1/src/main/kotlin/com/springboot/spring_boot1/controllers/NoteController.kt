package com.springboot.spring_boot1.controllers

import com.springboot.spring_boot1.controllers.NoteController.NoteResponse
import com.springboot.spring_boot1.database.NoteRepository
import com.springboot.spring_boot1.database.model.Note
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

//POST http://localhost:8085/notes
//GET http://localhost:80885/notes?ownerID=123


@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {

    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long
    )

    data class  NoteResponse(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant
    )

    @PostMapping
    fun save(
        @RequestBody body: NoteRequest): NoteResponse{
        val note =repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId()

            )
        )

        return note.toRespnose()

    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId()).map {
            it.toRespnose()
        }
    }

}

private fun Note.toRespnose(): NoteController.NoteResponse{
    return NoteResponse(
        id = id.toHexString(),
        title = title,
        content = content,
        color = color,
        createdAt = createdAt
    )
}