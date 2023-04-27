package com.linesapp.notesappwithroomjetpackcompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linesapp.notesappwithroomjetpackcompose.model.Note
import com.linesapp.notesappwithroomjetpackcompose.ui.theme.Typography

@Composable
fun NoteItem(
    note: Note,
    onDeleteIconClick: () -> Unit,
    onItemClick: () -> Unit
){
    Column(
        modifier = Modifier
            .clickable (
                onClick = onItemClick
                    )
            .background(Color.White)
            .padding(5.dp)
            .fillMaxWidth()
    ) {
       Box(
           modifier = Modifier
               .background(Color.White)
               .fillMaxWidth()
       ) {
           Row(
               modifier = Modifier
                   .fillMaxWidth(),
               verticalAlignment = Alignment.CenterVertically
           ){
               Text(
                   modifier = Modifier
                       .padding(5.dp)
                       .weight(6f),
                   text = note.noteTitle,
                   fontSize = Typography.h5.fontSize,
                   fontWeight = FontWeight.Bold,
                   color = MaterialTheme.colors.secondary,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
               IconButton(
                   modifier = Modifier
                       .weight(1f),
                   onClick = onDeleteIconClick,
               ) {
                   Icon(
                       modifier = Modifier
                           .size(30.dp),
                       painter = painterResource(id = R.drawable.baseline_delete_24),
                       contentDescription = "Delete Icon",
                       tint = Color.Unspecified
                   )
               }
           }
       }
        Spacer(modifier = Modifier.height(5.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(MaterialTheme.colors.secondary)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                text = note.noteContent,
                fontSize = Typography.h6.fontSize,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
@Preview
fun NoteItemPreview(){
    NoteItem(
        Note(
            0,
            "Book Of Lines",
            "Largely self-educated, Columbus was knowledgeable in geography, astronomy, and history. " +
                    "He developed a plan to seek a western sea passage to the East Indies, hoping to profit from the lucrative spice trade. " +
                    "After the Granada War, and Columbus's persistent lobbying in multiple kingdoms, " +
                    "the Catholic Monarchs Queen Isabella I and King Ferdinand II agreed to sponsor a journey west. " +
                    "Columbus left Castile in August 1492 with three ships and made landfall in the Americas on 12 October, " +
                    "ending the period of human habitation in the Americas now referred to as the pre-Columbian era. " +
                    "His landing place was an island in the Bahamas, known by its native inhabitants as Guanahani. " +
                    "He then visited the islands now known as Cuba and Hispaniola, establishing a colony in what is now Haiti. " +
                    "Columbus returned to Castile in early 1493, with captured natives. Word of his voyage soon spread throughout Europe."
        ),
        onDeleteIconClick = {},
        onItemClick = {}
    )
}