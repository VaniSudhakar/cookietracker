----------------------------------------------
Using the Command Line Sample
----------------------------------------------

The main class is [CookieAnalyzerApp.java](src/main/java/CookieAnalyzerApp.java)

Trial #1 : Opencsv (dependencies) : Custom Header Mapping would take enormous effort with string separated values with
no defined position.

Trail #2: Direct file read, remove first line : Condition: Format has a header row (limitation & can be removed with apt
parsers later)

Tests included.

To be run in the format: -f filename -d yyyy-MM-dd

Exceptions and conditional errors handled at each stage to a good percentage. Can be wastly extended.

A lot of boiler plate code could be avoided with several open source apis available. But, not used more than what was
allowed as per the test.