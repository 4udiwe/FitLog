package com.fitlog.app.di

import androidx.room.Room
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

    single<TrainingProgramDataBase> {
        Room.databaseBuilder(context = get(), TrainingProgramDataBase::class.java, "programs.db")
            .createFromAsset("database/training_programs.db")
            .build()
    }
    single<ProgramDao> {
        val db = get<TrainingProgramDataBase>()
        db.programs()
    }
    single<DayDao> {
        val db = get<TrainingProgramDataBase>()
        db.days()
    }
    single<ExerciseDao> {
        val db = get<TrainingProgramDataBase>()
        db.exercises()
    }


    factory<TrainingProgramRepository>{
        TrainingProgramRepositoryImpl(programDao = get(), dayDao = get(), exerciseDao = get())
    }
    factory<TrainingDayRepository> {
        TrainingDayRepositoryImpl(dayDao = get(), exerciseDao = get())
    }
    factory<ExerciseReposiotry> {
        ExerciseRepositoryImpl(exerciseDao =  get())
    }



}