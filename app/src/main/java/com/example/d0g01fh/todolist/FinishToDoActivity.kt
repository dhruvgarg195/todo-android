package com.example.d0g01fh.todolist

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.realm.Realm

class FinishToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_to_do)

        title = "My Task"

        var toDoItemId = intent.getStringExtra("toDoItem")
        val realm = Realm.getDefaultInstance()
        val toDoItem = realm.where(ToDoItem::class.java).equalTo("id",toDoItemId).findFirst()

        var toDoItemTextView = findViewById<TextView>(R.id.toDoItemId)
        toDoItemTextView.text = toDoItem.name


        if(toDoItem.important)
        {
            toDoItemTextView.setTypeface(Typeface.DEFAULT_BOLD)
            toDoItemTextView.setTextColor(Color.RED)
        }

        var completeButton = findViewById<Button>(R.id.completeButton)

        completeButton.setOnClickListener {

            realm.beginTransaction()
            toDoItem.deleteFromRealm()
            realm.commitTransaction()

            finish()
        }

        var editButton = findViewById<Button>(R.id.editButtonId)

        editButton.setOnClickListener {

            realm.beginTransaction()
            toDoItem.deleteFromRealm()
            realm.commitTransaction()

            var toAddItemIntent = Intent(this,AddToDoActivity::class.java)
            startActivity(toAddItemIntent)

            finish()

        }
    }
}
