# OnlineBanking
Online Banking using Spring Boot-Mysql-Angular

The goal for the project is to create a dynamic and responsive Java online banking web application to deposit, withdraw, and transfer the money between the accounts.

This application allows admin to perform following operations:
•	Authorize the roles and guidelines for the user
•	Grant access to the user regarding money transfer, deposits, and withdrawal
•	Block the user account in case of any threat
•	Authorize the cheque book requests

This applications also allows user to perform following operations:
•	Register or log in to the application to maintain a record of activities
•	Deposit and withdraw money from the account
•	View transactions and balance in the primary and savings account
•	Transfer funds between different accounts and add recipients
•	Request cheque books for different accounts



1.	Building jar file with tests:
> mvn package
2.	Building jar file without tests:
> mvn package -DskipTests
3.	Running jar file to start spring boot application:
> java -jar target/icin-0.0.1-SNAPSHOT.jar
4.	Creating MySQL docker image to use:
> docker run --detach --name=onlinebanking --env="MYSQL_ROOT_PASSWORD=root" -p 3306:3306 mysql:8
5.	Creating docker image for Online Banking project:
> docker build -t icinonlinebanking .
6.	Running docker image with MySQL:
> docker run --name iobanking --link onlinebanking:localhost -p 8040:8040 -t icinonlinebanking
