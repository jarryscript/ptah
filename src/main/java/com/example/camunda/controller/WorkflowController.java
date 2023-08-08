package com.example.camunda.controller;


import com.example.camunda.service.impl.CamundaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowController {
    @Autowired
    private CamundaService camundaService;

    public ResponseEntity<?> startWorkflow(){
        return null;
    }

    public ResponseEntity<?> sendMessage(){
        return null;
    }

    public ResponseEntity<?> getVariables(){
        return null;
    }
}
