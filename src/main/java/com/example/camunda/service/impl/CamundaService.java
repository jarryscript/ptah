package com.example.camunda.service.impl;

import com.example.camunda.model.dto.ProcessStatus;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CamundaService {

    @Autowired
    private ProcessEngine processEngine;

    public ProcessStatus startWorkflow(String processDefinitionKey) {
        String processInstanceId = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey).getProcessInstanceId();
        return ProcessStatus.builder().processInstanceId(processInstanceId).businessKey(processDefinitionKey).build();
    }

    public void sendMessage(String taskId, Map<String, Object> params) {
        processEngine.getTaskService().complete(taskId, params);
    }

    public Map<String, Object> getVariables() {
        String executionId = processEngine.getTaskService().createTaskQuery().processInstanceBusinessKey("").processInstanceId("").singleResult().getExecutionId();

        return processEngine.getRuntimeService().getVariables(executionId);
    }
}
