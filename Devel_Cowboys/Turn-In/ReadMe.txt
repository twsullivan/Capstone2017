################################
# Domain File Parser - Read Me #
################################

Team Members:
	- Thomas Kreuser
	- Zack McColgan
	- Philip Gabriel
	- Nick Nawoschik

Delivery Date: April 11, 2017

How to Run Tool:
	Open up terminal/command prompt and input the following command:

		java -jar DomainFileParser.jar <input_file> <output_file_path> <name> <description> <file_type> <max_results>
        
        Parameter Explinations:
	        - Input File (required): Absolute path of the input file, including filename and extension, from which to extract domains.
	        - Output File Path (required): Absolute folder path to save the output file containing domains in JSON format. The file name will be generated automatically so it does not need to be included. Output file name example: AdBlock.json
	        - Name (required): The name of the user who performed the web scrape.
	        - Description (required): Information about the domain list and its origin.
	        - File Type (required): The type of log format that is being processed (AdBlock, DNSBlackHole, InternetLog).
	        - Max Results (required): Maximum number of results to output. If set to 0, the result set is unlimited.
        
        Examples:
	        Example (Windows): java -jar DomainFileParser.jar "C:\Users\bob\Desktop\\domains.txt" C:\Users\bob\Desktop\" "Bob Smith" "Domains from AdBlock on 02Feb17" AdBlock 100
	        
	        Example (Mac): java -jar DomainFileParser.jar "/Users/bob/Desktop/domains.txt" "/Users/bob/Desktop" "Bob Smith" "Domains from AdBlock on 02Feb17" AdBlock 100