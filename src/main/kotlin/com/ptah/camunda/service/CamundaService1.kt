package com.ptah.camunda.service

import com.ptah.camunda.model.dto.Activity
import com.ptah.camunda.model.dto.Process
import org.camunda.bpm.engine.ProcessEngine
import org.camunda.bpm.engine.RuntimeService
import org.camunda.bpm.engine.runtime.ActivityInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CamundaService {
    @Autowired
    private val processEngine: ProcessEngine? = null

    @Autowired
    private val runtimeService: RuntimeService? = null
    fun startWorkflow(processDefinitionKey: String?, variables: Map<String?, Any?>?): Process {
        val processInstanceId =
            runtimeService!!.startProcessInstanceByKey(processDefinitionKey, variables).processInstanceId
        return getProcessInstance(processInstanceId)
    }

    fun getProcessInstance(processInstanceId: String?): Process {
        val processInstance =
            runtimeService!!.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult()
        if (processInstance != null) {
            val activityInstance = runtimeService.getActivityInstance(processInstanceId)
            val activities = Arrays.stream(activityInstance.childActivityInstances)
                .map { childActivityInstance: ActivityInstance ->
                    Activity(
                        childActivityInstance.activityName,
                        childActivityInstance.activityType
                    )
                }
                .toList()
            return Process.builder().activeActivities(activities).processInstanceId(processInstanceId)
                .businessKey(processInstance.businessKey).variables(getVariables(processInstanceId)).build()
        }
        throw RuntimeException()
    }

    fun getVariables(executionId: String?): Map<String, Any> {
        return runtimeService!!.getVariables(executionId)
    }

    fun sendMessage(messageName: String?, processInstanceId: String?, params: Map<String?, Any?>?): Process {
        runtimeService!!.createMessageCorrelation(messageName).processInstanceId(processInstanceId).setVariables(params)
            .correlate()
        return getProcessInstance(processInstanceId)
    }
}
