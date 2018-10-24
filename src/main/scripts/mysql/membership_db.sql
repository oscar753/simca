SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `perfil` ;

CREATE  TABLE IF NOT EXISTS `perfil` (
  `perfil_pk` INT NOT NULL COMMENT 'ID de la tabla perfil' ,
  `codigo_perfil` VARCHAR(40) NOT NULL ,
  `descripcion_perfil` VARCHAR(200) NOT NULL ,
  PRIMARY KEY (`perfil_pk`) ,
  UNIQUE INDEX `idx_coigo_perfil` (`codigo_perfil` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
PACK_KEYS = Default;


-- -----------------------------------------------------
-- Table `preregistro`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `preregistro` ;

CREATE  TABLE IF NOT EXISTS `preregistro` (
  `preregistro_pk` INT NOT NULL AUTO_INCREMENT ,
  `correo` VARCHAR(80) NOT NULL ,
  `id_seguridad` VARCHAR(50) NULL DEFAULT NULL ,
  `ventana_para_id_seguridad` DATETIME NULL DEFAULT NULL ,
  PRIMARY KEY (`preregistro_pk`) ,
  UNIQUE INDEX `id_seguridad_UNIQUE` (`id_seguridad` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario` ;

CREATE  TABLE IF NOT EXISTS `usuario` (
  `usuario_pk` INT NOT NULL AUTO_INCREMENT COMMENT 'ID de la tabla Usuario' ,
  `usuario` VARCHAR(50) NOT NULL COMMENT 'Login del usuario' ,
  `clave` VARCHAR(128) NOT NULL COMMENT 'Password del usuario' ,
  `correo` VARCHAR(254) NOT NULL COMMENT 'Email del usuario.' ,
  `fecha_creacion` DATETIME NOT NULL COMMENT 'Fecha en que se creó la cuenta de usuario' ,
  `cuenta_no_expirada` BIT NOT NULL DEFAULT true COMMENT 'Indica si la cuenta no ha expirado' ,
  `cuenta_no_bloqueada` BIT NOT NULL DEFAULT true COMMENT 'Indica si la cuenta no está bloqueada' ,
  `credencial_no_expirada` BIT NOT NULL DEFAULT true COMMENT 'Indica si la clave no ha expirado' ,
  `habilitado` BIT NOT NULL DEFAULT true COMMENT 'Indica si el usuario está habilitado' ,
  `contador_intentos_fallidos` INT(11) NOT NULL DEFAULT true COMMENT 'Mantiene un conteo de los intentos fallidos para acceder al sistema' ,
  `instante_de_bloqueo` DATETIME NULL DEFAULT NULL COMMENT 'Fecha en que el usuario fue bloqueado' ,
  `pregunta_secreta` VARCHAR(200) NOT NULL COMMENT 'Pregunta secreta para recuperar el password' ,
  `respuesta_secreta` VARCHAR(200) NOT NULL COMMENT 'Respuesta a la pregunta secreta' ,
  `id_de_seguridad` VARCHAR(50) NULL DEFAULT NULL COMMENT 'UID temporal utilizado para identificar al usuario por un medio extra al login' ,
  `ventana_para_id_seguridad` DATETIME NULL DEFAULT NULL COMMENT 'Fecha hasta la que el id_de_seguridad es válido' ,
  `fecha_ultimo_acceso` DATETIME NULL DEFAULT NULL COMMENT 'Fecha en la que el usuario accedió por última vez al sistema' ,
  `fecha_ultimo_cambio_clave` DATETIME NULL DEFAULT NULL COMMENT 'Fecha en que el usuario cambió su password por última vez' ,
  PRIMARY KEY (`usuario_pk`) ,
  UNIQUE INDEX `idx_correo` (`correo` ASC) ,
  UNIQUE INDEX `idx_usuario` (`usuario` ASC) ,
  UNIQUE INDEX `id_de_seguridad_UNIQUE` (`id_de_seguridad` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 100
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usuario_detalle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario_detalle` ;

CREATE  TABLE IF NOT EXISTS `usuario_detalle` (
  `usuario_fk` INT NOT NULL COMMENT 'ID del usuario (llave foránea al id de la tabla usuario)' ,
  `nombre` VARCHAR(40) NOT NULL COMMENT 'Nombre del usuario' ,
  `ap_paterno` VARCHAR(40) NOT NULL COMMENT 'Apellido paterno del usuario' ,
  `ap_materno` VARCHAR(40) NULL DEFAULT NULL COMMENT 'Apellido materno del usuario' ,
  `telefonos` VARCHAR(40) NOT NULL COMMENT 'Teléfonos del usuario' ,
  `direccion` VARCHAR(40) NOT NULL COMMENT 'Dirección del usuario' ,
  `manda_correo_promo` BIT NOT NULL DEFAULT true COMMENT 'Indica si el usuario desea que se le envíe información vía correo electrónico' ,
  PRIMARY KEY (`usuario_fk`) ,
  CONSTRAINT `fk_usuario_detalle_usuario1`
    FOREIGN KEY (`usuario_fk` )
    REFERENCES `usuario` (`usuario_pk` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `usuario_perfil`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `usuario_perfil` ;

CREATE  TABLE IF NOT EXISTS `usuario_perfil` (
  `usuario_fk` INT NOT NULL ,
  `perfil_fk` INT NOT NULL ,
  PRIMARY KEY (`usuario_fk`, `perfil_fk`) ,
  INDEX `fk_usuario_perfil_perfil` (`perfil_fk` ASC) ,
  CONSTRAINT `fk_usuario_perfil_perfil`
    FOREIGN KEY (`perfil_fk` )
    REFERENCES `perfil` (`perfil_pk` ),
  CONSTRAINT `fk_usuario_perfil_usuario`
    FOREIGN KEY (`usuario_fk` )
    REFERENCES `usuario` (`usuario_pk` ))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `tipo_configuracion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_configuracion` ;

CREATE  TABLE IF NOT EXISTS `tipo_configuracion` (
  `tipo_configuracion_pk` INT NOT NULL COMMENT 'Llave primaria del tipo de configuración' ,
  `nombre` VARCHAR(45) NOT NULL COMMENT 'Nombre del tipo de configuración' ,
  `descripcion` VARCHAR(150) NULL COMMENT 'Una descripción breve del tipo de configuración' ,
  PRIMARY KEY (`tipo_configuracion_pk`) ,
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `configuracion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `configuracion` ;

CREATE  TABLE IF NOT EXISTS `configuracion` (
  `configuracion_pk` INT NOT NULL AUTO_INCREMENT COMMENT 'llave primaria de la tabla' ,
  `tipo_configuracion_fk` INT NOT NULL COMMENT 'indica el tipo de configuración' ,
  `llave` VARCHAR(45) NOT NULL COMMENT 'nombre que identifica la configuración' ,
  `valor_str` VARCHAR(250) NULL COMMENT 'Valor STRING de la configuración' ,
  `valor_int` INT NULL COMMENT 'Valor INTEGER de la configuración' ,
  `valor_flt` FLOAT NULL COMMENT 'Valor FLOAT de la configuración' ,
  `valor_bol` BIT NULL COMMENT 'Valor BOOLEAN de la configuración' ,
  PRIMARY KEY (`configuracion_pk`) ,
  UNIQUE INDEX `llave_UNIQUE` (`llave` ASC) ,
  INDEX `fk_configuracion_tipo_configuracion1_idx` (`tipo_configuracion_fk` ASC) ,
  CONSTRAINT `fk_configuracion_tipo_configuracion1`
    FOREIGN KEY (`tipo_configuracion_fk` )
    REFERENCES `tipo_configuracion` (`tipo_configuracion_pk` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `imagen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `imagen` ;

CREATE  TABLE IF NOT EXISTS `imagen` (
  `imagen_pk` INT NOT NULL ,
  `mime_type` VARCHAR(20) NOT NULL ,
  `nombre` VARCHAR(150) NULL ,
  `contenido` BLOB NOT NULL ,
  PRIMARY KEY (`imagen_pk`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `tipo_bitacora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tipo_bitacora` ;

CREATE  TABLE IF NOT EXISTS `tipo_bitacora` (
  `tipo_bitacora_pk` INT NOT NULL ,
  `codigo` VARCHAR(30) NOT NULL ,
  `descripcion` VARCHAR(150) NULL ,
  PRIMARY KEY (`tipo_bitacora_pk`) ,
  UNIQUE INDEX `nombre_UNIQUE` (`codigo` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bitacora`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bitacora` ;

CREATE  TABLE IF NOT EXISTS `bitacora` (
  `bitacora_pk` INT NOT NULL AUTO_INCREMENT ,
  `tipo_bitacora_fk` INT NOT NULL ,
  `ip` VARCHAR(45) NOT NULL ,
  `eventDate` DATETIME NOT NULL ,
  `username` VARCHAR(25) NULL DEFAULT NULL ,
  `extraInfo` VARCHAR(255) NULL DEFAULT NULL ,
  PRIMARY KEY (`bitacora_pk`) ,
  INDEX `fk_bitacora_tipo_bitacora1_idx` (`tipo_bitacora_fk` ASC) ,
  CONSTRAINT `fk_bitacora_tipo_bitacora1`
    FOREIGN KEY (`tipo_bitacora_fk` )
    REFERENCES `tipo_bitacora` (`tipo_bitacora_pk` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `imagen_usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `imagen_usuario` ;

CREATE  TABLE IF NOT EXISTS `imagen_usuario` (
  `usuario_usuario_pk` INT NOT NULL ,
  `imagen_imagen_pk` INT NOT NULL ,
  PRIMARY KEY (`usuario_usuario_pk`, `imagen_imagen_pk`) ,
  INDEX `fk_imagen_usuario_imagen1_idx` (`imagen_imagen_pk` ASC) ,
  CONSTRAINT `fk_imagen_usuario_usuario1`
    FOREIGN KEY (`usuario_usuario_pk` )
    REFERENCES `usuario` (`usuario_pk` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_imagen_usuario_imagen1`
    FOREIGN KEY (`imagen_imagen_pk` )
    REFERENCES `imagen` (`imagen_pk` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Placeholder table for view `perfiles_por_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `perfiles_por_usuario` (`usuario_pk` INT, `usuario` INT, `perfil_pk` INT, `codigo_perfil` INT);

-- -----------------------------------------------------
-- View `perfiles_por_usuario`
-- -----------------------------------------------------
DROP VIEW IF EXISTS `perfiles_por_usuario` ;
DROP TABLE IF EXISTS `perfiles_por_usuario`;
CREATE  OR REPLACE VIEW `perfiles_por_usuario` AS 
	SELECT 
		`usuario`.`usuario_pk` AS `usuario_pk`,
		`usuario`.`usuario` AS `usuario`,
		`perfil`.`perfil_pk` AS `perfil_pk`,
		`perfil`.`codigo_perfil` AS `codigo_perfil`
	FROM
		`usuario`
		JOIN `usuario_perfil`
			ON `usuario`.`usuario_pk` = `usuario_perfil`.`usuario_fk`
		JOIN `perfil`
			ON `usuario_perfil`.`perfil_fk` = `perfil`.`perfil_pk`
;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `perfil`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `perfil` (`perfil_pk`, `codigo_perfil`, `descripcion_perfil`) VALUES (1, 'PERFIL_USER', 'PERFIL USUARIO');
INSERT INTO `perfil` (`perfil_pk`, `codigo_perfil`, `descripcion_perfil`) VALUES (2, 'PERFIL_ADMIN', 'PERFIL ADMINISTRADOR');

COMMIT;

-- -----------------------------------------------------
-- Data for table `usuario`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `usuario` (`usuario_pk`, `usuario`, `clave`, `correo`, `fecha_creacion`, `cuenta_no_expirada`, `cuenta_no_bloqueada`, `credencial_no_expirada`, `habilitado`, `contador_intentos_fallidos`, `instante_de_bloqueo`, `pregunta_secreta`, `respuesta_secreta`, `id_de_seguridad`, `ventana_para_id_seguridad`, `fecha_ultimo_acceso`, `fecha_ultimo_cambio_clave`) VALUES (1, 'admin', 'ffa17a01252f59275842a722172eb3ff', 'admin@correo.org', '2013-05-31 13:24:07', true, true, true, true, 0, '2009-05-31 13:24:18', '¿Cuanto es uno más uno?', 'dos', '8dGyT$XVEQ7C8gK$NLvgfkc6WurzkbGuTrctGlQtMrSTXGc3to', '2009-05-31 13:24:07', '2013-05-31 13:24:18', '2013-05-31 13:24:18');
INSERT INTO `usuario` (`usuario_pk`, `usuario`, `clave`, `correo`, `fecha_creacion`, `cuenta_no_expirada`, `cuenta_no_bloqueada`, `credencial_no_expirada`, `habilitado`, `contador_intentos_fallidos`, `instante_de_bloqueo`, `pregunta_secreta`, `respuesta_secreta`, `id_de_seguridad`, `ventana_para_id_seguridad`, `fecha_ultimo_acceso`, `fecha_ultimo_cambio_clave`) VALUES (2, 'user', '21366b8f5c1b9a6e084000a4f5d15a08', 'empresa@correo.org', '2013-05-31 13:24:07', true, true, true, true, 0, '2013-05-31 13:24:18', '¿Cuanto es uno más uno?', 'dos', '8342T$XVEQ7C8gK$NLvgfkc6WurzkbGuTrctGlQtMrSTXGc3jo', '2013-05-31 13:24:18', '2013-05-31 13:24:18', '2013-05-31 13:24:18');

COMMIT;

-- -----------------------------------------------------
-- Data for table `usuario_detalle`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `usuario_detalle` (`usuario_fk`, `nombre`, `ap_paterno`, `ap_materno`, `telefonos`, `direccion`, `manda_correo_promo`) VALUES (1, 'Administrador', 'Admin', NULL, '1234567890', 'Confidencial', false);
INSERT INTO `usuario_detalle` (`usuario_fk`, `nombre`, `ap_paterno`, `ap_materno`, `telefonos`, `direccion`, `manda_correo_promo`) VALUES (2, 'Usuario', 'User', NULL, '1234567890', 'Confidencial', false);

COMMIT;

-- -----------------------------------------------------
-- Data for table `usuario_perfil`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `usuario_perfil` (`usuario_fk`, `perfil_fk`) VALUES (1, 1);
INSERT INTO `usuario_perfil` (`usuario_fk`, `perfil_fk`) VALUES (1, 2);
INSERT INTO `usuario_perfil` (`usuario_fk`, `perfil_fk`) VALUES (2, 1);

COMMIT;

-- -----------------------------------------------------
-- Data for table `tipo_configuracion`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `tipo_configuracion` (`tipo_configuracion_pk`, `nombre`, `descripcion`) VALUES (1, 'String', 'Valores de tipo texto');
INSERT INTO `tipo_configuracion` (`tipo_configuracion_pk`, `nombre`, `descripcion`) VALUES (2, 'Integer', 'Valores de tipo entero');
INSERT INTO `tipo_configuracion` (`tipo_configuracion_pk`, `nombre`, `descripcion`) VALUES (3, 'Float', 'Valores de tipo decimales');
INSERT INTO `tipo_configuracion` (`tipo_configuracion_pk`, `nombre`, `descripcion`) VALUES (4, 'Boolean', 'Valores de tipo booleano');

COMMIT;

-- -----------------------------------------------------
-- Data for table `tipo_bitacora`
-- -----------------------------------------------------
START TRANSACTION;
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (1, 'GENERAL', 'Mensaje general');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (2, 'LOGIN_NOT_FOUND', 'No existe el login');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (3, 'EMAIL_NOT_FOUND', 'No existe el correo electrónico');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (4, 'UNAUTHORIZED_ACCESS', 'Acceso no permitdo');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (5, 'INVALID_SID', 'SID inválido');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (6, 'USER_BLOCKED', 'Usuario bloqueado');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (7, 'MAIL_SENT', 'Correo enviado');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (8, 'WRONG_PASSWORD', 'Clave incorrecta');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (9, 'REGISTRATION_APPLIED', 'Registro solicitado');
INSERT INTO `tipo_bitacora` (`tipo_bitacora_pk`, `codigo`, `descripcion`) VALUES (10, 'REGISTRATION_COMPLETED', 'Registro completado');

COMMIT;
