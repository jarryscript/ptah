package com.ptah.camunda.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
public class Process {
    private String processInstanceId;
    private String businessKey;
    private List<Activity> activeActivities;
    private Map<String,Object> variables;
}
