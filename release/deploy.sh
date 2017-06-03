#!/usr/bin/env bash

openssl aes-256-cbc -K $encrypted_214e54897333_key -iv $encrypted_214e54897333_iv -in release/codesigning.asc.enc -out release/codesigning.asc -d
gpg --fast-import release/signingkey.asc

mvn versions:set -DnewVersion=$TRAVIS_TAG
mvn deploy -P sign,build-extras --settings release/settings.xml
