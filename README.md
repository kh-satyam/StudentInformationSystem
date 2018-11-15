# StudentInformationSystem
FullStackWeb Project

Database Table
CREATE TABLE `student` (
  `Name` varchar(100) NOT NULL,
  `DOB` date NOT NULL,
  `rollNumber` int NOT NULL,
  `physicsMarks` double DEFAULT NULL,
  `chemistryMarks` double DEFAULT NULL,
  `mathematicsMarks` double DEFAULT NULL,
  PRIMARY KEY (`rollNumber`)
);