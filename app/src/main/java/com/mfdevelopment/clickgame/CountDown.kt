package com.mfdevelopment.clickgame

import android.os.CountDownTimer

class CountDown {

    companion object {
        fun createCountDownTimer(
            timeoutSeconds: Long,
            onTick: (millisUntilFinished: Long) -> Unit,
            onFinish: () -> Unit
        ): CountDownTimer {
            val timer = object : CountDownTimer(timeoutSeconds * 1000, 10) {
                override fun onTick(millisUntilFinished: Long) {
                    onTick(millisUntilFinished)
                }

                override fun onFinish() {
                    onFinish()
                }
            }
            return timer
        }
    }
}