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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.fitlog.R
import com.fitlog.app.viewmodel.SettingViewModel
import com.fitlog.domain.models.TrainingProgram


@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    vm: SettingViewModel
){


    val currentProgram = vm.currentProgramFlow.collectAsState(initial = TrainingProgram(name = "", desc = ""))
    val programsList: State<List<TrainingProgram>> = vm.programsListFlow.collectAsState(initial = emptyList())


    val editProgramsState = remember {
        mutableStateOf(false)
    }
    if (editProgramsState.value){
        EditProgramsDialog(
            state = editProgramsState,
            currentProgram = currentProgram.value,
            programsList = programsList.value,
            vm = vm
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
                    Text(text = stringResource(id = R.string.training_program))
                    Text(text = stringResource(R.string.training_program_setting_desc), color = Color.Gray)
                }
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically),
                    onClick = {
                        editProgramsState.value = true
                    }
                ) {
                    Text(text = if (currentProgram.value == null) stringResource(R.string.choose_program) else currentProgram.value!!.name)
                }
            }
        }
    }
}

@Composable
fun EditProgramsDialog(
    currentProgram: TrainingProgram?,
    programsList: List<TrainingProgram>?,
    state: MutableState<Boolean>,
    vm: SettingViewModel
){
    val chosedProgram = remember { mutableStateOf(currentProgram) }
    val deleteProgramState = remember { mutableStateOf(false) }
    val addProgramState = remember { mutableStateOf(false) }

    if (addProgramState.value){
        AddProgramDialog(
            vm = vm,
            state = addProgramState
        )
    }
    if (deleteProgramState.value){
        DeleteProgramDialog(
            program = chosedProgram.value!!,
            vm = vm,
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
                Text(text = stringResource(R.string.back))
            }
        },
        title = {Text(stringResource(R.string.chose_training_program_dialog_header))},
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
                    items(programsList ?: emptyList()){
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
                                    vm.setCurrentProgram(
                                        newCurrentProgram = program,
                                        currentProgram = currentProgram
                                    )
                                    state.value = false
                                }
                            ){
                                Text(text = program.name)
                                Text(text = program.desc, color = Color.Gray)
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
    vm: SettingViewModel,
    state: MutableState<Boolean>
) {
    val programName = remember {
        mutableStateOf("")
    }
    val programDesc = remember {
        mutableStateOf("")
    }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                vm.addProgram(
                    TrainingProgram(name = programName.value, desc = programDesc.value)
                )
                state.value = false
            }) {
                Text(text = stringResource(R.string.add_new_program))
            }
        },
        dismissButton = {
            TextButton(onClick = { state.value = false }) {
                Text(text = stringResource(id = R.string.cancel))
            }
        },
        title = {
            Text(text = stringResource(R.string.add_new_training_program_dialog_header))
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
                    placeholder = { Text(stringResource(R.string.program_name)) })
                TextField(
                    value = programDesc.value,
                    onValueChange = {
                        programDesc.value = it
                    },
                    placeholder = { Text(stringResource(R.string.program_description)) })
            }
        },
    )
}

@Composable
fun DeleteProgramDialog(
    program: TrainingProgram,
    vm: SettingViewModel,
    state: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                vm.deleteProgram(program)
                state.value = false
            }) {
                Text(stringResource(id = R.string.delete))
            }
        },
        dismissButton = {
            TextButton(onClick = {state.value = false}) {
                Text(stringResource(id = R.string.cancel))
            }
        },
        title = {
            Text(text = stringResource(R.string.delete_program_dialog_header, program.name))
        }
    )
}
