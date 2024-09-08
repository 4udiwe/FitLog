package com.fitlog.app

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


class ProgramViewModel(private val db : com.example.fitlog.data.db.TrainingProgramDataBase) : ViewModel() {

    var currentProgram: com.example.fitlog.data.db.ProgramDB?
    var selectedDay: com.example.fitlog.data.models.TrainingDayDB? = null

    init {
        runBlocking {
            currentProgram = db.programs().currentProgram()
        }
    }

    fun getCurrentProgram(): Flow<com.example.fitlog.data.db.ProgramDB> {
        return db.programs().getCurrentProgram()
    }
    fun changeProgram(newProgram: com.example.fitlog.data.db.ProgramDB){
        viewModelScope.launch {
            currentProgram?.program?.current = false
            currentProgram?.let { db.programs().updateProgram(it.program) }
            newProgram.program.current = true
            db.programs().updateProgram(newProgram.program)
            currentProgram = db.programs().currentProgram()
        }
    }

    fun getExercises(dayDB: com.example.fitlog.data.models.TrainingDayDB) :List<com.example.fitlog.data.models.ExerciseDB> = runBlocking{
        return@runBlocking db.exercises().exercisesOfDay(checkNotNull(dayDB.id))
    }
    fun getPrograms(): Flow<List<com.example.fitlog.data.db.ProgramDB>> {
        return db.programs().all()
    }

    fun addProgram(newProgram: com.example.fitlog.data.models.TrainingProgramDB){
        viewModelScope.launch {
            db.programs().addProgram(newProgram)
        }
    }
    fun deleteProgram(oldProgram: com.example.fitlog.data.db.ProgramDB){
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

    fun getDays(): Flow<List<com.example.fitlog.data.models.TrainingDayDB>> {
        return db.days().daysOfProgram(currentProgram?.program?.id)
    }
    fun addDay(newDay: com.example.fitlog.data.models.TrainingDayDB){
        viewModelScope.launch {
            db.days().addDay(newDay)
        }
    }
    fun deleteDay(oldDay: com.example.fitlog.data.models.TrainingDayDB){
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

    fun addExer(newExer: com.example.fitlog.data.models.ExerciseDB){
        viewModelScope.launch {
            db.exercises().addExercise(newExer)
        }
    }
    fun updateExer(exer: com.example.fitlog.data.models.ExerciseDB){
        viewModelScope.launch {
            db.exercises().updateExercise(exer)
        }
    }
    fun deleteExer(oldExer: com.example.fitlog.data.models.ExerciseDB){
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