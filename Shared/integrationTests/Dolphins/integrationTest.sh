#!/bin/bash
#This will be the path to the centralized output files from the Query tools.
PATH_TO_FILES="../../../Devel_Dolphins/jsonTestFiles"
#Using a temporary directory in our folder for now, which holds some test JSON files.

timestamp=$( date +%m%d%H%M )

#Goes up 3 directorys, which should place it on 201701_Capstone
java -jar ../../../Devel_Dolphins/Build/JAnalysis.jar ${PATH_TO_FILES} > ${timestamp}.txt