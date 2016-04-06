#!/usr/bin/env bash
set -eu -o pipefail

DOCKER_CERTS_PATH=""
DOCKERMACHINE_NAME="${DOCKERMACHINE_NAME:-default}"

function is_darwin () {
  [ $(uname) == "Darwin" ]
}

function myip () {
  if is_darwin; then
    host "$(hostname)" | awk '{print $4}'
    return 0
  fi

  echo "Error: myip not implemented for $(uname) - please come here and implement it!"
  return 1
}

if is_darwin; then
  DOCKER_CERTS_PATH="$HOME/.docker/machine/certs"
  if [ ! -e "$DOCKER_CERTS_PATH" ]; then
    echo "Error: Looks like you're on OSX, but don't have Dockertoolbox"
    echo "(docker-machine) installed.  I expected to find the certs directory"
    echo "here: $DOCKER_CERTS_PATH"
    exit 1
  fi

  eval "$(docker-machine env $DOCKERMACHINE_NAME)"
fi

if [ ! -d "jekins-master/cert" ]; then
  echo "Copying $DOCKER_CERTS_PATH to jenkins-master/cert"
  cp -R "$DOCKER_CERTS_PATH" jenkins-master/cert
fi

make build
make run
