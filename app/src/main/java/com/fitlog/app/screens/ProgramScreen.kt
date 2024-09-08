@file:OptIn(ExperimentalMaterial3Api::class)

package com.fitlog.app.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fitlog.app.ProgramViewModel
import com.example.fitlog.R
import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB

@Composable
fun ProgramScreen(
    paddingValues: PaddingValues,
    programViewModel: ProgramViewModel// = viewModel(factory = ProgramViewModel.factory)
){
    val editDayState = remember { mutableStateOf(false) }
    val addDayState = remember { mutableStateOf(false) }
    val deleteDayState = remember { mutableStateOf(false) }
    val listDays = programViewModel.getDays().collectAsState(initial = emptyList())

    if (editDayState.value){
        programViewModel.selectedDay?.let { EditDayDialog(trainingDayDb = it, programViewModel = programViewModel, editDayState) }
    }
    if (addDayState.value){
        AddDayDialog(programViewModel = programViewModel, state = addDayState)
    }

    if (deleteDayState.value){
        programViewModel.selectedDay?.let { DeleteDayDialog(day = it, programViewModel = programViewModel, state = deleteDayState) }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally

    ){

        Text("Training program")
        programViewModel.currentProgram?.program?.let { Text(text = it.name) }
        LazyColumn {
            items(listDays.value){
                TrainingDayCard(
                    trainingDayDb = it,
                    programViewModel = programViewModel,
                    onClickEdit = {
                        programViewModel.selectedDay = it
                        editDayState.value = true

                    },
                    onClickDelete = {
                        programViewModel.selectedDay = it
                        deleteDayState.value = true
                    }
                )
            }
            item {
                AddButton(onClick = {
                    addDayState.value = true
                })
            }
        }
    }
}

@Composable
fun TrainingDayCard(
    trainingDayDb: com.example.fitlog.data.models.TrainingDayDB,
    programViewModel: ProgramViewModel,
    onClickEdit: (com.example.fitlog.data.models.TrainingDayDB) -> Unit,
    onClickDelete: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                onClickEdit.invoke(trainingDayDb)
            }
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = trainingDayDb.name)
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Column(
                    modifier = Modifier.padding(start = 40.dp,top = 5.dp)
                ){
                    trainingDayDb.id?.let {
                        programViewModel.getExercises(trainingDayDb).forEachIndexed{
                            index, exercise ->
                        Text(text = "${index + 1}: ${exercise.name}")
                        }
                    }
                }
                IconButton(onClick = {
                    onClickDelete.invoke()
                }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "delete button")
                }
            }
        }
    }
}


@Composable
fun AddDayDialog(
    programViewModel: ProgramViewModel,
    state: MutableState<Boolean>
) {
    val dayName = remember {
        mutableStateOf("")
    }

    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                programViewModel.addDay(
                    com.example.fitlog.data.models.TrainingDayDB(
                        name = dayName.value,
                        programId = programViewModel.currentProgram?.program?.id
                    )
                )
                state.value = false
            }) {
                Text(text = "Add new day")
            }
        },
        dismissButton = {
             TextButton(onClick = { state.value = false }) {
                 Text(text = "Cancel")
             }
        },
        title = {
            Text(text = "Add new training day")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ){
                TextField(
                    value = dayName.value,
                    onValueChange = { dayName.value = it },
                    placeholder = { Text("Training day name") })
            }
        },
    )
}

@Composable
fun EditDayDialog(
    trainingDayDb: com.example.fitlog.data.models.TrainingDayDB,
    programViewModel: ProgramViewModel,
    state: MutableState<Boolean>
){
    val addExerState = remember {
        mutableStateOf(false)
    }
    val editExerState = remember {
        mutableStateOf(false)
    }
    val deleteExerState = remember {
        mutableStateOf(false)
    }
    val currentExer = remember {
        mutableStateOf(com.example.fitlog.data.models.ExerciseDB())
    }
    if (addExerState.value){
        AddExerDialog(trainingDayDb = trainingDayDb, programViewModel = programViewModel, state = addExerState)
    }
    if (deleteExerState.value){
        DeleteExerDialog(exercise = currentExer.value, programViewModel = programViewModel, state = deleteExerState)
    }
    if (editExerState.value)
        EditExerDialog(
            exercise = currentExer.value,
            programViewModel = programViewModel,
            state = editExerState
        )
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = { state.value = false}) {
                Text(text = "Done")
            }
        },
        title = {Text("Edit exercises of day: ${trainingDayDb.name}")},
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
                    items(programViewModel.getExercises(trainingDayDb)){
                            exercise ->
                        Row (
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .clickable {
                                    currentExer.value = exercise
                                    editExerState.value = true
                                },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Column {
                                Text(text = exercise.name)
                                Row(
                                    Modifier
                                        .fillMaxWidth(0.8f),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ){
                                    Text("${exercise.reps} X ${exercise.sets}", color = Color.Gray)
                                    Text("rest time: ${exercise.restTime}", color = Color.Gray)
                                }
                            }
                            IconButton(onClick = {
                                currentExer.value = exercise
                                deleteExerState.value = true
                            }) {
                                Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "delete button")
                            }
                        }
                    }
                    item {
                        AddButton (onClick = {
                            addExerState.value = true
                        })
                    }
                }
            }
        }
    )
}
@Composable
fun DeleteDayDialog(day: com.example.fitlog.data.models.TrainingDayDB, programViewModel: ProgramViewModel, state: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                programViewModel.deleteDay(day)
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
            Text(text = "Delete ${day.name}?")
        }
    )
}

