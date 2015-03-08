Old version.

This is the core part of testing system.
Main methods can be found in the package "sitelogic". Classes wich name ends with ".TaskRunner".
In the working version they will be removed with the class that take information from the web page in JSON format and converts it to a HashMap<Param, String[]>.
HashMap<Param, String[]> have all information about testing process.
Then TestRunner class is invoked.
It can pass HashMap to the different testing classes depending of what tests are needed.
