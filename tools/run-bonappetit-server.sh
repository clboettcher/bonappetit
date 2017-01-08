#! /usr/bin/env bash
# Runs the bonappetit server.

# Comma separated list of spring boot profiles to activate.
PROFILES=$1
VERSION=0.1.0-ALPHA
BASE_NAME=bonappetit-server
JAR_LOCATION=.
BONAPPETIT_BASE_DIR="M:\BONAPPETIT_BASE"
BONAPPETIT_PROPERTIES_LOCATION="${BONAPPETIT_BASE_DIR}\content\config\bonappetit.properties"

java -jar -DBONAPPETIT_BASE=${BONAPPETIT_BASE_DIR} -Dspring.profiles.active=${PROFILES} ${JAR_LOCATION}/${BASE_NAME}-${VERSION}.jar --spring.config.location ${BONAPPETIT_PROPERTIES_LOCATION}

exit 0