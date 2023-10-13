package com.ptah.camunda.controller

import com.ptah.camunda.model.dto.Message
import com.ptah.camunda.model.dto.Process
import com.ptah.camunda.service.CamundaService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/workflow")
class WorkflowController {
    @Autowired
    private val camundaService: CamundaService? = null

    @PostMapping("/start")
    fun startWorkflow(@RequestBody process: Process): ResponseEntity<*> {
        return ResponseEntity.ok(camundaService!!.startWorkflow(process.businessKey, process.variables))
    }

    @PostMapping("/message")
    fun sendMessage(@RequestBody message: Message): ResponseEntity<*> {
        return ResponseEntity.ok(
            camundaService!!.sendMessage(
                message.messageName,
                message.processInstanceId,
                message.variables
            )
        )
    }

    @GetMapping("variables/{processInstanceId}")
    fun getVariables(@PathVariable("processInstanceId") processInstanceId: String?): ResponseEntity<*> {
        return ResponseEntity.ok(camundaService!!.getVariables(processInstanceId))
    }

    @GetMapping("/processInstance/{processInstanceId}")
    fun getProcessInstance(@PathVariable("processInstanceId") processInstanceId: String?): ResponseEntity<*> {
        return ResponseEntity.ok(camundaService!!.getProcessInstance(processInstanceId))
    }
}
