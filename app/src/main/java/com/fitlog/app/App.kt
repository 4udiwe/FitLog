package com.fitlog.app

import android.app.Application
import com.fitlog.data.db.TrainingProgramDataBase

class App : Application() {
    val db by lazy { TrainingProgramDataBase.createDB(this) }
}