==========================================
Cowboys - Falcons Integration Test ReadMe
==========================================


DESCRIPTION

These are the integration tests for the Cowboy and Falcon tools. Each test determines if the parser types are correctly used between the tools. The avalible parser type tests are:
	1) AdBlock
	2) The DNS BlackHole Project
	3) The Internet Log provided by Cody Grogan

Each parser type can be tested individually or as a whole. The HOW TO section under this will explain how to perform these tests.



TESTS EXPLANATION

Each individual parser test determines two things:
	1) The domains produced by the Cowboy’s tool are used in the Falcon’s tool
	2) The list ID produced by the Cowboy’s tool is used in the Falcon’s tool

The tests will output information to a folder called ‘test_output’ which contains three files
	1) Cowboy’s Tool output
	2) Falcon’s Tool output
	3) ReadMe - gives some information about the test, the console output, the test results, and some statistics



FILE SETUP

****** Parser Test Files  ******

The test files for each parser is located in the ‘TestSources’ folder. The names should remain the same unless you change the variables located in each parser type ‘runTest.sh’ script.


******   Team Jar Files   ******

The tests run the jar file produced by the myBuild script. These can also be changed in the variables located in each parser type ‘runTest.sh’ script.


****** File Comparison Jar ******

The test jar for the file comparison is located at Devel_Cowboys/Test Programs/Integration Test/dist folder. It needs to be build with NetBeans with each time it is updated.



HOW TO

******    Running All Tests    ******

To run all parser tests, do the following:
	1) Open Terminal
	2) Direct your terminal to the directory this file is located in using the `cd` command.
	3) Type ‘sh runAll.sh’ into your terminal

The tests now should all run like they are being individually tested. Outputs are put in the ‘test_output’ folder in each parser type folder. They contain the information explained above in the test explanation section.


****** Running Individual Test ******

For this how to, I will be using AdBlock as an example. The structures for the other parser types are similar.

To run an individual test, do the following:
	1) Open Terminal
	2) Direct your terminal to the ‘AdBlock_IntTest’ folder using the `cd` command
	3) Type ‘sh runTest.sh’ into your terminal

The test should have run and produced the output as explained above in test explanation section.


****** Changing Test Variables ******

For this how to, I will be using AdBlock as an example. The structures for the other parser types are similar.

Each parser type test contains variables you can change. These are located under the ‘Tool Editable’ section in the ‘runTest.sh’ file. It will look something like this:

###########################
# TOOL EDITABLE VARIABLES #
###########################

# Cowboy Tool Variables #
PROGRAM_RAN_BY_1="Integration Tester"
DESCRIPTION="This is a sample json file created for the integration test between the Cowboy and Falcon tools. Run Date and Time: `date +%Y/%m/%d-%H:%M:%S`. Parse Type: ${PARSE_TYPE}"
DOMAINS_TO_GET=50

# Falcon Tool Variables #
PROGRAM_RAN_BY_2=PROGRAM_RAN_BY_1
FALCON_OUTPUT_FILE_NAME="falcon_test_output.json"
ENVIRONMENT="GCA:8.8.8.8"
THROTTLE_VALUE=100

As you can see, the variables for each tool are separated by tool.
