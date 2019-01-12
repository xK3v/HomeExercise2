package at.fh.swengb.kazianschuetz.homeexercise2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var myDb: NoteDatabase
    private lateinit var myAdapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        myDb = NoteDatabase.getDatabase(applicationContext)

        myAdapter = NoteAdapter()


    }

    fun saveNote(v:View) {
        val newNote = Note(txt_title.text.toString(),txt_content.text.toString())
        myDb.noteDao.insert(newNote)
        myAdapter.updateData(myDb.noteDao.findAllNotes())
        finish()
    }
}
