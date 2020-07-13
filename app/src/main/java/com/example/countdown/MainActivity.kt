package com.example.countdown

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler.post(object : Runnable {
            override fun run() {
                // Keep the postDelayed before the updateTime(), so when the event ends, the handler will stop too.
                handler.postDelayed(this, 1000)
                updateTime()
            }
        })
    }



    fun updateTime() {

        val currentDate = Calendar.getInstance()

        val eventDate = Calendar.getInstance()
        
        eventDate[Calendar.YEAR] = 2020
        eventDate[Calendar.MONTH] = 6 // 0-11 so 1 less
        eventDate[Calendar.DAY_OF_MONTH] = 12
        eventDate[Calendar.HOUR] = 17
        eventDate[Calendar.MINUTE] = 45
        eventDate[Calendar.SECOND] = 0
        eventDate.timeZone = TimeZone.getTimeZone("GMT")
        val diff = eventDate.timeInMillis - currentDate.timeInMillis

        val days = diff / (24 * 60 * 60 * 1000)
        val hours = diff / (1000 * 60 * 60) % 24
        val minutes = diff / (1000 * 60) % 60
        val seconds = (diff / 1000) % 60

        countdown_text.text = "${days}d ${hours}h ${minutes}m ${seconds}s"
        endEvent(currentDate, eventDate)
    }

    private fun endEvent(currentdate: Calendar, eventdate: Calendar) {
        if (currentdate.time >= eventdate.time) {
            countdown_text.text = "Happy New Year!"
            //Stop Handler
            handler.removeMessages(0)
        }
    }

}