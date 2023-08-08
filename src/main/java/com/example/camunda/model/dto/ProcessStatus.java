package com.example.camunda.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
public class ProcessStatus {
    private String processInstanceId;
    private String businessKey;
    private Map<String,Object> variables;
}
