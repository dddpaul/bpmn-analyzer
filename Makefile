.PHONY: all build docker push

all: build

build:
	@./gradlew clean shadowJar -Pversion=0.0.1-SNAPSHOT

docker:
	@./gradlew clean buildDockerImage -Pversion=0.0.1-SNAPSHOT

# Used from github actions, do not modify
push:
	@./gradlew pushDockerImage -Pversion=$(version)
