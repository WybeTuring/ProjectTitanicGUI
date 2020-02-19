# ProjectTitanicGUI

This is Version 2 of the ProjectTitanic project, hosted on [GitHub](https://github.com/WybeTuring/ProjectTitanic.html).
This aim of this project is to design and build a software that enables policy makers and regulators to tract illegal mining (Hereafter referred to as Galamsey) across the world

# Archieving data persistence
Data persistence is achieved through the use of a database. This means that as the user is entering input and  making changes, the database is continously being altered and querried, to provide the user with up-to-date information. 
For anyone hoping to run this application on their local servers, the following requirements must be met;
1. Must have a running MySQL server
2. Must create a database user with the credentialas, user = root, password = 'root'
3. Must run the script PROJECT.sql included in this repository to create the required database to which the connection will be made. 

The software specification is as follows;

- mysql-connector-java-5.1.48
- mysql-connector-java-8.0.18
- javafx 8
- jdk 8

# Running the code
To run the code
