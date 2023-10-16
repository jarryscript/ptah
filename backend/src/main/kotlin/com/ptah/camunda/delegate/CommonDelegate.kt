package com.ptah.camunda.delegate

import org.camunda.bpm.engine.delegate.DelegateExecution
import org.camunda.bpm.engine.delegate.JavaDelegate

abstract class CommonDelegate : JavaDelegate {
    @Throws(Exception::class)
    override fun execute(execution: DelegateExecution) {
        val currentActivityName = execution.currentActivityName
        val declaredMethod = javaClass.getDeclaredMethod(currentActivityName, DelegateExecution::class.java)
        declaredMethod.invoke(execution)
    }
}
