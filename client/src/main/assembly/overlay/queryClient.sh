#!/bin/bash

PATH_TO_CODE_BASE=`pwd`

#JAVA_OPTS="-Djava.rmi.server.codebase=file://$PATH_TO_CODE_BASE/lib/jars/rmi-params-client-2024.2Q.jar"

MAIN_CLASS="ar.edu.itba.ppc.client.QueryClient"

java $JAVA_OPTS -cp 'lib/jars/*'  $MAIN_CLASS $*