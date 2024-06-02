#!/bin/sh

echo "Starting mvn clean install in crud-products..."
cd ./test-products
mvn clean install
cd ..

echo "Starting mvn clean install in crud-clients..."
cd ./test-clients
mvn clean install
cd ..

echo "Completed process."
