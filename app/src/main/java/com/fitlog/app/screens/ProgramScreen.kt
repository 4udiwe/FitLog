@file:OptIn(ExperimentalMaterial3Api::class)

package com.fitlog.app.screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitlog.app.viewmodel.ProgramViewModel
import com.example.fitlog.R
import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram

@Composable
fun ProgramScreen(
    paddingValues: PaddingValues,
    vm: ProgramViewModel
){

    val editDayState = remember { mutableStateOf(false) }
    val addDayState = remember { mutableStateOf(false) }
    val deleteDayState = remember { mutableStateOf(false) }


    val currentProgram: State<TrainingProgram?> = vm.currentProgramFlow.collectAsState(initial = TrainingProgram(name = "", desc = ""))
    val listDays = currentProgram.value?.let { vm.getDays(it).collectAsState(initial = emptyList()) }

    if (editDayState.value){
        EditDayDialog(vm = vm, state = editDayState)
    }
    if (addDayState.value){
        AddDayDialog(vm = vm, state = addDayState, program = currentProgram.value!!)
    }

    if (deleteDayState.value){
        DeleteDayDialog(vm = vm, state = deleteDayState)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)

    ){
        LazyColumn {
            item {
                Column (modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp), horizontalAlignment = Alignment.End){
                    Text("Training program:", color = Color.Gray)
                    Text(text = currentProgram.value?.name ?: "", fontSize = 22.sp)
                }
            }
            if (listDays != null) {
                items(listDays.value)
                {
                    TrainingDayCard(
                        trainingDay = it,
                        vm = vm,
                        onClickEdit = {
                            vm.selectedDay = it
                            editDayState.value = true

                        },
                        onClickDelete = {
                            vm.selectedDay = it
                            deleteDayState.value = true
                        }
                    )
                }
            }
            item {
                AddButton(onClick = {
                    addDayState.value = true
                })
                Text(text = "", Modifier.padding(bottom = 40.dp))
            }
        }
    }
}

@Composable
fun TrainingDayCard(
    trainingDay: TrainingDay = TrainingDay(
        name = "Trainging day name"
    ),
    vm: ProgramViewModel,
    onClickEdit: (TrainingDay) -> Unit,
    onClickDelete: () -> Unit
){
    var expandedMenu by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                verticalAlignment = Alignment.CenterVertically
                //horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = trainingDay.name,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(end = 20.dp),
                    fontSize = 20.sp
                )
                IconButton(
                    onClick = {
                        expandedMenu = true
                    }
                ) {
                    Icon(Icons.Default.MoreVert, contentDescription = "dropdown menu")
                }
                DropdownMenu(
                    expanded = expandedMenu,
                    onDismissRequest = { expandedMenu = false },
                    offset = DpOffset(x = 220.dp, y = 0.dp)
                ) {
                    DropdownMenuItem(
                        text = {
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(Icons.Default.Edit, contentDescription = "edit", Modifier.padding(horizontal = 10.dp))
                                Text(text = "Edit")
                            }
                        },
                        onClick = { onClickEdit.invoke(trainingDay) }
                    )
                    DropdownMenuItem(
                        text = {
                            Row (
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(Icons.Default.Delete, contentDescription = "delete", Modifier.padding(horizontal = 10.dp))
                                Text(text = "Delete")
                            }
                        },
                        onClick = { onClickDelete.invoke() }
                    )
                }
            }
            Divider(thickness = 4.dp, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, end = 30.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ))

            Column(
                modifier = Modifier.padding(start = 40.dp,top = 5.dp, end = 30.dp, bottom = 20.dp)
            ){
                vm.getExercises(trainingDay).collectAsState(initial = emptyList()).value.forEachIndexed{
                    index, exercise ->
                    if (index > 0){
                        Divider(thickness = 2.dp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 60.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            ))
                    }
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            modifier = Modifier.padding(end = 20.dp),
                            text = (index + 1).toString(),
                            color = Color.Gray
                        )
                        Text(
                            text = exercise.name,
                            fontSize = 18.sp,
                            modifier = Modifier
                                .fillMaxWidth(0.75f)
                                .padding(end = 40.dp),
                        )
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp),
                            text = "${exercise.sets} x ${exercise.reps}",
                            color = Color.Gray,
                            maxLines = 1
                        )

                    }
                }
            }
        }
    }
}
@Composable
fun AddDayDialog(
    vm: ProgramViewModel,
    state: MutableState<Boolean>,
    program: TrainingProgram
) {
    val dayName = remember {
        mutableStateOf("")
    }
    var errorNote by remember {
        mutableStateOf(false)
    }
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                if (dayName.value != "") {
                    vm.addDay(newDay = TrainingDay(name = dayName.value), program = program)
                    state.value = false
                } else {
                    errorNote = true
                }
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
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                TextField(
                    value = dayName.value,
                    onValueChange = { dayName.value = it },
                    placeholder = {
                        Text("Training day name")
                    }
                )
                val errorOffset by animateIntAsState(
                    targetValue = if (errorNote) 0 else 300,
                    animationSpec = spring(Spring.DampingRatioHighBouncy),
                    label = "Error animation"
                )
                Text(
                    text = "Enter training day name!",
                    Modifier
                        .padding(10.dp)
                        .offset(x = errorOffset.dp, y = 0.dp),
                    color = Color.Red,
                )
            }
        }
    )
}


