package com.ptah.camunda.service;

import com.ptah.camunda.model.dto.Activity;
import com.ptah.camunda.model.dto.Process;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CamundaService {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RuntimeService runtimeService;

    public Process startWorkflow(String processDefinitionKey, Map<String, Object> variables) {
        String processInstanceId = runtimeService.startProcessInstanceByKey(processDefinitionKey, variables).getProcessInstanceId();
        return getProcessInstance(processInstanceId);
    }

    public Process getProcessInstance(String processInstanceId) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (processInstance != null) {
            ActivityInstance activityInstance = runtimeService.getActivityInstance(processInstanceId);
            List<Activity> activities = Arrays.stream(activityInstance.getChildActivityInstances()).map(childActivityInstance -> new Activity(childActivityInstance.getActivityName(), childActivityInstance.getActivityType())).toList();
            return Process.builder().activeActivities(activities).processInstanceId(processInstanceId).businessKey(processInstance.getBusinessKey()).variables(getVariables(processInstanceId)).build();
        }
        throw new RuntimeException();
    }

    public Map<String, Object> getVariables(String executionId) {
        return runtimeService.getVariables(executionId);
    }

    public Process sendMessage(String messageName, String processInstanceId, Map<String, Object> params) {
        runtimeService.createMessageCorrelation(messageName).processInstanceId(processInstanceId).setVariables(params).correlate();
        return getProcessInstance(processInstanceId);
    }
}
