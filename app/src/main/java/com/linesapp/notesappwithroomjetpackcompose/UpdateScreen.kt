package com.linesapp.notesappwithroomjetpackcompose

import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.linesapp.notesappwithroomjetpackcompose.model.Note

@Composable
fun UpdateScreen(
    noteID: Int,
    noteTitle: String?,
    noteContent: String?,
    navController: NavController
){
    val viewModel = hiltViewModel<NotesViewModel>()
    var textFieldNoteTitle by remember {
        mutableStateOf(noteTitle)
    }
//    val decodedNoteContent = String(Base64.decode(noteContent, Base64.DEFAULT))
    var textFieldNoteContent by remember {
        mutableStateOf(noteContent)
    }

    val noteIdState = rememberSaveable{
        mutableStateOf(noteID)
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
            textFieldNoteTitle?.let {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = it,
                    onValueChange = {changedValue ->
                        textFieldNoteTitle = changedValue
                    },
                    label = {
                        Text(text = "Title")
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(8f)
        ){
            textFieldNoteContent?.let {
                TextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.secondary)
                        .verticalScroll(rememberScrollState()),
                    value = it,
                    onValueChange = {changedValue ->
                        textFieldNoteContent = changedValue
                    },
                    label = {
                        Text(text = "Content")
                    },
                    maxLines = Int.MAX_VALUE,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    )
                )
            }
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
                    textFieldNoteTitle?.let { title ->
                        textFieldNoteContent?.let { content ->
                            Note(noteIdState.value, title, content)
                        }?.let {
                            viewModel.updateNote(it)
                        }
                    }
                    navController.popBackStack()
                }
            ) {
                Text(
                    text = "Update",
                    color = Color.White
                )
            }
        }

    }
}