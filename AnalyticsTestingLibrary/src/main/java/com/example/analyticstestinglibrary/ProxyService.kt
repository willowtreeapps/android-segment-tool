package com.example.analyticstestinglibrary

interface ProxyService {
    fun startRecording(startRecordingURL: String)
    fun stopRecording(stopRecordingURL: String)
    fun clearData(clearURL: String)
    fun exportData(exportURL: String)
}