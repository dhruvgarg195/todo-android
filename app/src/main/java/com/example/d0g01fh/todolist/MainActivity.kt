package com.example.d0g01fh.todolist

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
//import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import io.realm.Realm

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        title = "My Todo List"


        fab.setOnClickListener {
            var toAddToDoActivity = Intent(this,AddToDoActivity:: class.java)
            startActivity(toAddToDoActivity)
        }

        //// realm fun begins



        ////Realm fun ends
    }

    override fun onResume() {
        super.onResume()
        val realm = Realm.getDefaultInstance()

        val query = realm.where(ToDoItem:: class.java)
        val results = query.findAll()

        var listView = findViewById<ListView>(R.id.toDoItemsListview)
        
        listView.setOnItemClickListener { parent, view, position, id ->
            val selectedToDo = results[position]
            val toFinishToDo = Intent(this, FinishToDoActivity::class.java)
            toFinishToDo.putExtra("toDoItem",selectedToDo.getId())
            startActivity(toFinishToDo)
        }

        var adapter = ToDoAdapter(this,android.R.layout.simple_list_item_1,results)

        listView.adapter = adapter
    }

}

class ToDoAdapter(context: Context, resource: Int, objects: MutableList<ToDoItem>) :
    ArrayAdapter<ToDoItem>(context, resource, objects) {

    override fun getCount(): Int {
        return super.getCount()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflator = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val toDoView = inflator.inflate(android.R.layout.simple_list_item_1,parent,false ) as TextView

        var theTodoItem = getItem(position)
        toDoView.text = theTodoItem.name

        if(theTodoItem.important)
        {
            toDoView.setTypeface(Typeface.DEFAULT_BOLD)
           // toDoView.setTextColor(Color.RED)
        }
        return toDoView
    }

}
