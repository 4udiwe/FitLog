package com.fitlog.app.di

import com.fitlog.domain.usecase.AddExerciseToDayUseCase
import com.fitlog.domain.usecase.AddTrainingDayToProgramUseCase
import com.fitlog.domain.usecase.AddTrainingProgramUseCase
import com.fitlog.domain.usecase.DeleteExerciseUseCase
import com.fitlog.domain.usecase.DeleteTrainingDayUseCase
import com.fitlog.domain.usecase.DeleteTrainingProgramUseCase
import com.fitlog.domain.usecase.GetAllProgramsUseCase
import com.fitlog.domain.usecase.GetCurrentProgramUseCase
import com.fitlog.domain.usecase.GetExercisesOfDayUseCase
import com.fitlog.domain.usecase.GetTrainingDaysOfProgramUseCase
import com.fitlog.domain.usecase.SetCurrentProgramUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<AddExerciseToDayUseCase> {
        AddExerciseToDayUseCase(exerciseReposiotry = get())
    }
    factory<AddTrainingDayToProgramUseCase> {
        AddTrainingDayToProgramUseCase(dayRepository = get())
    }
    factory<DeleteExerciseUseCase> {
        DeleteExerciseUseCase(exerciseReposiotry = get())
    }
    factory<DeleteTrainingDayUseCase> {
        DeleteTrainingDayUseCase(trainingDayRepository = get())
    }
    factory<GetAllProgramsUseCase> {
        GetAllProgramsUseCase(programRepository = get())
    }
    factory<GetCurrentProgramUseCase> {
        GetCurrentProgramUseCase(repository = get())
    }
    factory<GetExercisesOfDayUseCase> {
        GetExercisesOfDayUseCase(exerciseReposiotry = get())
    }
    factory<GetTrainingDaysOfProgramUseCase> {
        GetTrainingDaysOfProgramUseCase(dayRepository = get())
    }
    factory<SetCurrentProgramUseCase> {
        SetCurrentProgramUseCase(programRepository = get())
    }
    factory<AddTrainingProgramUseCase> {
        AddTrainingProgramUseCase(programRepository = get())
    }
    factory<DeleteTrainingProgramUseCase> {
        DeleteTrainingProgramUseCase(programRepository = get())
    }

}