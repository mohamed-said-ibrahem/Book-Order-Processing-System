DROP SCHEMA IF EXISTS OrderProcessingSystem;

CREATE SCHEMA OrderProcessingSystem;

USE OrderProcessingSystem;


CREATE TABLE `BOOK` (
	`ISBN` VARCHAR(100) NOT NULL,
	`Title` VARCHAR(200)  NOT NULL,
	`PID` INT NOT NULL,
	`Year` DATE NULL,
	`Price` DOUBLE NOT NULL,
    `Category` VARCHAR(200),
	`Threshold` INT(15) NOT NULL,
    `Stock` INT(15) NOT NULL DEFAULT '0',
	PRIMARY KEY (`ISBN`)
);

CREATE INDEX book_title ON BOOK(Title);

CREATE INDEX book_category ON BOOK(Category);

CREATE TABLE `USER` (
	`ID` INT(15) NOT NULL AUTO_INCREMENT,
	`Name` VARCHAR(200)  NOT NULL UNIQUE,
	`FName` VARCHAR(200) NOT NULL,
    `LName` VARCHAR(200) NOT NULL,
	`password` VARCHAR(200)  NOT NULL,
	`Email` VARCHAR(200)  NOT NULL UNIQUE,
  	`PhoneNumber` varchar(15) DEFAULT NULL,
	`ShippingAddress` VARCHAR(200) NULL,
 	`IsManager` tinyint(1) DEFAULT '0',
	PRIMARY KEY (`ID`)
);


CREATE TABLE `BOOK_AUTHORS` (
	`ISBN` VARCHAR(100) NOT NULL,
	`AuthorName` VARCHAR(100)  NOT NULL,
	PRIMARY KEY (`ISBN` ,`AuthorName`)
);


CREATE TABLE `BOOK_PUBLISHER` (
	`PID` INT(15) NOT NULL AUTO_INCREMENT,
	`FName` VARCHAR(100)  NOT NULL,
	`LName` VARCHAR(100)  NOT NULL,
	`Address` VARCHAR(100),
	`Phone` VARCHAR(100) NULL,
	`Email` VARCHAR(100) NULL,
	PRIMARY KEY (`PID`)
);



CREATE TABLE `BOOK_ORDERS` (
  	`OrderId` INT(15) NOT NULL,
	`ISBN` VARCHAR(100) NOT NULL,
    `Quantity` INT(15) NOT NULL,
	PRIMARY KEY (`ISBN`)
);



CREATE TABLE `BOOKS_SOLD` (
	`UID` INT(15) NOT NULL,
	`ISBN` VARCHAR(100)  NOT NULL,
	`SellingDate` Date NOT NULL,
	`SellingTime` time NULL,
    `Price` Double NOT NULL,
    `Quantity` INT(15) NOT NULL,
	PRIMARY KEY (`ISBN` , `UID`)
);



ALTER TABLE BOOK ADD FOREIGN KEY (`PID`) REFERENCES `BOOK_PUBLISHER`(`PID`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE BOOK_AUTHORS ADD FOREIGN KEY(`ISBN`) REFERENCES `BOOK`(`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE	BOOK_ORDERS ADD FOREIGN KEY (`ISBN`) REFERENCES `BOOK`(`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE	BOOKS_SOLD ADD FOREIGN KEY (`ISBN`) REFERENCES `BOOK`(`ISBN`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE	BOOKS_SOLD ADD FOREIGN KEY (`UID`) REFERENCES `USER`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE;


