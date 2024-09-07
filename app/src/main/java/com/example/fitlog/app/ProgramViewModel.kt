package com.example.fitlog.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.fitlog.data.models.ExerciseDB
import com.example.fitlog.data.db.ProgramDB
import com.example.fitlog.data.models.TrainingDayDB
import com.example.fitlog.data.models.TrainingProgramDB
import com.example.fitlog.data.db.TrainingProgramDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class ProgramViewModel(private val db : TrainingProgramDataBase) : ViewModel() {

    var currentProgram: ProgramDB?
    var selectedDay: TrainingDayDB? = null

    init {
        runBlocking {
            currentProgram = db.programs().currentProgram()
        }
    }

    fun getCurrentProgram(): Flow<ProgramDB> {
        return db.programs().getCurrentProgram()
    }
    fun changeProgram(newProgram: ProgramDB){
        viewModelScope.launch {
            currentProgram?.program?.current = false
            currentProgram?.let { db.programs().updateProgram(it.program) }
            newProgram.program.current = true
            db.programs().updateProgram(newProgram.program)
            currentProgram = db.programs().currentProgram()
        }
    }

    fun getExercises(dayDB: TrainingDayDB) :List<ExerciseDB> = runBlocking{
        return@runBlocking db.exercises().exercisesOfDay(checkNotNull(dayDB.id))
    }
    fun getPrograms(): Flow<List<ProgramDB>> {
        return db.programs().all()
    }

    fun addProgram(newProgram: TrainingProgramDB){
        viewModelScope.launch {
            db.programs().addProgram(newProgram)
        }
    }
    fun deleteProgram(oldProgram: ProgramDB){
        val job = viewModelScope.launch {
            oldProgram.trainingDayDBS.forEach {
                deleteDay(it)
            }
        }
        viewModelScope.launch {
            job.join()
            db.programs().removeProgram(oldProgram.program)
        }
    }

    fun getDays(): Flow<List<TrainingDayDB>> {
        return db.days().daysOfProgram(currentProgram?.program?.id)
    }
    fun addDay(newDay: TrainingDayDB){
        viewModelScope.launch {
            db.days().addDay(newDay)
        }
    }
    fun deleteDay(oldDay: TrainingDayDB){
        val job = viewModelScope.launch {
            val listExers = getExercises(oldDay)
            listExers.forEach{
                deleteExer(it)
            }
        }
        viewModelScope.launch {
            job.join()
            db.days().removeDay(oldDay)
        }
    }

    fun addExer(newExer: ExerciseDB){
        viewModelScope.launch {
            db.exercises().addExercise(newExer)
        }
    }
    fun updateExer(exer: ExerciseDB){
        viewModelScope.launch {
            db.exercises().updateExercise(exer)
        }
    }
    fun deleteExer(oldExer: ExerciseDB){
        viewModelScope.launch {
            db.exercises().deleteExercise(oldExer)
        }
    }

    companion object{
        val factory: ViewModelProvider.Factory = object :ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val database = (checkNotNull(extras[APPLICATION_KEY]) as App).db
                return ProgramViewModel(database) as T
            }
        }
    }
}