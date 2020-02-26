package com.example.analyticstestinglibrary.services

import com.example.analyticstestinglibrary.dataobjects.proxy.ProxyLog

interface ProxyService {
    fun startRecording()
    fun stopRecording()
    fun clearData()
    fun exportData(): List<ProxyLog>?
}