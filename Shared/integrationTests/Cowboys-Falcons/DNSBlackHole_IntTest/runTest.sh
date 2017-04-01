#!/bin/bash

# Integration test for Cowboy and Falcon tools.
# DNSBlackHole Test

#####################
# PROGRAM VARIABLES #
#####################
PARSE_TYPE="DNSBlackHole"

# Jar File Locations
COWBOYS_JAR='../../../../Devel_Cowboys/build/DomainFileParser.jar'
FALCONS_JAR='../../../../Devel_Falcons/build/DNSQueryTool.jar'

# Test Source File Locations
ADBLOCK_TS="../TestSources/${PARSE_TYPE}.txt"

# Test Program Location
TEST_PROGRAM='../../../../Devel_Cowboys/Test Programs/IntegrationTest/'

# Program Variables
OUTPUT_FOLDER_NAME='test_output'


###########################
# TOOL EDITABLE VARIABLES #
###########################

# Cowboy Tool Variables #
PROGRAM_RAN_BY_1="Integration Tester"
DESCRIPTION="This is a sample json file created for the integration test between the Cowboy and Falcon tools. Run Date and Time: `date +%Y/%m/%d-%H:%M:%S`. Parse Type: ${PARSE_TYPE}"
DOMAINS_TO_GET=0

# Falcon Tool Variables #
PROGRAM_RAN_BY_2=PROGRAM_RAN_BY_1
FALCON_OUTPUT_FILE_NAME="falcon_test_output.json"
ENVIRONMENT="GCA:8.8.8.8"
THROTTLE_VALUE=100


#############
# TEST CODE #
#############
# Sets up file for testing output
rm -rf ${OUTPUT_FOLDER_NAME}
mkdir ${OUTPUT_FOLDER_NAME}

# Sets up the results file.
RESULT_OUTPUT_FILE="${OUTPUT_FOLDER_NAME}/ReadMe.txt"
touch $RESULT_OUTPUT_FILE
echo "==========================================" > $RESULT_OUTPUT_FILE
echo "${PARSE_TYPE} Integration Test Results" >> $RESULT_OUTPUT_FILE
echo "==========================================" >> $RESULT_OUTPUT_FILE
echo "\nDOCUMENT INFORMATION:" >> $RESULT_OUTPUT_FILE
echo "\tCreated For: Cowboys and Falcons Integration Test" >> $RESULT_OUTPUT_FILE
echo "\tCreation Date and Time: `date +%Y/%m/%d-%H:%M:%S`" >> $RESULT_OUTPUT_FILE
echo "\nDOCUMENT DESCRIPTION: " >> $RESULT_OUTPUT_FILE
echo "This file contains the results form the integration test ran for the Cowboys and Falcons. In particular, it tests three things:" >> $RESULT_OUTPUT_FILE
echo "\t1) The AdBlock functionality between the two tools." >> $RESULT_OUTPUT_FILE
echo "\t2) The list ID name produced by the Cowboy's tool was also used in the Falcon's tool." >> $RESULT_OUTPUT_FILE
echo "\t3) That all domains from the Cowboy's output was used in the Falcon's tool." >> $RESULT_OUTPUT_FILE
echo "\nThe folder this read me is in has two additional documents: the output for the Cowboy's tool (${PARSE_TYPE}.json) and the output for the Falcon's tool (${FALCON_OUTPUT_FILE_NAME})." >> $RESULT_OUTPUT_FILE
echo "\nNote: This folder will be deleted when the test is ran again. To save these results, please move the folder/files from this location to another." >> $RESULT_OUTPUT_FILE
echo "\n\n\nOUTPUTS/RESULTS:" >> $RESULT_OUTPUT_FILE

# Runs the Cowboys tool, outputs to the screen and to the readme file.
SECONDS=0
START_DATE=`date +%Y/%m/%d-%H:%M:%S`
echo "\n***** Cowboys Tool *****" | tee -a $RESULT_OUTPUT_FILE
java -jar ${COWBOYS_JAR} "${ADBLOCK_TS}" "${OUTPUT_FOLDER_NAME}/" "${PROGRAM_RAN_BY}" "${DESCRIPTION}" "${PARSE_TYPE}" "${DOMAINS_TO_GET}" | tee -a $RESULT_OUTPUT_FILE

# Runs the Falcons tool, outputs to the screen and to the readme file.
echo "\n***** Falcons Tool *****" | tee -a $RESULT_OUTPUT_FILE
java -jar ${FALCONS_JAR} -i "${OUTPUT_FOLDER_NAME}/${PARSE_TYPE}.json" -o "${OUTPUT_FOLDER_NAME}/${FALCON_OUTPUT_FILE_NAME}" -e "${ENVIRONMENT}" -t "${THROTTLE_VALUE}" -n "${PROGRAM_RAN_BY}" | tee -a $RESULT_OUTPUT_FILE

# Runs the test file located in the Cowboys/Test Programs folder
echo "\n\n\n***** Testing Outputs *****" | tee -a $RESULT_OUTPUT_FILE
java -jar "${TEST_PROGRAM}dist/IntegrationTest.jar" "${OUTPUT_FOLDER_NAME}/${PARSE_TYPE}.json" "${OUTPUT_FOLDER_NAME}/${FALCON_OUTPUT_FILE_NAME}" | tee -a "test_output/ReadMe.txt"
STOP_TIME=$SECONDS
STOP_DATE=`date +%Y/%m/%d-%H:%M:%S`

echo "\n\n\nTOOL VARIABLES/STATS:" >> $RESULT_OUTPUT_FILE
echo "\tDomains Parsed: ${DOMAINS_TO_GET}" >> $RESULT_OUTPUT_FILE
echo "\tThrottle Value: ${THROTTLE_VALUE}" >> $RESULT_OUTPUT_FILE
echo "\tProgram Start Date and Time: ${START_DATE}" >> $RESULT_OUTPUT_FILE
echo "\tTools End Date and Time: ${STOP_DATE}" >> $RESULT_OUTPUT_FILE
echo "\tTool Run Duration: $(($STOP_TIME / 3600)) hour(s) $((($STOP_TIME / 60) % 60)) minute(s) $(($STOP_TIME % 60)) second(s)" >> $RESULT_OUTPUT_FILE

echo "\n\n\n***** End of ${PARSE_TYPE} Test *****"