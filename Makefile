DOCKER_COMPOSE_CMD=docker-compose
DIR=$(shell pwd)

up:
	@$(DOCKER_COMPOSE_CMD) up -d

down:
	@$(DOCKER_COMPOSE_CMD) down

clean:
	@./gradlew clean

test:
	@./gradlew test

run:
	@./gradlew run

build:
	@./gradlew build

test-cov:
	@./gradlew jacocoTestReport jacocoTestCoverageVerification
	@echo "Report available in ${DIR}/build/reports/jacoco/test/html/index.html"
