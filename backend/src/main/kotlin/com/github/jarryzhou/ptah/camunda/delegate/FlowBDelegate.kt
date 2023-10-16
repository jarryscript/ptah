package com.github.jarryzhou.ptah.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("FlowBDelegate")
class FlowBDelegate : JavaDelegate {
    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        execution.setVariable("Hello", "I am Flow B")
    }
}
