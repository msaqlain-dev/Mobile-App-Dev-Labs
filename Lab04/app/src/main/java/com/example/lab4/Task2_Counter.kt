package com.example.lab4
import android.content.Context
import android.preference.PreferenceDataStore
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.NavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class Task2_Counter {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Display(nav: NavController, context: Context = LocalContext.current) {
        val prefsMgr = PrefsManager(context)
        val scope = rememberCoroutineScope()
        var count by remember{
            mutableIntStateOf(0)
        }
        
        LaunchedEffect(Unit){
            scope.launch {
                count = prefsMgr.get().toInt()
            }
        }
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "COUNTER", fontWeight = FontWeight.Bold) }, actions = {
                    TextButton(onClick = {
                        count = 0
                        scope.launch {
                            prefsMgr.save("0")
                        }

                    }) {
                        Text(
                            text = "Reset",
                            color = Color.Red,
                            fontSize = 20.sp
                        )
                    }
                })
            },
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    count++
                    scope.launch {
                        prefsMgr.save(count.toString())
                    }
                }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add", Modifier.size(35.dp))
                }
            }
        ) {
            Column(
                Modifier
                    .padding(it)
                    .fillMaxSize(),
horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ){
                Text(text = count.toString(), fontSize = 100.sp)
            }
        }
    }
}

val Context.datastore by preferencesDataStore(name="mad_task2datastore")
class PrefsManager(private val context: Context){
    val key = stringPreferencesKey("lastValue")
    suspend fun save(data:String){
        context.datastore.edit {
            it[key] = data
        }
    }

    suspend fun get() : String{
        val userflow = context.datastore.data.map {
            it[key] ?: "0"
        }
        return userflow.first()
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun Task2_CounterPreview(){
    Task2_Counter().Display(nav = NavController(LocalContext.current))
}