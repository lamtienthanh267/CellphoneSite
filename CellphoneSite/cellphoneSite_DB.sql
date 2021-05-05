CREATE SCHEMA `cellphonesite` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `cellphonesite`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`));