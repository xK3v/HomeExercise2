package at.fh.swengb.kazianschuetz.homeexercise2

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "notes")
class Note (
    val title: String,
    val content: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}