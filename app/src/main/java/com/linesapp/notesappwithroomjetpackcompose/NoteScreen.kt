package com.linesapp.notesappwithroomjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.linesapp.notesappwithroomjetpackcompose.model.Note



@Composable
fun NoteScreen(
    navController: NavController
){
    val viewModel = hiltViewModel<NotesViewModel>()
//    val navController: NavHostController = rememberNavController()
    var textFieldNoteTitle by remember {
        mutableStateOf("")
    }
    var textFieldNoteContent by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = textFieldNoteTitle,
                onValueChange = {changedValue ->
                    textFieldNoteTitle = changedValue
                },
                label = {
                    Text(text = "Title")
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ){
            TextField(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.secondary)
                    .verticalScroll(rememberScrollState()),
                value = textFieldNoteContent,
                onValueChange = {changedValue ->
                    textFieldNoteContent = changedValue
                },
                label = {
                    Text(text = "Content")
                },
                maxLines = Int.MAX_VALUE
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)

        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colors.primary),
                onClick = {
                    textFieldNoteTitle.let { title ->
                        Note(0, title, textFieldNoteContent).let {
                            viewModel.addNote(it)
                        }
                    }
                    navController.popBackStack()
                }
            ) {
               Text(
                   text = "Add",
                   color = Color.White
               )
            }
        }

    }
}

//@Composable
//@Preview
//fun NoteScreenPreview(){
//    NoteScreen(noteTitle = "Lines", noteContent = "Kofi Bro")
//}