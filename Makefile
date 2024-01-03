.PHONY: all build

all: build

build:
	@./gradlew clean shadowJar -Pversion=0.0.1-SNAPSHOT

docker:
	@./gradlew clean buildDockerImage -Pversion=$(version)

push: docker
	@docker push ${IMAGE}:$(version)
