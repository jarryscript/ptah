package com.github.jarryzhou.ptah.camunda.service

import com.ptah.camunda.model.dto.Activity
import com.ptah.camunda.model.dto.Process
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.RuntimeService
import org.springframework.stereotype.Service

@Service
class CamundaService(
    private var processEngine: ProcessEngine,
    private var runtimeService: RuntimeService
) {
    fun startWorkflow(processDefinitionKey: String?, variables: Map<String, Any?>?): Process {
        val processInstanceId =
            runtimeService.startProcessInstanceByKey(processDefinitionKey, variables).processInstanceId
        return getProcessInstance(processInstanceId)
    }

    fun getProcessInstance(processInstanceId: String?): Process {
        val processInstance =
            runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult()
        if (processInstance != null) {
            val activityInstance = runtimeService.getActivityInstance(processInstanceId)
            val activities = activityInstance.childActivityInstances.map {
                Activity(
                    name = it.activityName,
                    type = it.activityType
                )
            }
            return Process().apply {
                activeActivities = activities
                this.processInstanceId = processInstanceId
                businessKey = processInstance.businessKey
                variables = getVariables(processInstanceId)
            }
        }
        throw RuntimeException()
    }

    fun getVariables(executionId: String?): Map<String, Any> = runtimeService.getVariables(executionId)

    fun sendMessage(messageName: String?, processInstanceId: String?, params: Map<String?, Any?>?): Process {
        runtimeService.createMessageCorrelation(messageName).processInstanceId(processInstanceId).setVariables(params)
            .correlate()
        return getProcessInstance(processInstanceId)
    }
}
