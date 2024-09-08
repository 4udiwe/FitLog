package com.fitlog.app.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fitlog.app.viewmodel.ProgramViewModel
import com.example.fitlog.R
import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.db.TrainingDayDBSaver
import kotlinx.coroutines.delay

@Composable
fun TrainingScreen(
    paddingValues: PaddingValues,
    programViewModel: ProgramViewModel// = viewModel(factory = ProgramViewModel.factory)
){
    val currentDay = rememberSaveable(stateSaver = TrainingDayDBSaver) {
        mutableStateOf(com.example.fitlog.data.models.TrainingDayDB(name = ""))
    }
    val currentExerciseIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    val isTraining = rememberSaveable {
        mutableStateOf(false)
    }
    if (!isTraining.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    "Let's start training!",
                    Modifier.padding(bottom = 10.dp)
                )
                programViewModel.currentProgram?.trainingDayDBS?.forEach{
                    TextButton(onClick = {
                        currentDay.value = it
                        isTraining.value = true
                    }) {
                        Text(text = it.name, fontSize = 20.sp)
                    }
                }
            }
        }
    }
    else {
        LazyColumn (
            Modifier.padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            itemsIndexed(programViewModel.getExercises(currentDay.value)){
                 index, exercise ->
                ExerciseCard(exercise = exercise, isActive = index == currentExerciseIndex.intValue, currentExerciseIndex)
            }
            item{
                TextButton(onClick = { isTraining.value = false}) {
                    Text("End training", fontSize = 20.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 5)
@Composable
fun ExerciseCard(
    exercise: com.example.fitlog.data.models.ExerciseDB = com.example.fitlog.data.models.ExerciseDB(
        name = "Current exercise"
    ),
    isActive: Boolean = false,
    currentExerciseIndex: MutableIntState = mutableIntStateOf(0)
) {

    val setsLast = remember {
        mutableIntStateOf(exercise.sets)
    }

    val timer = remember {
        mutableStateOf(false)
    }
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 4.dp,
                end = 4.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        //сделать обводку и еще че нить
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            if (isActive) {
                if (setsLast.intValue == 0 && !timer.value){
                    currentExerciseIndex.intValue += 1
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 24.dp, top = 16.dp, bottom = 16.dp)
                ){
                    Text(text = exercise.name, fontSize = 20.sp)
                    Text(text = "${exercise.sets} x ${exercise.reps}", color = Color.Gray)
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "Sets last:", color = Color.Gray, fontSize = 20.sp)
                    Text(
                        text =
                            if (setsLast.intValue == 0)
                                "Done!"
                            else
                                setsLast.intValue.toString()
                        ,
                        fontSize = 20.sp
                    )
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    IconButton(
                        modifier = Modifier.fillMaxSize(),
                        onClick = {
                        timer.value = !timer.value
                        if (timer.value)
                            setsLast.intValue -= 1
                    }) {
                        if (timer.value) {
                            Timer(
                                totalTime =
                                if (setsLast.intValue == 0)
                                    60000L
                                else
                                    exercise.restTime.toLong() * 1000
                                ,
                                isOn = timer,
                            )
                        }
                        else{
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_add_24),
                                contentDescription = "icon"
                            )
                        }
                    }
                }
            } else{
                Text(
                    text = exercise.name,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(start = 24.dp, top = 16.dp, bottom = 16.dp)
                )
                Text(
                    text = "${exercise.sets} x ${exercise.reps}",
                    color = Color.Gray,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun Timer(
    isOn: MutableState<Boolean> = mutableStateOf(true),
    modifier: Modifier = Modifier.size(60.dp),
    totalTime: Long = 10000L,
    initialValue:Float = 1f,
    activeColor: Color = Color.Cyan,
    inactiveColor: Color = Color.Gray,
    lineWidth: Dp = 10.dp
) {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    var value by remember {
        mutableStateOf(initialValue)
    }
    var currentTime by remember {
        mutableStateOf(totalTime)
    }
    val isRunning by remember {
        mutableStateOf(true)
    }
    if (currentTime == 0L){
        isOn.value = false
    }
    LaunchedEffect(key1 = isRunning, key2 = currentTime) {
        if (currentTime > 0 && isRunning){
            delay(100L)
            currentTime -= 100L
            value = currentTime / totalTime.toFloat()
        }
    }
    Box(
        modifier = modifier.onSizeChanged { size = it },
        contentAlignment = Alignment.Center
    ){
        androidx.compose.foundation.Canvas(modifier = modifier) {
            drawArc(
                color = inactiveColor,
                startAngle = -215f,
                sweepAngle = 250f,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(lineWidth.toPx(), cap = StrokeCap.Round)
            )
            drawArc(
                color = activeColor,
                startAngle = -215f,
                sweepAngle = 250f * value,
                useCenter = false,
                size = Size(size.width.toFloat(), size.height.toFloat()),
                style = Stroke(lineWidth.toPx(), cap = StrokeCap.Round)
            )
        }
    }
    Text(text = (currentTime/1000L).toString())

}