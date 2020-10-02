package com.thoughtworks.archguard.report.controller

import com.thoughtworks.archguard.report.domain.testing.MethodInfoListDTO
import com.thoughtworks.archguard.report.domain.testing.TestBadSmellService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/systems/{systemId}/test-bad-smell")
class TestBadSmellController(val testBadSmellService: TestBadSmellService) {

    @GetMapping("/static/methods")
    fun getModulesAboveLineThreshold(@PathVariable("systemId") systemId: Long,
                                     @RequestParam(value = "numberPerPage") limit: Long,
                                     @RequestParam(value = "currentPageNumber") currentPageNumber: Long)
            : ResponseEntity<MethodInfoListDTO> {
        val offset = (currentPageNumber - 1) * limit
        return ResponseEntity.ok(testBadSmellService.getStaticMethodList(systemId, limit, offset))
    }

}