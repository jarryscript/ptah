package com.github.jarryzhou.ptah.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component("SayGoodbyeDelegate")
class SayGoodbyeDelegate : JavaDelegate {
    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        execution.setVariable("Hello", "bye bye")
    }
}
