package com.gihub.jarryzhou.ptah.camunda.model.dto

class Message(
    var processInstanceId: String? = null,
    var messageName: String? = null,
    var variables: Map<String?, Any?>? = null
)
