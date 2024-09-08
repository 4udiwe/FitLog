package com.fitlog.app.di

import com.fitlog.data.db.DayDao
import com.fitlog.data.db.ExerciseDao
import com.fitlog.data.db.ProgramDao
import com.fitlog.data.db.TrainingProgramDataBase
import com.fitlog.data.repository.ExerciseRepositoryImpl
import com.fitlog.data.repository.TrainingDayRepositoryImpl
import com.fitlog.data.repository.TrainingProgramRepositoryImpl
import com.fitlog.domain.repository.ExerciseReposiotry
import com.fitlog.domain.repository.TrainingDayRepository
import com.fitlog.domain.repository.TrainingProgramRepository
import org.koin.dsl.module

val dataModule = module {

    single<ProgramDao> {
        TrainingProgramDataBase.createDB(context = get()).programs()
    }
    single<DayDao> {
        TrainingProgramDataBase.createDB(context = get()).days()
    }
    single<ExerciseDao> {
        TrainingProgramDataBase.createDB(context = get()).exercises()
    }


    single<TrainingProgramRepository> {
        TrainingProgramRepositoryImpl(programDao = get())
    }
    single<TrainingDayRepository> {
        TrainingDayRepositoryImpl(dayDao = get())
    }
    single<ExerciseReposiotry> {
        ExerciseRepositoryImpl(exerciseDao =  get())
    }

}