#!/bin/bash

# The project-specific build script
# Author: Austin Bolstridge
# For project: Falcon Development

###############################################################
#                 BEGIN USER EDITABLE OPTIONS                 #
###############################################################
#The location of all of your source files
SOURCE_DIRECTORY="./src"

#For information on the options available, type "javac" into the terminal
#or visit the following: http://docs.oracle.com/javase/7/docs/technotes/tools/windows/javac.html
OPTIONS="-g"

#Which Java API version to compile against
#We recommend using Java 7 for full backwards compatibility - however, if you use Java 8 features
#You need to place "8" in here instead of "7"
#In addition, we do not recommend going below Java 7, as a lot of features start disappearing in Java 6
#JAVA_VERSION="7"

#Which libraries you want to use to compile against.
#Separate each JAR with a semicolon (;)
#For example: 
#	CLASSPATH="./libs/gson.jar;./libs/dns.jar"
CLASSPATH="./src/gson-2.6.2.jar"

#The destination directory for the .class files
DESTINATION_DIRECTORY="./build/classes"

#The destination directory for the JAR files
JAR_DIRECTORY="./build"

JAR_NAME="DNSQueryTool"

#The main class (class with the main function in it)
#Include the FULL class path, with package
MAIN_CLASS="DNSQueryTool.DNSClient"
###############################################################
#                  END USER EDITABLE OPTIONS                  #
###############################################################

mkdir -p ${DESTINATION_DIRECTORY}
mkdir -p ${DESTINATION_DIRECTORY}/libs
mkdir -p ${JAR_DIRECTORY}

javaFiles=($(find ${SOURCE_DIRECTORY} -name "*.java"))
ARRAY_LENGTH=${#javaFiles[@]}

compilationFiles=""

# Loop through each element in the array, essentially
for (( i=0; i<${ARRAY_LENGTH}; i++ ));
do
	JAVA_FILE=${javaFiles[${i}]}
	compilationFiles="$compilationFiles $JAVA_FILE"
done

javac ${OPTIONS} -classpath ${CLASSPATH} ${compilationFiles} -d ${DESTINATION_DIRECTORY}

IFS=';' read -ra LIB_PATHS <<< "$CLASSPATH"
declare -a LIBRARIES
i=0
for LIB_PATH in "${LIB_PATHS[@]}"; 
do
	cp ${LIB_PATH} ${DESTINATION_DIRECTORY}/libs
	IFS='/' read -ra LIB <<< "$LIB_PATH"
	LIBRARIES[${i}]=${LIB[${#LIB[@]}-1]}
done

for LIBRARY in "${LIBRARIES[@]}";
do
	PREVIOUS_DIR=$PWD
	cd ${DESTINATION_DIRECTORY}
	jar xvf libs/${LIBRARY} > /dev/null
	cd "${PREVIOUS_DIR}"
done

touch ${JAR_DIRECTORY}/MANIFEST.MF
echo "Manifest-Version: 1.0" > ${JAR_DIRECTORY}/MANIFEST.MF
echo "Main-Class: ${MAIN_CLASS}" >> ${JAR_DIRECTORY}/MANIFEST.MF

jar cfm ${JAR_DIRECTORY}/${JAR_NAME}.jar ${JAR_DIRECTORY}/MANIFEST.MF -C ${DESTINATION_DIRECTORY} .