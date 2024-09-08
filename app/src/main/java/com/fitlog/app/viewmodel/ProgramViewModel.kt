package com.fitlog.app.viewmodel

import androidx.lifecycle.ViewModel

import com.fitlog.data.db.TrainingProgramDataBase
import com.fitlog.data.repository.TrainingProgramRepositoryImpl
import com.fitlog.domain.usecase.AddExerciseToDayUseCase
import com.fitlog.domain.usecase.AddTrainingDayToProgramUseCase
import com.fitlog.domain.usecase.DeleteExerciseUseCase
import com.fitlog.domain.usecase.DeleteTrainingDayUseCase
import com.fitlog.domain.usecase.GetCurrentProgramUseCase
import com.fitlog.domain.usecase.GetExercisesOfDayUseCase
import com.fitlog.domain.usecase.GetTrainingDaysOfProgramUseCase


class ProgramViewModel(
    private val getCurrentProgramUseCase: GetCurrentProgramUseCase,
    private val getTrainingDaysOfProgramUseCase: GetTrainingDaysOfProgramUseCase,
    private val addTrainingDayToProgramUseCase: AddTrainingDayToProgramUseCase,
    private val deleteTrainingDayUseCase: DeleteTrainingDayUseCase,
    private val getExercisesOfDayUseCase: GetExercisesOfDayUseCase,
    private val addExerciseToDayUseCase: AddExerciseToDayUseCase,
    private val deleteExerciseUseCase: DeleteExerciseUseCase
) : ViewModel() {


    /*


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

     */
}