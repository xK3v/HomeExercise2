package at.fh.swengb.kazianschuetz.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var db: NoteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)

        db = NoteDatabase.getDatabase(applicationContext)


        //only ask for name and age if they are not saved already
        if (sharedPreferences.contains("USERNAME")) {
            openNotes()
        }
    }

    fun saveSharedPreferences(v: View) {
        val nameInput: String = txt_title.text.toString()
        val ageInput: String = txt_content.text.toString()

        if (nameInput.isBlank() or ageInput.isBlank()) {
            Toast.makeText(this, "Please enter valid values!", Toast.LENGTH_LONG).show()
            //finish()
            //startActivity(intent)
        } else {
            val user = db.userDao.findByName(nameInput)

            if (user == null) {
                sharedPreferences.edit().putString("USERNAME", nameInput).apply()
                db.userDao.insert(User(nameInput,ageInput.toInt()))
                openNotes()
            }
            else {
                sharedPreferences.edit().putString("USERNAME", nameInput).apply()
                openNotes()
            }
        }

    }

    private fun openNotes() {
        val intent = Intent(this, NoteListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
