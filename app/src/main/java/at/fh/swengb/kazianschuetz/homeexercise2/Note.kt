package at.fh.swengb.kazianschuetz.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["name"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class Note (
    var title: String,
    val content: String,
    val userId: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    companion object {
        val EXTRA_NOTE_ID = "NoteId_Extra"

    }
}