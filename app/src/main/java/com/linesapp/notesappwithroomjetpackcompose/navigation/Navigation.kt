package com.linesapp.notesappwithroomjetpackcompose.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.linesapp.notesappwithroomjetpackcompose.HomeScreen
import com.linesapp.notesappwithroomjetpackcompose.NoteScreen
import com.linesapp.notesappwithroomjetpackcompose.NotesViewModel
import com.linesapp.notesappwithroomjetpackcompose.UpdateScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    val viewModel = hiltViewModel<NotesViewModel>()

    NavHost(navController = navController, startDestination = Screen.HomeScreen.route ){
        composable(route = Screen.HomeScreen.route){
            HomeScreen(
                viewModel = viewModel,
                navController = navController
            )
        }
        composable(route = Screen.NoteScreen.route,){
            NoteScreen(navController = navController)
        }
        composable(
            route = Screen.UpdateScreen.route,
            arguments = listOf(
                navArgument("note_id"){
                    type = NavType.IntType
                    nullable = false
                },
                navArgument("note_title"){
                    type = NavType.StringType
                    defaultValue = "No Title"
                    nullable = true
                },
                navArgument("note_content"){
                    type = NavType.StringType
                    defaultValue = "No Content"
                    nullable = true
                }
            )
        ){entry ->
            UpdateScreen(
                noteID = entry.arguments!!.getInt("note_id"),
                noteTitle = entry.arguments?.getString("note_title"),
                noteContent = entry.arguments?.getString("note_content"),
                navController = navController,
            )
        }
    }
}