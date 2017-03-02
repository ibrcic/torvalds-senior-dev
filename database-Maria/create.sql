-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema InventoryItemDb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `InventoryItemDb` ;

-- -----------------------------------------------------
-- Schema InventoryItemDb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `InventoryItemDb` DEFAULT CHARACTER SET utf8 ;
USE `InventoryItemDb` ;

-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Major`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Major` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Major` (
  `majorID` INT NOT NULL,
  `majorTitle` VARCHAR(45) NULL,
  `majorAbbreviation` VARCHAR(5) NULL,
  PRIMARY KEY (`majorID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Borrower`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Borrower` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Borrower` (
  `borrowerId` INT NOT NULL,
  `username` VARCHAR(20) NULL,
  `email` VARCHAR(20) NULL,
  `flagged` TINYINT NULL,
  PRIMARY KEY (`borrowerId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Class` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Class` (
  `classId` INT NOT NULL,
  `classTitle` VARCHAR(6) NULL,
  `className` VARCHAR(55) NULL,
  PRIMARY KEY (`classId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Rental`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Rental` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Rental` (
  `rentalId` INT NOT NULL,
  `signature` VARCHAR(45) NULL,
  `startDate` DATE NULL,
  `endDate` DATE NULL,
  `Borrower_borrowerId` INT NOT NULL,
  PRIMARY KEY (`rentalId`, `Borrower_borrowerId`),
  INDEX `fk_Rental_Student1_idx` (`Borrower_borrowerId` ASC),
  CONSTRAINT `fk_Rental_Student1`
    FOREIGN KEY (`Borrower_borrowerId`)
    REFERENCES `InventoryItemDb`.`Borrower` (`borrowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`ItemType`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`ItemType` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`ItemType` (
  `itemTypeId` INT NOT NULL,
  `itemTypeName` VARCHAR(45) NULL,
  `image` BLOB NULL,
  `manufacturer` VARCHAR(45) NULL,
  `model` VARCHAR(45) NULL,
  PRIMARY KEY (`itemTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Item` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Item` (
  `idItem` INT NOT NULL,
  `barcode` BLOB NULL,
  `serialNumber` VARCHAR(45) NULL,
  `typeId` INT NULL,
  `department` VARCHAR(14) NULL,
  `aquireDate` DATE NULL,
  `yellowTag` INT(8) NULL,
  `procurementOrder` VARCHAR(45) NULL,
  `cost` DECIMAL(12,2) NULL,
  `assetTag` VARCHAR(45) NULL,
  `ItemType_itemTypeId` INT NOT NULL,
  PRIMARY KEY (`idItem`),
  INDEX `fk_Item_ItemType1_idx` (`ItemType_itemTypeId` ASC),
  CONSTRAINT `fk_Item_ItemType1`
    FOREIGN KEY (`ItemType_itemTypeId`)
    REFERENCES `InventoryItemDb`.`ItemType` (`itemTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Warranty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Warranty` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Warranty` (
  `warrentyId` INT NOT NULL,
  `warrentyName` VARCHAR(45) NULL,
  `warantyCompany` VARCHAR(45) NULL,
  `endDate` DATE NULL,
  `warrantyDescription` TEXT NULL,
  PRIMARY KEY (`warrentyId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Item_has_Warranty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Item_has_Warranty` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Item_has_Warranty` (
  `Item_idItem` INT NOT NULL,
  `Warranty_warrentyId` INT NOT NULL,
  PRIMARY KEY (`Item_idItem`, `Warranty_warrentyId`),
  INDEX `fk_Item_has_Warranty_Warranty1_idx` (`Warranty_warrentyId` ASC),
  INDEX `fk_Item_has_Warranty_Item1_idx` (`Item_idItem` ASC),
  CONSTRAINT `fk_Item_has_Warranty_Item1`
    FOREIGN KEY (`Item_idItem`)
    REFERENCES `InventoryItemDb`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_Warranty_Warranty1`
    FOREIGN KEY (`Warranty_warrentyId`)
    REFERENCES `InventoryItemDb`.`Warranty` (`warrentyId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Damage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Damage` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Damage` (
  `damageId` INT NOT NULL,
  `damageName` VARCHAR(45) NULL,
  `damageDescription` TEXT NULL,
  `Severity` INT(3) NULL,
  PRIMARY KEY (`damageId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Item_has_Damage`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Item_has_Damage` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Item_has_Damage` (
  `Item_idItem` INT NOT NULL,
  `Damage_damageId` INT NOT NULL,
  PRIMARY KEY (`Item_idItem`, `Damage_damageId`),
  INDEX `fk_Item_has_Damage_Damage1_idx` (`Damage_damageId` ASC),
  INDEX `fk_Item_has_Damage_Item1_idx` (`Item_idItem` ASC),
  CONSTRAINT `fk_Item_has_Damage_Item1`
    FOREIGN KEY (`Item_idItem`)
    REFERENCES `InventoryItemDb`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_has_Damage_Damage1`
    FOREIGN KEY (`Damage_damageId`)
    REFERENCES `InventoryItemDb`.`Damage` (`damageId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Rental_has_Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Rental_has_Item` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Rental_has_Item` (
  `Rental_rentalId` INT NOT NULL,
  `Rental_Student_studentId` INT NOT NULL,
  `Item_idItem` INT NOT NULL,
  PRIMARY KEY (`Rental_rentalId`, `Rental_Student_studentId`, `Item_idItem`),
  INDEX `fk_Rental_has_Item_Item1_idx` (`Item_idItem` ASC),
  INDEX `fk_Rental_has_Item_Rental1_idx` (`Rental_rentalId` ASC, `Rental_Student_studentId` ASC),
  CONSTRAINT `fk_Rental_has_Item_Rental1`
    FOREIGN KEY (`Rental_rentalId` , `Rental_Student_studentId`)
    REFERENCES `InventoryItemDb`.`Rental` (`rentalId` , `Borrower_borrowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rental_has_Item_Item1`
    FOREIGN KEY (`Item_idItem`)
    REFERENCES `InventoryItemDb`.`Item` (`idItem`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Section`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Section` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Section` (
  `Class_classId` INT NOT NULL,
  `section` INT(1) NULL,
  PRIMARY KEY (`Class_classId`),
  CONSTRAINT `fk_table2_Class1`
    FOREIGN KEY (`Class_classId`)
    REFERENCES `InventoryItemDb`.`Class` (`classId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Borrower_has_Major`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Borrower_has_Major` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Borrower_has_Major` (
  `Borrower_borrowerId` INT NOT NULL,
  `Major_majorID` INT NOT NULL,
  PRIMARY KEY (`Borrower_borrowerId`, `Major_majorID`),
  INDEX `fk_Borrower_has_Major1_Major1_idx` (`Major_majorID` ASC),
  INDEX `fk_Borrower_has_Major1_Borrower1_idx` (`Borrower_borrowerId` ASC),
  CONSTRAINT `fk_Borrower_has_Major1_Borrower1`
    FOREIGN KEY (`Borrower_borrowerId`)
    REFERENCES `InventoryItemDb`.`Borrower` (`borrowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Borrower_has_Major1_Major1`
    FOREIGN KEY (`Major_majorID`)
    REFERENCES `InventoryItemDb`.`Major` (`majorID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Borrower_has_Class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Borrower_has_Class` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Borrower_has_Class` (
  `Borrower_borrowerId` INT NOT NULL,
  `Class_classId` INT NOT NULL,
  PRIMARY KEY (`Borrower_borrowerId`, `Class_classId`),
  INDEX `fk_Borrower_has_Class1_Class1_idx` (`Class_classId` ASC),
  INDEX `fk_Borrower_has_Class1_Borrower1_idx` (`Borrower_borrowerId` ASC),
  CONSTRAINT `fk_Borrower_has_Class1_Borrower1`
    FOREIGN KEY (`Borrower_borrowerId`)
    REFERENCES `InventoryItemDb`.`Borrower` (`borrowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Borrower_has_Class1_Class1`
    FOREIGN KEY (`Class_classId`)
    REFERENCES `InventoryItemDb`.`Class` (`classId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `InventoryItemDb`.`Reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `InventoryItemDb`.`Reservation` ;

CREATE TABLE IF NOT EXISTS `InventoryItemDb`.`Reservation` (
  `reservationId` INT NOT NULL,
  `Borrower_borrowerId` INT NOT NULL,
  `Rental_rentalId` INT NOT NULL,
  `ItemType_itemTypeId` INT NOT NULL,
  PRIMARY KEY (`reservationId`, `Borrower_borrowerId`),
  INDEX `fk_Reservervation_Borrower1_idx` (`Borrower_borrowerId` ASC),
  INDEX `fk_Reservervation_Rental1_idx` (`Rental_rentalId` ASC),
  INDEX `fk_Reservervation_ItemType1_idx` (`ItemType_itemTypeId` ASC),
  CONSTRAINT `fk_Reservervation_Borrower1`
    FOREIGN KEY (`Borrower_borrowerId`)
    REFERENCES `InventoryItemDb`.`Borrower` (`borrowerId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservervation_Rental1`
    FOREIGN KEY (`Rental_rentalId`)
    REFERENCES `InventoryItemDb`.`Rental` (`rentalId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Reservervation_ItemType1`
    FOREIGN KEY (`ItemType_itemTypeId`)
    REFERENCES `InventoryItemDb`.`ItemType` (`itemTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `InventoryItemDb` ;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
