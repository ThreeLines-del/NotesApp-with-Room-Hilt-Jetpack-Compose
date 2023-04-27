package com.linesapp.notesappwithroomjetpackcompose.navigation

sealed class Screen(val route: String){
    object HomeScreen: Screen("home_screen")
    object NoteScreen: Screen("note_screen")
    object UpdateScreen: Screen("update_screen?note_id={note_id}&note_title={note_title}&note_content={note_content}")
}
