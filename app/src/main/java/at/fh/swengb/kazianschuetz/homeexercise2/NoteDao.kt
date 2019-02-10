package at.fh.swengb.kazianschuetz.homeexercise2

import android.arch.persistence.room.*

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(note:Note)


    @Query("UPDATE notes SET title = :title, content = :content WHERE id = :noteId")
    fun update2(title: String, content: String, noteId: Long)

    @Query("SELECT * FROM notes")
    fun findAllNotes(): List<Note>

    @Query("SELECT * FROM notes WHERE id = :id")
    fun findNoteById(id:Long): Note

    @Delete
    fun delete(note: Note) :Int
}