DOCKER_COMPOSE_CMD=docker-compose
DIR=$(shell pwd)

up:
	@$(DOCKER_COMPOSE_CMD) up

down:
	@$(DOCKER_COMPOSE_CMD) down

clean:
	@./gradlew clean

test:
	@./gradlew test

test-cov:
	@./gradlew jacocoTestReport jacocoTestCoverageVerification
	@echo "Report available in ${DIR}/build/reports/jacoco/test/html/index.html"
