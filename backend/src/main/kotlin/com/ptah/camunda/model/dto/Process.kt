package com.ptah.camunda.model.dto
class Process(
    var processInstanceId: String? = null,
    var businessKey: String? = null,
    var activeActivities: List<Activity>? = null,
    var variables: Map<String, Any?>? = null
)
