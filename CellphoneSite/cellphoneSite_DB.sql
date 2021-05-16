 -- MySQL Workbench Synchronization
-- Generated: 2021-05-13 23:25
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: lamti

CREATE SCHEMA `cellphonesite` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `cellphonesite`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL,
  PRIMARY KEY (`id`));
  
  alter table `users` add column email varchar(45) not null;
  
  CREATE TABLE `cellphonesite`.`role_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL COMMENT 'phan_quyen_user',
  PRIMARY KEY (`id`));

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE TABLE IF NOT EXISTS `cellphonesite`.`product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL COMMENT 'tên sản phẩm',
  `code` VARCHAR(100) NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL COMMENT 'mô tả',
  `descrip_details` TEXT NULL DEFAULT NULL COMMENT 'mô tả chi tiết',
  `best_sale` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'bán chạy',
  `featured` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'nổi bật',
  `newest` TINYINT(4) NOT NULL DEFAULT 0 COMMENT 'mới về',
  `price` DOUBLE NOT NULL,
  `price_competitive` DOUBLE NOT NULL COMMENT 'giá cạnh tranh',
  `image` VARCHAR(100) NOT NULL DEFAULT 'no-image.jpg',
  `date_create` DATETIME NOT NULL,
  `date_edit` DATETIME NULL DEFAULT NULL,
  `total_amount` INT(11) NOT NULL DEFAULT 0,
  `brand_id` INT(11) NOT NULL,
  `product_type_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_brand1_idx` (`brand_id` ASC) VISIBLE,
  INDEX `fk_product_product_type1_idx` (`product_type_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_brand1`
    FOREIGN KEY (`brand_id`)
    REFERENCES `cellphonesite`.`brand` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_product_type1`
    FOREIGN KEY (`product_type_id`)
    REFERENCES `cellphonesite`.`product_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`product_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'tên loại sản phẩm',
  `code` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`brand` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'tên thương hiệu',
  `code` VARCHAR(45) NULL DEFAULT NULL,
  `logo` VARCHAR(45) NOT NULL DEFAULT 'no-image.jpg',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`tags` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL COMMENT 'từ khóa',
  `code` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`tag_product` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) NOT NULL,
  `tags_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tag_product_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_tag_product_tags1_idx` (`tags_id` ASC) VISIBLE,
  CONSTRAINT `fk_tag_product_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `cellphonesite`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tag_product_tags1`
    FOREIGN KEY (`tags_id`)
    REFERENCES `cellphonesite`.`tags` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`product_image` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file` VARCHAR(45) NOT NULL DEFAULT 'no-image.jpg',
  `product_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_product_image_product1_idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `fk_product_image_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `cellphonesite`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`slider` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `caption` VARCHAR(100) NOT NULL COMMENT 'nội dung slider',
  `description` VARCHAR(100) NULL DEFAULT NULL COMMENT 'mô tả',
  `link` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`image_slider` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `file` VARCHAR(45) NULL DEFAULT NULL,
  `slider_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_image_slider_slider1_idx` (`slider_id` ASC) VISIBLE,
  CONSTRAINT `fk_image_slider_slider1`
    FOREIGN KEY (`slider_id`)
    REFERENCES `cellphonesite`.`slider` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`invoice` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date_create` DATETIME NOT NULL,
  `total` DOUBLE NOT NULL,
  `name` VARCHAR(100) NOT NULL COMMENT 'tên khách hàng',
  `phone_number` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `address_delivery` VARCHAR(100) NOT NULL,
  `note` VARCHAR(100) NULL DEFAULT NULL,
  `ship` DOUBLE NOT NULL DEFAULT 0,
  `vat` DOUBLE NULL DEFAULT NULL,
  `payment` DOUBLE NOT NULL DEFAULT 0,
  `discount` DOUBLE NULL DEFAULT NULL,
  `discount_type` ENUM('Phần trăm', 'Tiền mặt') NULL DEFAULT NULL,
  `payment_method` ENUM('Tiền mặt', 'Chuyển khoản') NOT NULL DEFAULT 'Tiền mặt',
  `status` ENUM('Đang chờ xử lý', 'Đang xử lý', 'Đang giao', 'Hủy') NOT NULL DEFAULT 'Đang chờ xử lý',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `cellphonesite`.`invoice_details` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `amount` INT(11) NULL DEFAULT NULL COMMENT 'số lượng',
  `unit_price` DOUBLE NULL DEFAULT NULL COMMENT 'đơn giá',
  `product_id` INT(11) NOT NULL,
  `invoice_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_invoice_details_product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_invoice_details_invoice1_idx` (`invoice_id` ASC) VISIBLE,
  CONSTRAINT `fk_invoice_details_product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `cellphonesite`.`product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_invoice_details_invoice1`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `cellphonesite`.`invoice` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
