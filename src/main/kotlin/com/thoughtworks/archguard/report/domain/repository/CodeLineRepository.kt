package com.thoughtworks.archguard.report.domain.repository

import com.thoughtworks.archguard.report.domain.model.MethodLine


interface CodeLineRepository {
    fun getMethodLinesAboveThresholdCount(systemId: Long, threshold: Int): Long

    fun getMethodLinesAboveThreshold(systemId: Long, threshold: Int, limit: Long, offset: Long): List<MethodLine>
}