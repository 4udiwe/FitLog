package com.fitlog.app

import android.app.Application
import com.example.fitlog.data.db.TrainingProgramDataBase

class App : Application() {
    val db by lazy { com.example.fitlog.data.db.TrainingProgramDataBase.createDB(this) }
}