# EmployeesFilesResolver
## Table of contents
* [General info](#general-info)
* [Setup](#setup)
* [Run](#run)

## General info
This project is a Java console application. It parse given files list of employees and calculate average salary for each position.  
Supported file type: JSON, CSV  
Supported file format, as in sampleFiles folder.

## Setup
To run test and compile program use maven: 
1. Run `mvn package` - it will build ready to use jar file.

## Run
Program takes multiple files path as arguments:  
`java -jar target/EmployeeResolver-1.jar [file path] [file path] [file path] ...`  
where:  
`[file path]` – path to Csv or Json file containing data to analysis.  

example:  
`java -jar target/EmployeeResolver-1.jar  sampleFiles/employees.csv`

Program gives result in following format:  
 ```
 [file name]:
 possition1 - average salary
 possition2 - average salary
 ...
 ```
