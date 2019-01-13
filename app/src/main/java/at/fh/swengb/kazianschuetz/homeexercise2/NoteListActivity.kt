package at.fh.swengb.kazianschuetz.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        val name = sharedPreferences.getString("NAME", "-")
        val age = sharedPreferences.getInt("AGE", 0)

        lb_head.text = "Notes for $name, $age"

        myDb = NoteDatabase.getDatabase(applicationContext)

        myAdapter = NoteAdapter()

        rv_notes.adapter = myAdapter
        rv_notes.layoutManager = LinearLayoutManager(this)


        //myAdapter.updateData(myDb.noteDao.findAllNotes())
    }

    override fun onResume() {
        super.onResume()
        myAdapter.updateData(myDb.noteDao.findAllNotes())
    }



    fun addNote(v:View) {
        val intent = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }


}
