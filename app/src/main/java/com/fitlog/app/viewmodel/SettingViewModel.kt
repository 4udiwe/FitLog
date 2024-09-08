package com.fitlog.app.viewmodel

import androidx.lifecycle.ViewModel
import com.fitlog.domain.usecase.GetAllProgramsUseCase
import com.fitlog.domain.usecase.GetCurrentProgramUseCase
import com.fitlog.domain.usecase.SetCurrentProgramUseCase

class SettingViewModel(
    private val getCurrentProgramUseCase: GetCurrentProgramUseCase,
    private val getAllProgramsUseCase: GetAllProgramsUseCase,
    private val setCurrentProgramUseCase: SetCurrentProgramUseCase
): ViewModel() {
}