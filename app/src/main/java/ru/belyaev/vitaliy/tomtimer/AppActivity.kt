package ru.belyaev.vitaliy.tomtimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlinx.android.synthetic.main.ac_app.dateTime
import kotlinx.android.synthetic.main.ac_app.showTimeButton
import toothpick.Toothpick

class AppActivity : AppCompatActivity() {

    private val dateTimeFormatter =
        buildDTFOfPattern("dd.MM.yyyy, HH:mm:ss", ZoneId.systemDefault())

    @Inject
    lateinit var dateTimeProvider: CurrentDateTimeProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        Toothpick.inject(this, Toothpick.openScope(APP_SCOPE))
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.ac_app)
        setCurrentDateTime()
        showTimeButton.setOnClickListener { setCurrentDateTime() }
    }

    private fun setCurrentDateTime() {
        val localDateTime = LocalDateTime.now()
        dateTime.text = dateTimeFormatter.format(localDateTime)
    }

    private fun buildDTFOfPattern(pattern: String, zoneId: ZoneId?): DateTimeFormatter =
        DateTimeFormatter
            .ofPattern(pattern)
            .run { zoneId?.let { withZone(zoneId) } ?: this }

    companion object {
        const val APP_SCOPE = "APP_SCOPE"
    }
}