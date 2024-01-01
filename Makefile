.PHONY: all build

all: build

build:
	@./gradlew clean shadowJar

analyzer:
	@java -jar build/libs/spring-bpmn-analyzer-0.0.1-SNAPSHOT-all.jar