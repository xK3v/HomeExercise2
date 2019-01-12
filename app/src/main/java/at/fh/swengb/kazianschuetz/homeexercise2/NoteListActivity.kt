package at.fh.swengb.kazianschuetz.homeexercise2

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_note_list.*

class NoteListActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        sharedPreferences = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        var name = sharedPreferences.getString("NAME","-")
        var age = sharedPreferences.getInt("AGE",0)

        lb_head.text = "Notes for $name, $age"


    }
}
