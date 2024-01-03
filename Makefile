.PHONY: all build

all: build

build:
	@./gradlew clean shadowJar

docker:
	@./gradlew clean buildDockerImage
