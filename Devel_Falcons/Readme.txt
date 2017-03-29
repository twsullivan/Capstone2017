See build directory for latest build.

Usage: java -jar DNSQueryTool.jar -i <domainNames> -o <queryResults> -e <dnsEnvironment> -t <queriesPerSecond> -n <"Name"> -q (optional)
    -h    Help - Displays this usage dump.
    -i    Input filename - The file containing the domain names.
    -o    Output filename - The file containing the results of the queries.
    -e    Environment - The DNS environment that will be queried. (GCA:8.8.8.8) The tag (GCA) identifies the environment being queried and is passed to the json output.
    -t    Throttle value - Max queries per second.
    -n    Name - Name of person running queries.
    -q    Optional flag; If in command line then no output is displayed.
