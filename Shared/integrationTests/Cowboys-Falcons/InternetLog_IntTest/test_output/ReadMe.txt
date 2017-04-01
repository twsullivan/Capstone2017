==========================================
InternetLog Integration Test Results
==========================================

DOCUMENT INFORMATION:
	Created For: Cowboys and Falcons Integration Test
	Creation Date and Time: 2017/04/01-10:24:27

DOCUMENT DESCRIPTION: 
This file contains the results form the integration test ran for the Cowboys and Falcons. In particular, it tests three things:
	1) The AdBlock functionality between the two tools.
	2) The list ID name produced by the Cowboy's tool was also used in the Falcon's tool.
	3) That all domains from the Cowboy's output was used in the Falcon's tool.

The folder this read me is in has two additional documents: the output for the Cowboy's tool (InternetLog.json) and the output for the Falcon's tool (falcon_test_output.json).

Note: This folder will be deleted when the test is ran again. To save these results, please move the folder/files from this location to another.



OUTPUTS/RESULTS:

***** Cowboys Tool *****

###########################
# Domain File Parser v1.5 #
###########################


File saved successfully to "test_output/InternetLog.json".



***** Falcons Tool *****

[Status] Active: 0, Completed: 0, Total: 100[Status] Active: 50, Completed: 1, Total: 100[Status] Active: 50, Completed: 1, Total: 100[Status] Active: 50, Completed: 1, Total: 100[Status] Active: 50, Completed: 2, Total: 100[Status] Active: 50, Completed: 2, Total: 100[Status] Active: 50, Completed: 2, Total: 100[Status] Active: 50, Completed: 3, Total: 100[Status] Active: 50, Completed: 3, Total: 100[Status] Active: 50, Completed: 5, Total: 100[Status] Active: 49, Completed: 51, Total: 100[Status] Active: 49, Completed: 51, Total: 100[Status] Active: 49, Completed: 51, Total: 100[Status] Active: 48, Completed: 52, Total: 100[Status] Active: 48, Completed: 52, Total: 100[Status] Active: 48, Completed: 52, Total: 100[Status] Active: 46, Completed: 54, Total: 100[Status] Active: 46, Completed: 54, Total: 100[Status] Active: 43, Completed: 57, Total: 100[Status] Active: 0, Completed: 100, Total: 100



***** Testing Outputs *****
ID Name Matching Test:
	PASS: The list ID matches the Falcon and Cowboys files.

Domain Count Test:
	PASS: The JSON files have the same number of domains in the two lists.



TOOL VARIABLES/STATS:
	Domains Parsed: 100
	Throttle Value: 100
	Program Start Date and Time: 2017/04/01-10:24:27
	Tools End Date and Time: 2017/04/01-10:24:49
	Tool Run Duration: 0 hour(s) 0 minute(s) 22 second(s)
