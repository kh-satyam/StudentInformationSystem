# StudentInformationSystem
FullStackWeb Project using REST api implementation details  

Front end (HTML + CSS + BootStrap + JavaScript + jQuery)  
Back end (Java + Jersy + JDBC + MySQL)  
Database Schema  

`create database studentproject;`  
`use studentproject;`  
  
<code>
CREATE TABLE `student` (  
  `Name` varchar(100) NOT NULL,  
  `DOB` date NOT NULL,  
  `rollNumber` int NOT NULL,  
  `physicsMarks` double DEFAULT NULL,  
  `chemistryMarks` double DEFAULT NULL,  
  `mathematicsMarks` double DEFAULT NULL,  
  PRIMARY KEY (`rollNumber`),  
  CHECK ( `rollNumber` > 0 )  
);
</code>













COMMENT TABLE



<code>
create table comment(id int not null AUTO_INCREMENT, rollno int not null,comment varchar(255),primary key(id),foreign key(rollno)references student(rollNumber));
</code>
