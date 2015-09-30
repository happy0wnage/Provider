SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

ALTER TABLE `provider`.`Contract` 
DROP FOREIGN KEY `fk_Contract_Administrator1`;

ALTER TABLE `provider`.`Tariff` 
DROP FOREIGN KEY `fk_Tariff_Service1`;

ALTER TABLE `provider`.`Service` 
DROP FOREIGN KEY `fk_Service_Contract1`;

ALTER TABLE `provider`.`Contract` 
DROP COLUMN `idAdministrator`,
DROP INDEX `fk_Contract_Administrator1_idx` ;

ALTER TABLE `provider`.`Tariff` 
DROP COLUMN `Speed_limit`,
DROP COLUMN `City`,
DROP COLUMN `idService`,
CHANGE COLUMN `Fee` `Price` DOUBLE NOT NULL ,
ADD COLUMN `idService` INT(11) NOT NULL AFTER `idTariff`,
ADD COLUMN `Description` TEXT NOT NULL AFTER `Price`,
ADD INDEX `fk_Tariff_Service1_idx` (`idService` ASC),
DROP INDEX `fk_Tariff_Service1_idx` ;

CREATE TABLE IF NOT EXISTS `provider`.`Payment` (
  `idPayment` INT(11) NOT NULL AUTO_INCREMENT,
  `idAbonent` INT(11) NOT NULL,
  `Balance` DOUBLE NOT NULL DEFAULT 0,
  `Status` TINYINT(1) NOT NULL DEFAULT 1,
  `End_date_service` DATE NOT NULL,
  PRIMARY KEY (`idPayment`),
  INDEX `fk_Payment_Abonent1_idx` (`idAbonent` ASC),
  CONSTRAINT `fk_Payment_Abonent1`
    FOREIGN KEY (`idAbonent`)
    REFERENCES `provider`.`Abonent` (`idAbonent`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

ALTER TABLE `provider`.`Service` 
DROP COLUMN `Balance`,
DROP COLUMN `End_date`,
DROP COLUMN `Start_date`,
DROP COLUMN `idContract`,
CHANGE COLUMN `idService` `idService` INT(11) NOT NULL ,
ADD COLUMN `idContract` INT(11) NOT NULL AFTER `idService`,
ADD INDEX `fk_Service_Contract1_idx` (`idContract` ASC),
DROP INDEX `fk_Service_Contract1_idx` ;

ALTER TABLE `provider`.`Tariff` 
ADD CONSTRAINT `fk_Tariff_Service1`
  FOREIGN KEY (`idService`)
  REFERENCES `provider`.`Service` (`idService`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `provider`.`Service` 
ADD CONSTRAINT `fk_Service_Contract1`
  FOREIGN KEY (`idContract`)
  REFERENCES `provider`.`Contract` (`idContract`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