@Composable
fun EditDayDialog(
    vm: ProgramViewModel,
    state: MutableState<Boolean>
){
    val trainingDay = vm.selectedDay!!
    val addExerState = remember {
        mutableStateOf(false)
    }
    val editExerState = remember {
        mutableStateOf(false)
    }
    val deleteExerState = remember {
        mutableStateOf(false)
    }

    if (addExerState.value){
        AddExerDialog(vm = vm, state = addExerState)
    }
    if (deleteExerState.value){
        DeleteExerDialog(vm = vm, state = deleteExerState)
    }
    if (editExerState.value)
        EditExerDialog(vm = vm, day = trainingDay, state = editExerState)
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = {
            state.value = false
        },
        confirmButton = {
            TextButton(onClick = {
                state.value = false
            }) {
                Text(text = "Done")
            }
        },
        title = {
            Column {
                Text("Edit exercises of day: ${trainingDay.name}")
                Divider(thickness = 4.dp, modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 6.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    ))
            }
        },
        text = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                vm.getExercises(trainingDay).collectAsState(initial = emptyList()).value.forEach{
                        exercise ->
                    Row (
                        Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                vm.selectedExercise = exercise
                                editExerState.value = true
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Column (
                            Modifier.fillMaxWidth(0.9f)
                        ){
                            Text(text = exercise.name, fontSize = 18.sp)
                            Row(
                                Modifier
                                    .fillMaxWidth(0.8f),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text("${exercise.reps} X ${exercise.sets}", color = Color.Gray, modifier = Modifier.fillMaxWidth(0.5f))
                                Text("rest: ${exercise.restTime}", color = Color.Gray)
                                Text(text = "s.")
                            }
                        }
                        IconButton(onClick = {
                            vm.selectedExercise = exercise
                            deleteExerState.value = true
                        }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_delete_24), contentDescription = "delete button")
                        }
                    }
                    Divider(thickness = 2.dp, modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                    )
                }
                AddButton (onClick = {
                    addExerState.value = true
                })
            }
        }
    )
}
@Composable
fun DeleteDayDialog(
    vm: ProgramViewModel,
    state: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                vm.deleteDay(vm.selectedDay!!)
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
            Text(text = "Delete day:\n${vm.selectedDay!!.name}?")
        }
    )
}

