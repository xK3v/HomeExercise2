package at.fh.swengb.kazianschuetz.homeexercise2

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User): Long

    @Query("SELECT * FROM users where name = :searchString")
    fun findByName(searchString: String): User?

    @Query("SELECT * FROM users WHERE name = :name")
    fun findUsersAndNotesByName(name: String): NotesAndUser
}