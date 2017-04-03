#!/bin/bash
#This will be the path to the centralized output files from the Query tools. Currently using our own home made JSONs
PATH_TO_FILES="../../../Devel_Seahawks/IntegrationTestJSON"
#goes back three levels to the main Capstone folder and then goes into our folders.
#Using a temporary directory in our folder for now, which holds some test JSON files.

timestamp=$( date +%m%d%H%M )

#Goes back 3 directorys, which should place it on 201701_Capstone folder and then move into our myBuild created jar file

#This option for output to a csv file
java -jar ../../../Devel_Seahawks/Build/QStats.jar -f ${PATH_TO_FILES} -o ${timestamp}.csv

#This option for output to console, can be copy and pasted into Excel
#java -jar ../../../Devel_Seahawks/Build/QStats.jar -f ${PATH_TO_FILES}
