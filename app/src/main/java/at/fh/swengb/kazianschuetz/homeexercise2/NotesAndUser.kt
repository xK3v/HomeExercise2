package at.fh.swengb.kazianschuetz.homeexercise2

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation

class NotesAndUser {
    @Embedded
lateinit var user: User
    @Relation(entity = Note::class, entityColumn = "userId", parentColumn = "name")
    lateinit var notes: List<Note>
}