@Composable
fun DeleteExerDialog(exercise: com.example.fitlog.data.models.ExerciseDB, programViewModel: ProgramViewModel, state: MutableState<Boolean>) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                programViewModel.deleteExer(exercise)
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
                Text(text = "Delete ${exercise.name}?")
        }
    )
}

@Composable
fun AddExerDialog(
    trainingDayDb: com.example.fitlog.data.models.TrainingDayDB,
    programViewModel: ProgramViewModel,
    state: MutableState<Boolean>
) {
    val name = remember { mutableStateOf("") }
    val reps = remember { mutableIntStateOf(12) }
    val sets = remember { mutableIntStateOf(4) }
    val rest = remember { mutableIntStateOf(300) }

    val switchState = remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = {
            state.value = false
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        confirmButton = {
            TextButton(
                onClick = {
                    programViewModel.addExer(
                        com.example.fitlog.data.models.ExerciseDB(
                            name = name.value,
                            reps = reps.intValue,
                            sets = sets.intValue,
                            restTime = rest.intValue,
                            dayId = trainingDayDb.id
                        )
                    )
                    state.value = false
                }) {
                Text("Add exercise")
            }
        },
        dismissButton = {
            TextButton(onClick = { state.value = false}) {
                Text(text = "Cancel")
            }
        },
        title = {Text("Edit day: ${trainingDayDb.name}")},
        text = {
            Column (
                Modifier.fillMaxWidth()
            ){
                TextField(
                    value = name.value,
                    onValueChange = {
                        name.value = it
                    },
                    placeholder = { Text(text = "Exercise name")}
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text("Use custom params")
                    Switch(checked = switchState.value, onCheckedChange = {switchState.value = it})
                }
                if (switchState.value){
                    Column(Modifier.fillMaxWidth()){
                        Row (Modifier.fillMaxWidth()){
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(end = 2.dp, bottom = 4.dp),
                                label = { Text(text = "Reps") },
                                value = reps.intValue.toString(),
                                onValueChange = { reps.intValue = if (it == "") 0 else it.toInt()}
                            )
                            TextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 2.dp, bottom = 4.dp),
                                label = { Text(text = "Sets") },
                                value = sets.intValue.toString(),
                                onValueChange = { sets.intValue = if (it == "") 0 else it.toInt()}
                            )
                        }
                        TextField(
                            label = { Text(text = "Rest time (s)") },
                            value = rest.intValue.toString(),
                            onValueChange = { rest.intValue = if (it == "") 0 else it.toInt()}
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun EditExerDialog(
    exercise: com.example.fitlog.data.models.ExerciseDB,
    programViewModel: ProgramViewModel,
    state: MutableState<Boolean>
){
    val exerName = remember {
        mutableStateOf(exercise.name)
    }
    val exerSets = remember {
        mutableIntStateOf(exercise.sets)
    }
    val exerReps = remember {
        mutableIntStateOf(exercise.reps)
    }
    val exerTime = remember {
        mutableIntStateOf(exercise.restTime)
    }
    val showError = remember {
        mutableStateOf(false)
    }
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                if (exerName.value == ""
                    || exerSets.intValue == 0
                    || exerReps.intValue == 0
                    || exerTime.intValue == 0)
                    showError.value = true
                else {
                    exercise.name = exerName.value
                    exercise.sets = exerSets.intValue
                    exercise.reps = exerReps.intValue
                    exercise.restTime = exerTime.intValue

                    programViewModel.updateExer(exercise)
                    state.value = false
                }
            }) {
                Text(text = "Done")
            }
        },
        title = {Text("Edit exercise")},
        text = {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TextField(
                    value = exerName.value,
                    onValueChange = {
                        exerName.value
                    },
                    placeholder = { Text(text = "Exercise name")}
                )
                Row {
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(end = 2.dp),
                        value = exerSets.intValue.toString(),
                        onValueChange = {
                            exerSets.intValue = if (it == "") 0 else it.toInt()
                        },
                        placeholder = { Text(text = "Sets")}
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp),
                        value = exerReps.intValue.toString(),
                        onValueChange = {
                            exerReps.intValue = if (it == "") 0 else it.toInt()
                        },
                        placeholder = {Text(text = "Reps")}
                    )
                }
                TextField(
                    value = exerTime.intValue.toString(),
                    onValueChange = {
                        exerTime.intValue = if (it == "") 0 else it.toInt()
                    },
                    placeholder = { Text(text = "Rest time")}
                )
                if (showError.value){
                    Text(
                        modifier = Modifier.padding(16.dp),
                        text = "Fill all gaps!",
                        color = Color.Red
                    )
                }
            }
        }
    )
}
