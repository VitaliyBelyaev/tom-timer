package ru.belyaev.vitaliy.tomtimer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.belyaev.vitaliy.tomtimer.R

class AppActivity : AppCompatActivity() {
    private val viewModel: AppActivityViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_app)
    }
}