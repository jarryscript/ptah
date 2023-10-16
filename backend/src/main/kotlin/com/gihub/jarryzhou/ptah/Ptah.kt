package com.gihub.jarryzhou.ptah

import org.camunda.bpm.engine.repository.ProcessDefinition
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableProcessApplication
@EnableJpaRepositories
class Ptah

private val logger = LoggerFactory.getLogger(Ptah::class.java)

@EventListener
fun notify(event: PostDeployEvent) {
    val definitions = event.processEngine.repositoryService.createProcessDefinitionQuery().list()
    logger.info("========================================================================")
    logger.info("Found deployed process:")
    definitions.stream().forEach { definition: ProcessDefinition -> logger.info(definition.toString()) }
    logger.info("========================================================================")
}

fun main(args: Array<String>) {
    runApplication<Ptah>(*args)
}
