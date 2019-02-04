package com.example.d0g01fh.todolist

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class ToDoItem : RealmObject() {
    @PrimaryKey
    private var id = UUID.randomUUID().toString()

    public var name =""
    public var important = false

    fun getId(): String{
        return id
    }

    override fun toString() : String{
        return name
    }
}