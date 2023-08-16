package com.example.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.lang.reflect.Method;

public abstract class CommonDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String currentActivityName = execution.getCurrentActivityName();
        String methodName = currentActivityName;
        Method declaredMethod = getClass().getDeclaredMethod(methodName, DelegateExecution.class);
        declaredMethod.invoke(execution);
    }
}
