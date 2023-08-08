package com.example.camunda.service.impl;

import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CamundaService {

    @Autowired
    private ProcessEngine processEngine;

    public String startWorkflow(String processDefinitionKey) {
        return processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey).getProcessInstanceId();
    }

    public void sendMessage() {
    }

    public Map<String, Object> getVariables() {
        String executionId = processEngine.getTaskService().createTaskQuery().processInstanceBusinessKey("")
                .processInstanceId("")
                .singleResult().getExecutionId();

        return processEngine.getRuntimeService().getVariables(executionId);
    }
}
