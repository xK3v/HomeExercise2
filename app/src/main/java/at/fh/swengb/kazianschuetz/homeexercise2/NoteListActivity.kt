package at.fh.swengb.kazianschuetz.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var myAdapter: NoteAdapter
    private lateinit var myDb: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        myDb = NoteDatabase.getDatabase(applicationContext)
        val username = sharedPreferences.getString("USERNAME", "anonymous")

        val user = myDb.userDao.findByName(username?:"anonymous")

        val name = user?.name?:"anonymous"
        val age = user?.age?:0


        lb_head.text = "Notes for $name, $age"


        myAdapter = NoteAdapter(
            clickListener = {
                val implicitIntent = Intent(this, AddNoteActivity::class.java)
                implicitIntent.putExtra(Note.EXTRA_NOTE_ID, it.id)
                startActivity(implicitIntent)
            },
            longClickListener = {
                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setTitle("Delete note")
                dialogBuilder.setMessage("Are you sure you want to delete this note?")
                dialogBuilder.setPositiveButton("Yes") {_,_ ->
                    myDb.noteDao.delete(it)
                    val notesAndUsers = myDb.userDao.findUsersAndNotesByName(user?.name?:"anonymous")
                    val list = notesAndUsers.notes
                    myAdapter.updateData(list)
                }
                dialogBuilder.setNegativeButton("No", null)
                dialogBuilder.show()
            }

        )

        rv_notes.adapter = myAdapter
        rv_notes.layoutManager = LinearLayoutManager(this)


        //myAdapter.updateData(myDb.noteDao.findAllNotes())
    }

    override fun onResume() {
        super.onResume()

        val username = sharedPreferences.getString("USERNAME", "-")

        val notesAndUsers = myDb.userDao.findUsersAndNotesByName(username?:"anonymous")

        val list = notesAndUsers.notes

        myAdapter.updateData(list)
    }



    fun addNote(v:View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

    fun logout(v:View) {
        sharedPreferences.edit().remove("USERNAME").apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
