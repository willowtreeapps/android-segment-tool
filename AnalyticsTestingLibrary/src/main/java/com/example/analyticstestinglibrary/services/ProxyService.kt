package com.example.analyticstestinglibrary.services

interface ProxyService {
    fun startRecording()
    fun stopRecording()
    fun clearData()
    fun exportData(): String?
}