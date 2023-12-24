.PHONY: all build

all: build

build:
	@./gradlew clean bootJar

analyzer:
	@java -jar build/libs/spring-bpmn-analyzer-0.0.1-SNAPSHOT.jar