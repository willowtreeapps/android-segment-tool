package com.example.analyticstestinglibrary

interface ProxyService {
    fun startRecording()
    fun stopRecording()
    fun clearData()
    fun exportData(): ProxyLog
}