import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.yaml.snakeyaml.Yaml

plugins {
    id("org.springframework.boot") version "3.1.3"
    id("io.spring.dependency-management") version "1.1.3"
    id("org.liquibase.gradle") version "2.2.0"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    kotlin("plugin.jpa") version "1.8.22"

}

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.15.2")
        classpath("com.fasterxml.jackson.module:jackson-module-kotlin:2.15.2")
    }
}


group = "com"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("cn.hutool:hutool-all:5.8.21")
    implementation("org.apache.commons:commons-lang3:3.13.0")
    implementation("org.camunda.bpm.springboot:camunda-bpm-spring-boot-starter:7.20.0-alpha5")
    implementation("com.aliyun.oss:aliyun-sdk-oss:3.17.1")
    implementation("org.postgresql:postgresql")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0")

    liquibaseRuntime("org.liquibase:liquibase-core:4.24.0")
    liquibaseRuntime("org.liquibase:liquibase-groovy-dsl:3.0.2")
    liquibaseRuntime("info.picocli:picocli:4.6.1")
    liquibaseRuntime("org.liquibase.ext:liquibase-hibernate5:3.6")
    liquibaseRuntime(sourceSets.main.get().output)
    liquibaseRuntime("org.postgresql:postgresql:42.2.24")

    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(module = "mockito-core")
    }
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:4.0.0")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

val ymlFile = File("src/main/resources/application.yaml")
val prop: Map<String, Any> = Yaml().load(ymlFile.inputStream()) as Map<String, Any>

liquibase {
    activities.register("main") {
        if (ymlFile.exists()) {
            val mapper = ObjectMapper(YAMLFactory())
            val yaml = mapper.readTree(ymlFile)
            val activeProfile = yaml.get("spring").get("profiles").get("active")[0].textValue()
            val activeYamlFile = File("src/main/resources/application-$activeProfile.yaml")
            val activeYaml = mapper.readTree(activeYamlFile)
            val dbUrl = activeYaml.get("spring").get("datasource").get("url").textValue()
            this.arguments = mapOf(
                "logLevel" to "info",
                "changelogFile" to "src/main/resources/db/changelog.xml",
                "excludeObjects" to "table:act_.*",
                "url" to dbUrl,
            )
        }
    }
    runList = "main"
}
