package com.example.camunda.service;

import com.example.camunda.model.dto.ProcessInformation;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessInstanceWithVariablesImpl;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CamundaService {

    @Autowired
    private ProcessEngine processEngine;

    public ProcessInformation startWorkflow(String processDefinitionKey, Map<String, Object> variables) {
        String processInstanceId = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinitionKey, variables).getProcessInstanceId();
        String executionId = processEngine.getRuntimeService().createExecutionQuery().active().processInstanceId(processInstanceId).singleResult().getId();
        return ProcessInformation.builder().processInstanceId(processInstanceId).businessKey(processDefinitionKey).variables(getVariables(executionId)).build();
    }

    public Map<String, Object> getVariables(String executionId) {
        return processEngine.getRuntimeService().getVariables(executionId);
    }

    public ProcessInformation sendMessage(String messageName, String businessKey, Map<String, Object> params) {
        processEngine.getRuntimeService().correlateMessage(messageName, businessKey, params);
        String processInstanceId = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceBusinessKey(businessKey).singleResult().getProcessInstanceId();
        return ProcessInformation.builder().variables(getVariables(processInstanceId)).build();
    }

    public ProcessInformation getProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance != null) {
            return ProcessInformation.builder().processInstanceId(processInstanceId).businessKey(processInstance.getBusinessKey()).variables(getVariables(processInstanceId)).build();
        }
        throw new RuntimeException();
    }
}
