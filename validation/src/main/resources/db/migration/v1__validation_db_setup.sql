DROP DATABASE IF EXISTS validations;

DROP USER IF EXISTS 'validations_user'@'%';

-- Creates databases
CREATE DATABASE validations;


-- Creates user & grants permission
CREATE USER 'validations_user'@'%' IDENTIFIED BY 'P9v@tX3#nLz!Q8wK';


-- GRANT START Either this 
-- GRANT ALL ON validations.* TO 'validations_user'@'%' ;

-- or this

GRANT SELECT, INSERT, UPDATE, DELETE ON validations.* TO 'validations_user'@'%';
-- GRANT END Either this.

-- Create Tables validations Schema Start --
CREATE TABLE validations.`merchant_payment_request` (
 `id` int NOT NULL AUTO_INCREMENT,
 `endUserID` varchar(100),
 `merchantTxnReference` varchar(100) NOT NULL,
 `transactionRequest` text DEFAULT NULL,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`),
UNIQUE KEY (`merchantTxnReference`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE validations.`validation_rules` (
 `id` int NOT NULL AUTO_INCREMENT,
 `validatorName` varchar(50) NOT NULL,
 `isActive` BOOLEAN NOT NULL,
 `priority` SMALLINT NOT NULL,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`),
 UNIQUE KEY (`validatorName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE validations.`validation_rules_params` (
 `id` int NOT NULL AUTO_INCREMENT,
 `validatorName` varchar(50) NOT NULL,
 `paramName` varchar(200) NOT NULL,
 `paramValue` varchar(200) NOT NULL,
 `creationDate` timestamp(2) NOT NULL DEFAULT CURRENT_TIMESTAMP(2),
 PRIMARY KEY (`id`),
 FOREIGN KEY (`validatorName`) REFERENCES `validation_rules` (`validatorName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- Create Tables validations Schema End***


