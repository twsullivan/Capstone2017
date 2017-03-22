#!/bin/bash

#Team Seahawks build file
#QStats

###############################################################
#                 BEGIN USER EDITABLE OPTIONS                 #
###############################################################
#The location of all of your source files

SOURCE_DIRECTORY="https://github.com/UWF-HMCSE-CS/201701_Capstone/tree/master/Devel_Seahawks/src"

#For information on the options available, type "javac" into the terminal
#or visit the following: http://docs.oracle.com/javase/7/docs/technotes/tools/windows/javac.html

OPTIONS="-g"

#Which libraries you want to use to compile against.
#Separate each JAR with a semicolon (;)
#For example: 
#CLASSPATH="./libs/gson.jar;./libs/dns.jar"

CLASSPATH="https://github.com/UWF-HMCSE-CS/201701_Capstone/blob/master/Devel_Seahawks/dist/lib/commons-cli-1.3.1.jar";"https://github.com/UWF-HMCSE-CS/201701_Capstone/blob/master/Devel_Seahawks/dist/lib/gson-2.8.0.jar"

#The destination directory for the .class files

DESTINATION_DIRECTORY="https://github.com/UWF-HMCSE-CS/201701_Capstone/tree/master/Devel_Seahawks/build/classes"

#The destination directory for the JAR files

JAR_DIRECTORY="https://github.com/UWF-HMCSE-CS/201701_Capstone/tree/master/Devel_Seahawks/build"

#What you want your JAR to be called

JAR_NAME="QStats"

#The main class (class with the main function in it)
#Include the FULL class path, with package

MAIN_CLASS="https://github.com/UWF-HMCSE-CS/201701_Capstone/blob/master/Devel_Seahawks/build/classes/Main.class"

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
