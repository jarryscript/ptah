package com.gihub.jarryzhou.ptah.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("FlowADelegate")
class FlowADelegate : JavaDelegate {
    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        execution.setVariable("Hello", "I am Flow A")
    }
}
