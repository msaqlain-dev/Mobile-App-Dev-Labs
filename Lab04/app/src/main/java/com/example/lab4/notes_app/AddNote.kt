package com.example.lab4.notes_app

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

class AddNote {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Display(nav: NavController, context: Context = LocalContext.current) {
        var tfTitle by remember {
            mutableStateOf("")
        }
        var tfContent by remember {
            mutableStateOf("")
        }
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {Text(text = "Add Note", fontWeight = FontWeight.Bold)},
                    actions = {
                        Button(onClick = {
                            var model = NoteModel(title = tfTitle, content = tfContent, datetime = Functions().getDateTime())
                            if(Functions().saveNote(context, model)){
                                Toast.makeText(context, "Note Saved", Toast.LENGTH_SHORT).show()
                                nav.navigate("/notes")
                            }else{
                                Toast.makeText(context, "Can not Save Note", Toast.LENGTH_SHORT).show()
                            }

                        }) {
                            Text(text = "Save Note")
                        }
                    }
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                TextField(value = tfTitle, onValueChange = {
                    tfTitle = it
                }, placeholder = {
                    Text(text = "Enter Title Of Note")
                }, modifier = Modifier.fillMaxWidth().padding(15.dp))

                TextField(value = tfContent, onValueChange = {
                    tfContent = it
                }, placeholder = {
                    Text(text = "Enter Content of Note")
                }, modifier=Modifier.fillMaxSize().padding(bottom=15.dp, start=15.dp, end=15.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun AddNotePreview() {
    AddNote().Display(nav = NavController(LocalContext.current))
}