@Composable
fun DeleteExerDialog(
    vm: ProgramViewModel,
    state: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { state.value = false },
        confirmButton = {
            TextButton(onClick = {
                vm.deleteExercise(vm.selectedExercise!!)
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
            Text(text = "Delete exercise:\n${vm.selectedExercise!!.name}?")
        }
    )
}

@Composable
fun AddExerDialog(
    vm: ProgramViewModel,
    state: MutableState<Boolean>
) {
    val name = remember { mutableStateOf("") }
    val reps = remember { mutableIntStateOf(12) }
    val sets = remember { mutableIntStateOf(4) }
    val rest = remember { mutableIntStateOf(200) }

    var errorNote by remember { mutableStateOf(false) }

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
                    if (name.value != "" && reps.intValue > 0 && sets.intValue > 0 && rest.intValue > 0) {
                        vm.addExercise(
                            Exercise(
                                name = name.value,
                                reps = reps.intValue,
                                sets = sets.intValue,
                                restTime = rest.intValue
                            ), vm.selectedDay!!
                        )
                        state.value = false
                    } else {
                        errorNote = true
                    }
                }
            ) {
                Text("Add exercise")
            }
        },
        dismissButton = {
            TextButton(onClick = { state.value = false}) {
                Text(text = "Cancel")
            }
        },
        title = {Text("Add new exercise")},
        text = {
            Column (
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
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
                val errorOffset by animateIntAsState(
                    targetValue = if (errorNote) 0 else 300,
                    animationSpec = spring(Spring.DampingRatioHighBouncy),
                    label = "Error animation"
                )
                Text(
                    text = "Enter exercise name!",
                    Modifier
                        .padding(10.dp)
                        .offset(x = errorOffset.dp, y = 0.dp),
                    color = Color.Red,
                )
            }
        }
    )
}

@Composable
fun EditExerDialog(
    day: TrainingDay,
    vm: ProgramViewModel,
    state: MutableState<Boolean>
){
    val exerName = remember {
        mutableStateOf(vm.selectedExercise!!.name)
    }
    val exerSets = remember {
        mutableIntStateOf(vm.selectedExercise!!.sets)
    }
    val exerReps = remember {
        mutableIntStateOf(vm.selectedExercise!!.reps)
    }
    val exerTime = remember {
        mutableIntStateOf(vm.selectedExercise!!.restTime)
    }
    val showError = remember {
        mutableStateOf(false)
    }
    AlertDialog(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        onDismissRequest = { state.value = false },
        dismissButton = {
            TextButton(onClick = { state.value = false }) {
                Text("Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = {
                if (exerName.value == ""
                    || exerSets.intValue <= 0
                    || exerReps.intValue <= 0
                    || exerTime.intValue <= 0)
                    showError.value = true
                else {
                    vm.selectedExercise!!.name = exerName.value
                    vm.selectedExercise!!.sets = exerSets.intValue
                    vm.selectedExercise!!.reps = exerReps.intValue
                    vm.selectedExercise!!.restTime = exerTime.intValue

                    vm.addExercise(vm.selectedExercise!!, day)
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
                        exerName.value = it
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
                        label = {Text(text = "Sets")}
                    )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 2.dp),
                        value = exerReps.intValue.toString(),
                        onValueChange = {
                            exerReps.intValue = if (it == "") 0 else it.toInt()
                        },
                        label = {Text(text = "Reps")}
                    )
                }
                TextField(
                    value = exerTime.intValue.toString(),
                    onValueChange = {
                        exerTime.intValue = if (it == "") 0 else it.toInt()
                    },
                    label = { Text(text = "Rest time")}
                )
                val errorOffset by animateIntAsState(
                    targetValue = if (showError.value) 0 else 300,
                    animationSpec = spring(Spring.DampingRatioHighBouncy),
                    label = "Error animation"
                )
                Text(
                    text = "Fill all gaps!",
                    Modifier
                        .padding(10.dp)
                        .offset(x = errorOffset.dp, y = 0.dp),
                    color = Color.Red,
                )
            }
        }
    )
}
