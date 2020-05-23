package ru.belyaev.vitaliy.tomtimer.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.belyaev.vitaliy.tomtimer.presentation.AppActivityViewModel

val viewModelModule = module {
    viewModel { AppActivityViewModel() }
}