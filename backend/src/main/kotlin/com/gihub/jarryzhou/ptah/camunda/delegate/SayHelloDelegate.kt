package com.gihub.jarryzhou.ptah.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("SayHelloDelegate")
class SayHelloDelegate : JavaDelegate {
    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        execution.setVariable("output", execution.getVariable("flow"))
    }
}
