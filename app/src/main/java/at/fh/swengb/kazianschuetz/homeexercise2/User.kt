package at.fh.swengb.kazianschuetz.homeexercise2

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "users")
class User(@PrimaryKey var name: String, @ColumnInfo(name = "age", index = true) val age: Int)