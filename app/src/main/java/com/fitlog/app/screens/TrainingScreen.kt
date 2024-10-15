package com.fitlog.app.screens

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitlog.R
import com.fitlog.app.viewmodel.TrainingViewModel
import com.fitlog.domain.models.Exercise
import com.fitlog.domain.models.TrainingDay
import com.fitlog.domain.models.TrainingProgram
import kotlinx.coroutines.delay


@Composable
fun TrainingScreen(
    paddingValues: PaddingValues,
    vm: TrainingViewModel
){
    val currentProgram = vm.currentProgramFlow.collectAsState(
        initial = TrainingProgram(
            name = "",
            desc = ""
        )
    )
    val isTraining = rememberSaveable {
        mutableStateOf(false)
    }

    val currentDay = remember {
        mutableStateOf(TrainingDay(name = ""))
    }
    val currentExerciseIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    if (!isTraining.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.verticalScroll(enabled = true, state = rememberScrollState())
            ){
                Text(
                    stringResource(R.string.select_training_day),
                    Modifier.padding(bottom = 10.dp)
                )

                vm.getDays(currentProgram.value).collectAsState(initial = emptyList()).value.forEachIndexed {
                    index, day ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                currentDay.value = day
                                isTraining.value = true
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 30.dp)
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                        ) {
                            Text(text = (index + 1).toString(), color = Color.Gray)
                            Divider(
                                thickness = 4.dp, modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                                    .clip(
                                        RoundedCornerShape(10.dp)
                                    )
                            )
                            Text(text = day.name, fontSize = 30.sp)
                        }
                    }
                }
            }
        }
    }
    else {

        Column (
            Modifier
                .padding(paddingValues)
                .verticalScroll(enabled = true, state = rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            vm.getExercises(currentDay.value).collectAsState(initial = emptyList()).value.forEachIndexed{
                    index, exercise ->
                ExerciseCard(exercise = exercise, isActive = index == currentExerciseIndex.intValue, currentExerciseIndex)
            }
            TextButton(onClick = {
                isTraining.value = false
            }) {
                Text(stringResource(R.string.end_training), fontSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 5)
@Composable
fun ExerciseCard(
    exercise: Exercise = Exercise(name = "Exercise exercise name"),
    isActive: Boolean = false,
    currentExerciseIndex: MutableIntState = mutableIntStateOf(0)
) {

    val setsLast = remember {
        mutableIntStateOf(exercise.sets)
    }

    val timer = remember {
        mutableStateOf(false)
    }
    if (setsLast.intValue == 0 && !timer.value){
        currentExerciseIndex.intValue += 1
        setsLast.intValue--
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
    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            if (setsLast.intValue == 0 && !timer.value){
                currentExerciseIndex.intValue += 1
            }
            Column (
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row (
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = exercise.name,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.7f)
                            .padding(start = 30.dp, top = 16.dp, bottom = 16.dp, end = 30.dp)
                    )
                    Text(
                        text = "${exercise.sets} x ${exercise.reps}",
                        color = Color.Gray,
                        fontSize = 20.sp
                    )
                }
                if (isActive) {
                    Divider(
                        thickness = 4.dp, modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp)
                            .clip(
                                RoundedCornerShape(10.dp)
                            )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Row (modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .padding(
                                    start = 30.dp,
                                    top = 16.dp,
                                    bottom = 16.dp
                                ), verticalAlignment = Alignment.CenterVertically){
                            if (setsLast.intValue == 0){
                                Text(text = stringResource(id = R.string.done), fontSize = 28.sp)
                            } else{
                                Text(
                                    modifier = Modifier.padding(
                                        end = 10.dp
                                    ),
                                    text = stringResource(R.string.sets_last),
                                    color = Color.Gray,
                                    fontSize = 20.sp
                                )
                                Text(text = setsLast.intValue.toString(), fontSize = 28.sp)
                            }
                        }
                        IconButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .padding(top = 10.dp, bottom = 10.dp),
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
                                        exercise.restTime.toLong() * 1000,
                                    isOn = timer,
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Done,
                                    modifier = Modifier.size(40.dp),
                                    contentDescription = "icon",
                                )
                            }
                        }
                    }
                }
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
    activeColor: Color = colorResource(id = R.color.purple_500),
    inactiveColor: Color = Color.Transparent,
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
    Text(text =
    if (currentTime / 1000L > 60){
        "${currentTime/ 1000L / 60}:${
            if (currentTime/ 1000L % 60 >= 10)
                currentTime/ 1000L % 60
            else
                "0${currentTime/ 1000L % 60}"
        }"
    }
    else
        (currentTime / 1000L).toString(),
        fontSize = 18.sp
    )
}


