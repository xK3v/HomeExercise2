package at.fh.swengb.kazianschuetz.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add_note.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var myDb: NoteDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        myDb = NoteDatabase.getDatabase(applicationContext)

        if (intent.hasExtra(Note.EXTRA_NOTE_ID)) {
            val noteId = intent.getLongExtra(Note.EXTRA_NOTE_ID, 0)
            val note = myDb.noteDao.findNoteById(noteId)
            txt_title.setText(note.title)
            txt_content.setText(note.content)
        }

    }

    fun saveNote(v: View) {


        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("USERNAME", "-")

        val newNote = Note(txt_title.text.toString(), txt_content.text.toString(), username?:"anonymous")


        if (intent.hasExtra(Note.EXTRA_NOTE_ID)) {
            val noteId = intent.getLongExtra(Note.EXTRA_NOTE_ID, 0)
            myDb.noteDao.update2(newNote.title, newNote.content, noteId)
        } else {
            myDb.noteDao.insert(newNote)
        }

        finish()
    }

    fun shareNote (v:View) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, txt_content.text.toString())
        intent.type = "text/plain"
        val chooserIntent = Intent.createChooser(intent, "Select an app you want to share your note content with:")
        startActivity(chooserIntent)
    }
}
