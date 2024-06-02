#!/bin/bash

echo "Starting mvn clean install and running Spring Boot for test-products..."
cd test-products || exit
mvn clean install
mvn spring-boot:run &
cd ..

echo "Starting mvn clean install and running Spring Boot for test-clients..."
cd test-clients || exit
mvn clean install
mvn spring-boot:run &
cd ..

wait
echo "Both Spring Boot applications are running."
