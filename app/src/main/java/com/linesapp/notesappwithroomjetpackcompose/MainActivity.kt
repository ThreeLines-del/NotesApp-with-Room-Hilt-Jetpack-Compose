package com.linesapp.notesappwithroomjetpackcompose

import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.linesapp.notesappwithroomjetpackcompose.navigation.Navigation
import com.linesapp.notesappwithroomjetpackcompose.navigation.Screen
import com.linesapp.notesappwithroomjetpackcompose.ui.theme.NotesAppWithRoomJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private lateinit var notesViewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val notesViewModel: NotesViewModel by viewModels()
//        notesViewModel = ViewModelProvider(this)[(NotesViewModel::class.java)]
        setContent {
            NotesAppWithRoomJetpackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: NotesViewModel,
    navController: NavController
){
    val noteListState = viewModel.getAllNotes.collectAsState(initial = listOf())


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn{
            items(noteListState.value.size){ index ->
                val note = noteListState.value[index]
                NoteItem(
                    note = note,
                    onDeleteIconClick = {
                        viewModel.deleteNote(note)
                    },
                    onItemClick = {
//                        val encodedNoteContent = Base64.encodeToString(note.noteContent.toByteArray(), Base64.DEFAULT)
                        navController.navigate(
                            Screen.UpdateScreen.route
                                .replace("{note_id}", note.id.toString())
                                .replace("{note_title}", note.noteTitle)
                                .replace("{note_content}", note.noteContent)
                        )
                    }
                )
            }
        }

        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            onClick = {
                navController.navigate(Screen.NoteScreen.route)
            },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "Add Button"
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    NotesAppWithRoomJetpackComposeTheme {
//        HomeScreen(
//            viewModel = NotesViewModel()
//        )
//    }
//}