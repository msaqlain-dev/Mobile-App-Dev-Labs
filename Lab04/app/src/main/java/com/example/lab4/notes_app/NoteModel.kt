package com.example.lab4.notes_app

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.ui.platform.LocalContext
import java.io.File
import java.lang.Exception

class NoteModel(title: String, content: String, datetime: String) {
    var id = ""
    var title = title
    var content = content
    var datetime = datetime


    constructor(id:String, title: String, content: String, datetime: String) : this(title, content, datetime) {
        this.id = id
        this.title = title
        this.content = content
        this.datetime = datetime
    }
}
