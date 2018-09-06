#!/bin/bash

mvn exec:java -Dexec.mainClass="com.acme.multimodule.Main" -Dexec.args="'$1'" -pl app -q 2>/dev/null
