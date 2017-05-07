#!/bin/bash

if [ -f jenkinslocation.txt ]; then exit 0; fi

read -p "Docker Host IP Address needed. You do not have a jenkins location set, please enter one: " DOCKERIP

echo "$DOCKERIP" > jenkinslocation.txt