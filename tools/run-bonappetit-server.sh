#! /usr/bin/env bash
# Runs the bonappetit server.

# Comma separated list of spring boot profiles to activate.
PROFILES=$1
VERSION=0.0.1-SNAPSHOT
BASE_NAME=bonappetit-server
JAR_LOCATION=.
BONAPPETIT_BASE_DIR="M:\BONAPPETIT_BASE"


java -jar -DBONAPPETIT_BASE=${BONAPPETIT_BASE_DIR} -Dspring.profiles.active=${PROFILES} ${JAR_LOCATION}/${BASE_NAME}-${VERSION}.jar

exit 0