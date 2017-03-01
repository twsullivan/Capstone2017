#!/bin/bash

# Script to build all Development (and maybe IT) projects
# Author: Austin Bolstridge, Falcon Development Team

#Explaination:
# ls -d */ lists directories only in the current directory (removing any *.sh or *.md files)
# awk allows us to filter the results gained from the ls command
# --posix forces it to use POSIX-compatible mode
# -F " " sets the field separator to a space
# The content inside the single quotes matches the folder name against the regex located between the //
# The regex makes sure that the folder begins with either "IT_" or "Devel_", and if it does, awk will echo it.
# The surrounding () and $() makes sure that the awk output gets transformed into an array that we can use.
folders=($(ls -d */ | awk --posix -F " " '$0 ~ /((IT)|(Devel)){1}\_[0-9a-zA-Z]+/'))
#We need to get the array length in order to do our for loop
ARRAY_LENGTH=${#folders[@]}

# Loop through each element in the array, essentially
for (( i=0; i<${ARRAY_LENGTH}; i++ ));
do
	#Get the array element we'll be working on, for simplicity (and to save me typing)
	TARGET_DIR=${folders[${i}]}
	#Remove any and all spaces from the string
	TARGET_DIR="$(echo -e "${TARGET_DIR}" | tr -d '[:space:]')"
	#Remove the trailing slash
	TARGET_DIR=${TARGET_DIR%/}
	#Change directory to our target directory
	cd ${TARGET_DIR}

	#If the project-specific build script exists, AND it actually has content...
	if [[ -e "build.sh" && -s "build.sh" ]]
	then
		#...Execute the script...
		./build.sh
	else
		#...Otherwise, alert that this team doesn't have an auto-build script defined.
		echo ${TARGET_DIR}" does not have a build script defined. Not building their project."
	fi #End of the if statement
	#Change back to our "root" directory (not actually root, but whatever)
	cd ..
done #End of our for loop