@file:OptIn(ExperimentalMaterial3Api::class)

package com.fitlog.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fitlog.app.ProgramViewModel
import com.example.fitlog.R
import com.example.fitlog.data.db.ProgramDB
import com.example.fitlog.data.models.TrainingProgramDB


@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    programViewModel: ProgramViewModel// = viewModel(factory = ProgramViewModel.factory)
){
    val editProgramsState = remember {
        mutableStateOf(false)
    }
    val currentProgram = programViewModel.getCurrentProgram().collectAsState(com.example.fitlog.data.db.ProgramDB())
    if (editProgramsState.value){
        EditProgramsDialog(
            programViewModel = programViewModel,
            state = editProgramsState
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(paddingValues),
        ){

        Card (modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(0.6f)
                ) {
                    Text(text = "Training program")
                    Text(text = "Chose your training program or add new one", color = Color.Gray)
                }
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    onClick = {
                        editProgramsState.value = true
                    }
                ) {
                    Text(text = currentProgram.value?.program?.name ?: "Choose program")
                }
            }
        }
    }
}

@Composable
fun EditProgramsDialog(
    programViewModel: ProgramViewModel,
    state: MutableState<Boolean>
){
    val listPrograms = programViewModel.getPrograms().collectAsState(initial = emptyList())

    val chosedProgram = remember { mutableStateOf(com.example.fitlog.data.db.ProgramDB()) }
    val deleteProgramState = remember { mutableStateOf(false) }
    val addProgramState = remember { mutableStateOf(false) }

    if (addProgramState.value){
        AddProgramDialog(
            programViewModel = programViewModel,
            state = addProgramState
        )
    }
    if (deleteProgramState.value){
        DeleteProgramDialog(
            programDB = chosedProgram.value,
            programViewModel = programViewModel,
            state = deleteProgramState
        )
    }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = {
            state.value = false
            },
        confirmButton = {
            TextButton(onClick = { state.value = false}) {
                Text(text = "Back")
            }
        },
        title = {Text("Chose training program")},
        text = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp)
                ){
                    items(listPrograms.value){
                            program ->
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column (modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .clickable {
                                    programViewModel.changeProgram(program)
                                    state.value = false
                                }
                            ){
                                Text(text = program.program.name)
                                Text(text = program.program.desc, color = Color.Gray)
                            }
                            IconButton(onClick = {
                                chosedProgram.value = program
                                deleteProgramState.value = true
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_delete_24),
                                    contentDescription = "delete button"
                                )
                            }
                        }
                    }
                    item {
                        AddButton (onClick = {
                            addProgramState.value = true
                        })
                    }
                }
            }
        }
    )
}

@Composable
fun AddProgramDialog(
    programViewModel: ProgramViewModel,
    state: MutableState<Boolean>
) {
    val programName = remember {
        mutableStateOf("")
    }
    val programDesc = remember {
        mutableStateOf("Default description")
    }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                programViewModel.currentProgram?.program?.current = false

                programViewModel.addProgram(
                    com.example.fitlog.data.models.TrainingProgramDB(
                        name = programName.value,
                        desc = programDesc.value
                    )
                )
                state.value = false
            }) {
                Text(text = "Add new program")
            }
        },
        dismissButton = {
            TextButton(onClick = { state.value = false }) {
                Text(text = "Cancel")
            }
        },
        title = {
            Text(text = "Add new training program")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                TextField(
                    value = programName.value,
                    onValueChange = { programName.value = it },
                    placeholder = { Text("Program name") })
                TextField(
                    value = programDesc.value,
                    onValueChange = { programDesc.value = it },
                    placeholder = { Text("Program description") })
            }
        },
    )
}

@Composable
fun DeleteProgramDialog(
    programDB: com.example.fitlog.data.db.ProgramDB,
    programViewModel: ProgramViewModel,
    state: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                programViewModel.deleteProgram(programDB)
                state.value = false
            }) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = {state.value = false}) {
                Text("Cancel")
            }
        },
        title = {
            Text(text = "Delete ${programDB.program.name}?")
        }
    )
}

