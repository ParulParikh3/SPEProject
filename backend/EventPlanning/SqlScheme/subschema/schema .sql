-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema techevent
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema techevent
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `techevent` DEFAULT CHARACTER SET utf8 ;
USE `techevent` ;

-- -----------------------------------------------------
-- Table `techevent`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `techevent`.`users` (
  `userid` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NULL,
  `phone_no` BIGINT(10) NULL,
  `gender` ENUM('Female', 'Male', 'Others') NULL,
  `email` VARCHAR(50) NULL,
  UNIQUE INDEX `phone_no_UNIQUE` (`phone_no` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  PRIMARY KEY (`userid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `techevent`.`login`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `techevent`.`login` (
  `userid` BIGINT(10) NOT NULL,
  `username` VARCHAR(20) NULL,
  `password` VARCHAR(20) NULL,
  PRIMARY KEY (`userid`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  CONSTRAINT `fk_login_users`
    FOREIGN KEY (`userid`)
    REFERENCES `techevent`.`users` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `techevent`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `techevent`.`event` (
  `eventid` BIGINT(10) NOT NULL AUTO_INCREMENT,
  `organizer_id` BIGINT(10) NOT NULL,
  `eventname` VARCHAR(50) NOT NULL,
  `eventdate` DATE NOT NULL,
  `description` VARCHAR(2000) NULL,
  `participantcount` BIGINT(10) NULL DEFAULT 0,
  `participant_registered` BIGINT(10) NULL DEFAULT 0,
  `eventlocation` VARCHAR(1000) NOT NULL,
  `waiting_count` BIGINT(10) NULL,
  `register_fee` BIGINT(10) NOT NULL DEFAULT 0,
  `phase` ENUM('creation', 'open', 'closed') NOT NULL DEFAULT 'creation',
  `eventtype` VARCHAR(80) NULL,
  `last_date` DATE NULL,
  PRIMARY KEY (`eventid`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `techevent`.`optionaldetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `techevent`.`optionaldetails` (
  `userid` BIGINT(10) NOT NULL,
  `resume` VARCHAR(80) NULL,
  `linkedin` VARCHAR(80) NULL,
  `role` ENUM('speaker', 'sponsor') NULL DEFAULT 'speaker',
  `educational_details` VARCHAR(500) NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `fk_optionaldetails_users1`
    FOREIGN KEY (`userid`)
    REFERENCES `techevent`.`users` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `techevent`.`user_eventlink`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `techevent`.`user_eventlink` (
  `userid` BIGINT(10) NOT NULL,
  `eventid` BIGINT(10) NOT NULL,
  `role` ENUM('speaker', 'sponsor', 'organizer', 'participant') NOT NULL,
  `amountpaid` BIGINT(10) NULL,
  `status` ENUM('waiting', 'applied', 'created', 'approved', 'rejected', 'requested') NOT NULL,
  PRIMARY KEY (`userid`, `eventid`),
  INDEX `fk_user_eventlink_event1_idx` (`eventid` ASC),
  CONSTRAINT `fk_user_eventlink_users1`
    FOREIGN KEY (`userid`)
    REFERENCES `techevent`.`users` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_eventlink_event1`
    FOREIGN KEY (`eventid`)
    REFERENCES `techevent`.`event` (`eventid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `techevent`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `techevent`.`review` (
  `userid` BIGINT(10) NOT NULL,
  `eventid` BIGINT(10) NOT NULL,
  `review` VARCHAR(200) NULL,
  `suggestion` VARCHAR(200) NULL,
  PRIMARY KEY (`userid`, `eventid`),
  INDEX `fk_review_event1_idx` (`eventid` ASC),
  CONSTRAINT `fk_review_users1`
    FOREIGN KEY (`userid`)
    REFERENCES `techevent`.`users` (`userid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_review_event1`
    FOREIGN KEY (`eventid`)
    REFERENCES `techevent`.`event` (`eventid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `techevent`.`waiting_list`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `techevent`.`waiting_list` (
  `waiting_id` INT NOT NULL AUTO_INCREMENT,
  `userid` BIGINT(10) NULL,
  `eventid` BIGINT(10) NULL,
  PRIMARY KEY (`waiting_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `techevent`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `techevent`;
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (1, 'Sowmya', 7795268399, 'Female', 'sowmya@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (2, 'Parul', 1234567890, 'Female', 'parul@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (3, 'Divya', 2345678901, 'Female', 'divya@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (4, 'Yash', 3456789012, 'Male', 'yash@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (5, 'Mayank', 4567890123, 'Male', 'mayank@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (6, 'Deepika', 5678901234, 'Female', 'deepika@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (7, 'Sravya', 6789012345, 'Female', 'sravya@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (8, 'Karthik', 7890123456, 'Male', 'karthik@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (9, 'Pranith', 8901234567, 'Male', 'pranith@gmail.com');
INSERT INTO `techevent`.`users` (`userid`, `name`, `phone_no`, `gender`, `email`) VALUES (10, 'Venky', 9012345678, 'Male', 'venky@gmail.com');

COMMIT;


-- -----------------------------------------------------
-- Data for table `techevent`.`login`
-- -----------------------------------------------------
START TRANSACTION;
USE `techevent`;
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (1, 'Sowmya', 'sowmya');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (2, 'Parul', 'parul');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (3, 'Divya', 'divya');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (4, 'Yash', 'yash');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (5, 'Mayank', 'mayank');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (6, 'Deepika', 'deepika');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (7, 'Sravya', 'sravya');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (8, 'Karthik', 'karthik');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (9, 'Pranith', 'pranith');
INSERT INTO `techevent`.`login` (`userid`, `username`, `password`) VALUES (10, 'Venky', 'venky');

COMMIT;


-- -----------------------------------------------------
-- Data for table `techevent`.`event`
-- -----------------------------------------------------
START TRANSACTION;
USE `techevent`;
INSERT INTO `techevent`.`event` (`eventid`, `organizer_id`, `eventname`, `eventdate`, `description`, `participantcount`, `participant_registered`, `eventlocation`, `waiting_count`, `register_fee`, `phase`, `eventtype`, `last_date`) VALUES (1, 1, 'Robowar', '2019-05-17', 'War between robots', 20, 0, 'Bangalore', 0, 200, 'open', 'single', '2019-05-17');
INSERT INTO `techevent`.`event` (`eventid`, `organizer_id`, `eventname`, `eventdate`, `description`, `participantcount`, `participant_registered`, `eventlocation`, `waiting_count`, `register_fee`, `phase`, `eventtype`, `last_date`) VALUES (2, 4, 'MAS', '2019-05-18', 'Multi agent system', 5, 0, 'Mumbai', 0, 500, 'open', 'multi', '2019-05-18');
INSERT INTO `techevent`.`event` (`eventid`, `organizer_id`, `eventname`, `eventdate`, `description`, `participantcount`, `participant_registered`, `eventlocation`, `waiting_count`, `register_fee`, `phase`, `eventtype`, `last_date`) VALUES (3, 4, 'Debate', '2019-02-13', 'Debate about privacy', 10, 0, 'Chennai', 0, 600, 'closed', 'single', '2019-02-01');
INSERT INTO `techevent`.`event` (`eventid`, `organizer_id`, `eventname`, `eventdate`, `description`, `participantcount`, `participant_registered`, `eventlocation`, `waiting_count`, `register_fee`, `phase`, `eventtype`, `last_date`) VALUES (4, 3, 'Codeathon', '2019-06-05', 'Programming puzzles', 5, 0, 'Hyderabad', 0, 700, 'closed', 'multi', '2019-01-01');
INSERT INTO `techevent`.`event` (`eventid`, `organizer_id`, `eventname`, `eventdate`, `description`, `participantcount`, `participant_registered`, `eventlocation`, `waiting_count`, `register_fee`, `phase`, `eventtype`, `last_date`) VALUES (5, 7, 'Techwarriors', '2019-06-14', 'Technical Quiz', 1, 0, 'Patna', 0, 300, 'creation', 'single', '2019-06-01');
INSERT INTO `techevent`.`event` (`eventid`, `organizer_id`, `eventname`, `eventdate`, `description`, `participantcount`, `participant_registered`, `eventlocation`, `waiting_count`, `register_fee`, `phase`, `eventtype`, `last_date`) VALUES (6, 8, 'AWS events', '2019-06-15', 'Amazon events', 2, 0, 'Agra', 0, 450, 'creation', 'multi', '2019-06-01');

COMMIT;


-- -----------------------------------------------------
-- Data for table `techevent`.`user_eventlink`
-- -----------------------------------------------------
START TRANSACTION;
USE `techevent`;
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (1, 1, 'organizer', 0, 'created');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (4, 2, 'organizer', 0, 'created');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (4, 3, 'organizer', 0, 'created');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (3, 4, 'organizer', 0, 'created');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (7, 5, 'organizer', 0, 'created');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (8, 6, 'organizer', 0, 'created');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (9, 3, 'speaker', 0, 'approved');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (10, 4, 'sponsor', 0, 'approved');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (10, 3, 'speaker', 0, 'approved');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (9, 4, 'sponsor', 0, 'approved');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (9, 1, 'speaker', 0, 'approved');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (10, 1, 'sponsor', 0, 'approved');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (9, 2, 'sponsor', 0, 'approved');
INSERT INTO `techevent`.`user_eventlink` (`userid`, `eventid`, `role`, `amountpaid`, `status`) VALUES (10, 2, 'speaker', 0, 'approved');

COMMIT;

