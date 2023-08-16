package com.example.camunda.controller;

import com.example.camunda.model.dto.Message;
import com.example.camunda.model.dto.Process;
import com.example.camunda.service.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class WorkflowController {
    @Autowired
    private CamundaService camundaService;

    @PostMapping("/start")
    public ResponseEntity<?> startWorkflow(@RequestBody Process process) {
        return ResponseEntity.ok(camundaService.startWorkflow(process.getBusinessKey(), process.getVariables()));
    }

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(camundaService.sendMessage(message.getMessageName(), message.getProcessInstanceId(), message.getVariables()));
    }

    @GetMapping("variables/{processInstanceId}")
    public ResponseEntity<?> getVariables(@PathVariable("processInstanceId") String processInstanceId) {
        return ResponseEntity.ok(camundaService.getVariables(processInstanceId));
    }

    @GetMapping("/processInstance/{processInstanceId}")
    public ResponseEntity<?> getProcessInstance(@PathVariable("processInstanceId") String processInstanceId) {
        return ResponseEntity.ok(camundaService.getProcessInstance(processInstanceId));
    }
}
