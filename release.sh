#!/bin/sh
set -e -x

rm -rf dist/
mkdir dist

cd ../gocd-build-bitbucket-pull-requests
mvn clean install -P bitbucket.pr
cp target/gocd-bitbucket-pr-material*.jar dist/
