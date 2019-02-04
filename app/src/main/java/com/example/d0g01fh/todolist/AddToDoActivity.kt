package com.example.d0g01fh.todolist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import io.realm.Realm

class AddToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)

        title = "New ToDo"

        var addButton = findViewById<Button>(R.id.addButton)
        var impCheckbox = findViewById<CheckBox>(R.id.importantCheckbox)
        var nameText = findViewById<EditText>(R.id.addToDoEditText)

        addButton.setOnClickListener {

            val todo = ToDoItem()
            todo.name = nameText.text.toString()
            todo.important = impCheckbox.isChecked

            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            realm.copyToRealm(todo)
            realm.commitTransaction()

            finish()
        }

    }
}
