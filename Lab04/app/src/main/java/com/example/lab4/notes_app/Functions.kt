package com.example.lab4.notes_app
import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.io.File
import java.lang.Exception
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Functions {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getUniqueId():String{
        var localdatetime = LocalDateTime.now()
        var datetime = localdatetime.toString().replace(":", "").replace("-", "").replace(".", "")
        return datetime;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateTime():String{
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
    }


    fun separate(id:String, value:String):NoteModel{
        var firstBreak = value.indexOfFirst {
            it == '~'
        }
        var lastBreak = value.indexOfLast {it == '~'}
        var title = value.substring(0, firstBreak)
        var datetime = value.substring(firstBreak+1, lastBreak)
        var content = value.substring(lastBreak+1, value.length)
        return NoteModel(id, title, content, datetime)
    }

    fun getCombined(title:String, content:String, datetime:String): String {
        return "$title~$datetime~$content"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveNote(context: Context, note:NoteModel) : Boolean{
        if (note.title.isEmpty()){
            Toast.makeText(context, "Please Provide Title", Toast.LENGTH_LONG).show()
            return false
        }
        return try{
//            val newfolder = File(context.filesDir, "notes")
//            if (!newfolder.exists()){
//                newfolder.mkdir()
//            }
//            val file = File(newfolder, "${Functions().getUniqueId()}.txt")
            val output = context.openFileOutput("${Functions().getUniqueId()}.txt", Context.MODE_PRIVATE)
            output.write(getCombined(note.title, note.content, note.datetime).toByteArray())
            output.close()
            true
        }catch(e: Exception){
            false
        }
    }

    fun getAllFiles(context:Context) : List<NoteModel>{
        var files = context.filesDir.listFiles()?.toList() ?: emptyList()
        var data = listOf<NoteModel>().toMutableList()
        for (i in files.indices){
            if (files[i].name != "datastore" && files[i].name != "profileInstalled" && !files[i].isDirectory){
                data.add(readTheFile(context, files[i].name))
            }
        }
        return data
    }

    fun readTheFile(context:Context, fileid:String):NoteModel{
        val inputStream = context.openFileInput(fileid)
        val bytes = inputStream.readBytes()
        var data = bytes.toString(Charsets.UTF_8)
        return separate(fileid, data)
    }


    fun deleteNote(context:Context, noteId:String):Boolean{
        return File(context.filesDir, noteId).delete()
    }
}