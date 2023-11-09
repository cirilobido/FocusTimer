package com.cirilobido.focustimer.presentation.home

import android.os.CountDownTimer
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cirilobido.focustimer.core.Constants.Companion.ONE_HOUR_IN_MIN
import com.cirilobido.focustimer.core.Constants.Companion.ONE_MIN_IN_MILLIS
import com.cirilobido.focustimer.core.Constants.Companion.ONE_MIN_IN_SEC
import com.cirilobido.focustimer.core.Constants.Companion.ONE_SEC_IN_MILLIS
import com.cirilobido.focustimer.domain.model.TimerTypeEnum
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private lateinit var _timer: CountDownTimer

    private var _isTimerActive: Boolean = false

    private val _timeValue =
        mutableLongStateOf(TimerTypeEnum.FOCUS.timeToMillis())
    val timeValueState = _timeValue

    private val _timerTypeState =
        mutableStateOf(TimerTypeEnum.FOCUS)
    val timerTypeState = _timerTypeState

    private val _roundsState =
        mutableIntStateOf(0)
    val roundsState = _roundsState

    private val _todayTimeState =
        mutableLongStateOf(0)
    val todayTimeState = _todayTimeState

    fun onStartTimer() {
        viewModelScope.launch {
            _timer = object : CountDownTimer(
                _timeValue.longValue,
                ONE_SEC_IN_MILLIS
            ) {
                override fun onTick(millisUntilFinished: Long) {
                    _timeValue.longValue = millisUntilFinished
                    _todayTimeState.longValue += ONE_SEC_IN_MILLIS
                }

                override fun onFinish() {
                    onCancelTimer(reset = true)
                }
            }
            _timer.start().also {
                if (!_isTimerActive) _roundsState.intValue += 1
                _isTimerActive = true
            }
        }
    }

    fun onCancelTimer(reset: Boolean = false) {
        try {
            _timer.cancel()
        } catch (_: UninitializedPropertyAccessException) {
            /** Handle cancel timer error */
        }
        if (!_isTimerActive || reset) {
            _timeValue.longValue = _timerTypeState.value.timeToMillis()
        }
        _isTimerActive = false
    }

    private fun onResetTimer() {
        if (_isTimerActive) {
            onCancelTimer()
            onStartTimer()
        }
    }

    fun onIncreaseTime() {
        _timeValue.longValue += ONE_MIN_IN_MILLIS
        onResetTimer()
    }

    fun onDecreaseTime() {
        _timeValue.longValue -= ONE_MIN_IN_MILLIS
        onResetTimer()
        if (_timeValue.longValue < 0) {
            onCancelTimer()
        }
    }

    fun onUpdateType(timerTypeEnum: TimerTypeEnum) {
        _timerTypeState.value = timerTypeEnum
        onCancelTimer(reset = true)
    }

    fun millisToMinutes(millisUntilFinished: Long): String {
        val totalSeconds = millisUntilFinished / ONE_SEC_IN_MILLIS
        val minutes = (totalSeconds / ONE_MIN_IN_SEC).toInt()
        val seconds = (totalSeconds % ONE_MIN_IN_SEC).toInt()
        return String.format("%02d:%02d", minutes, seconds)
    }

    fun millisToHours(milliseconds: Long): String {
        val totalSeconds = milliseconds / ONE_SEC_IN_MILLIS
        val seconds = (totalSeconds % ONE_MIN_IN_SEC)
        val totalMinutes = (totalSeconds / ONE_MIN_IN_SEC).toInt()
        val hours = (totalMinutes / ONE_HOUR_IN_MIN)
        val minutes = (totalMinutes % ONE_HOUR_IN_MIN)
        return if (totalMinutes <= ONE_HOUR_IN_MIN) {
            String.format("%02dm %02ds", minutes, seconds)
        } else {
            String.format("%02dh %02dm", hours, minutes)
        }
    }
}