#!/bin/bash

# Integration test for Cowboy and Falcon tools.
# This file goes through all of the file types.
# To lower the amount of domains to search for, go to each folder for the parse types (i.e. AdBlock) and change the DOMAINS_TO_GET value.

echo "\n"
echo "============================================="
echo "= START COWBOY AND FALCONS INTEGRATION TEST ="
echo "=============================================\n"

SECONDS=0

echo "RUNNING ADBLOCK TEST"
echo "--------------------"
cd AdBlock_IntTest/
sh runTest.sh

echo "\n\nRUNNING DNS BLACKHOLE TEST"
echo "----------------------------"
cd ../DNSBlackHole_IntTest/
sh runTest.sh

echo "\n\nRUNNING INTERNET LOG TEST"
echo "---------------------------"
cd ../InternetLog_IntTest/
sh runTest.sh

END_TIME=$SECONDS
echo "\nRun Duration: $((($END_TIME / 60)/60)) hours $(($END_TIME / 60)) minutes $(($END_TIME % 60)) seconds"

echo "==========================================="
echo "= END COWBOY AND FALCONS INTEGRATION TEST ="
echo "===========================================\n"