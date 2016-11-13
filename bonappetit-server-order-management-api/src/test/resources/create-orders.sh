#!/usr/bin/env bash

curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d @orders.json 'http://localhost:8080/v1/api/orders'