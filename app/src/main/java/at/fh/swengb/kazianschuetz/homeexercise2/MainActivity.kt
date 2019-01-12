package at.fh.swengb.kazianschuetz.homeexercise2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
    }

    fun saveSharedPreferences(v:View) {

        sharedPreferences.edit().putString("NAME", txt_title.text.toString()).apply()
        sharedPreferences.edit().putInt("AGE",txt_content.text.toString().toInt()).apply()
        openNotes()
    }

    private fun openNotes() {
        val intent = Intent(this, NoteListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
