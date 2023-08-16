package com.example.camunda.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Message {
    private String processInstanceId;
    private String messageName;
    private Map<String, Object> variables;
}
