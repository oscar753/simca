-- -----------------------------------------------------
-- Table perfil
-- -----------------------------------------------------
CREATE TABLE perfil (
  perfil_pk INTEGER NOT NULL ,
  codigo_perfil VARCHAR(40) NOT NULL UNIQUE ,
  descripcion_perfil VARCHAR(200) NOT NULL ,
  PRIMARY KEY (perfil_pk) 
);

-- -----------------------------------------------------
-- Table preregistro
-- -----------------------------------------------------
CREATE TABLE preregistro (
  preregistro_pk SERIAL NOT NULL ,
  correo VARCHAR(80) NOT NULL ,
  id_seguridad VARCHAR(50) NOT NULL UNIQUE ,
  ventana_para_id_seguridad DATETIME YEAR TO FRACTION ,
  PRIMARY KEY (preregistro_pk)
 );

-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE usuario (
  usuario_pk SERIAL NOT NULL ,
  usuario VARCHAR(50) NOT NULL UNIQUE ,
  clave VARCHAR(128) NOT NULL ,
  correo VARCHAR(254) NOT NULL UNIQUE ,
  fecha_creacion DATETIME YEAR TO FRACTION NOT NULL ,
  cuenta_no_expirada BOOLEAN NOT NULL ,
  cuenta_no_bloqueada BOOLEAN NOT NULL ,
  credencial_no_expirada BOOLEAN NOT NULL ,
  habilitado BOOLEAN NOT NULL ,
  contador_intentos_fallidos INTEGER NOT NULL ,
  instante_de_bloqueo DATETIME YEAR TO FRACTION ,
  pregunta_secreta VARCHAR(200) NOT NULL ,
  respuesta_secreta VARCHAR(200) NOT NULL ,
  id_de_seguridad VARCHAR(50) UNIQUE ,
  ventana_para_id_seguridad DATETIME YEAR TO FRACTION ,
  fecha_ultimo_acceso DATETIME YEAR TO FRACTION ,
  fecha_ultimo_cambio_clave DATETIME YEAR TO FRACTION ,
  PRIMARY KEY (usuario_pk) );


-- -----------------------------------------------------
-- Table usuario_detalle
-- -----------------------------------------------------
CREATE TABLE usuario_detalle (
  usuario_fk INTEGER NOT NULL ,
  nombre VARCHAR(40) NOT NULL ,
  ap_paterno VARCHAR(40) NOT NULL ,
  ap_materno VARCHAR(40) ,
  telefonos VARCHAR(40) NOT NULL ,
  direccion VARCHAR(40) NOT NULL ,
  manda_correo_promo BOOLEAN NOT NULL ,
  PRIMARY KEY (usuario_fk) ,
  FOREIGN KEY (usuario_fk )
    REFERENCES usuario (usuario_pk )
 );


-- -----------------------------------------------------
-- Table usuario_perfil
-- -----------------------------------------------------
CREATE TABLE usuario_perfil (
  usuario_fk INTEGER NOT NULL ,
  perfil_fk INTEGER NOT NULL ,
  PRIMARY KEY (usuario_fk, perfil_fk) ,
  FOREIGN KEY (perfil_fk )
    REFERENCES perfil (perfil_pk ),
  FOREIGN KEY (usuario_fk )
    REFERENCES usuario (usuario_pk )
);


-- -----------------------------------------------------
-- Table tipo_configuracion
-- -----------------------------------------------------
CREATE TABLE tipo_configuracion (
  tipo_configuracion_pk INTEGER NOT NULL ,
  nombre VARCHAR(45) NOT NULL UNIQUE ,
  descripcion VARCHAR(150) ,
  PRIMARY KEY (tipo_configuracion_pk)
);


-- -----------------------------------------------------
-- Table configuracion
-- -----------------------------------------------------
CREATE TABLE configuracion (
  configuracion_pk SERIAL NOT NULL ,
  tipo_configuracion_fk INTEGER NOT NULL ,
  llave VARCHAR(45) NOT NULL UNIQUE ,
  valor_str VARCHAR(250) ,
  valor_int INTEGER ,
  valor_flt DOUBLE PRECISION ,
  valor_bol BOOLEAN ,
  PRIMARY KEY (configuracion_pk) ,
  FOREIGN KEY (tipo_configuracion_fk )
    REFERENCES tipo_configuracion (tipo_configuracion_pk )
);


-- -----------------------------------------------------
-- Table imagen
-- -----------------------------------------------------
CREATE TABLE imagen (
  imagen_pk SERIAL NOT NULL ,
  mime_type VARCHAR(20) NOT NULL ,
  nombre VARCHAR(150) ,
  contenido BLOB(2048) NOT NULL ,
  PRIMARY KEY (imagen_pk)
);


-- -----------------------------------------------------
-- Table tipo_bitacora
-- -----------------------------------------------------
CREATE TABLE tipo_bitacora (
  tipo_bitacora_pk INTEGER NOT NULL ,
  codigo VARCHAR(30) NOT NULL UNIQUE ,
  descripcion VARCHAR(150) ,
  PRIMARY KEY (tipo_bitacora_pk) 
);


-- -----------------------------------------------------
-- Table bitacora
-- -----------------------------------------------------
CREATE TABLE bitacora (
  bitacora_pk SERIAL NOT NULL ,
  tipo_bitacora_fk INTEGER NOT NULL ,
  ip VARCHAR(45) NOT NULL ,
  eventDate DATETIME YEAR TO FRACTION NOT NULL ,
  username VARCHAR(25) ,
  extraInfo VARCHAR(255) ,
  PRIMARY KEY (bitacora_pk) ,
  FOREIGN KEY (tipo_bitacora_fk )
    REFERENCES tipo_bitacora (tipo_bitacora_pk )
);


-- -----------------------------------------------------
-- Table imagen_usuario
-- -----------------------------------------------------
CREATE TABLE imagen_usuario (
  usuario_usuario_pk INTEGER NOT NULL ,
  imagen_imagen_pk INTEGER NOT NULL ,
  PRIMARY KEY (usuario_usuario_pk, imagen_imagen_pk) ,
  FOREIGN KEY (usuario_usuario_pk )
    REFERENCES usuario (usuario_pk ),
  FOREIGN KEY (imagen_imagen_pk )
    REFERENCES imagen (imagen_pk )
);




-- -----------------------------------------------------
-- Data for table perfil
-- -----------------------------------------------------
INSERT INTO perfil (perfil_pk, codigo_perfil, descripcion_perfil) VALUES (1, 'PERFIL_USER', 'PERFIL USUARIO');
INSERT INTO perfil (perfil_pk, codigo_perfil, descripcion_perfil) VALUES (2, 'PERFIL_ADMIN', 'PERFIL ADMINISTRADOR');


-- -----------------------------------------------------
-- Data for table usuario
-- -----------------------------------------------------
INSERT INTO usuario (usuario_pk, usuario, clave, correo, fecha_creacion, cuenta_no_expirada, cuenta_no_bloqueada, credencial_no_expirada, habilitado, contador_intentos_fallidos, instante_de_bloqueo, pregunta_secreta, respuesta_secreta, id_de_seguridad, ventana_para_id_seguridad, fecha_ultimo_acceso, fecha_ultimo_cambio_clave) VALUES (1, 'admin', 'ffa17a01252f59275842a722172eb3ff', 'admin@correo.org', '2013-05-31 13:24:07', 't', 't', 't', 't', 0, '2009-05-31 13:24:18', '¿Cuanto es uno más uno?', 'dos', '8dGyT$XVEQ7C8gK$NLvgfkc6WurzkbGuTrctGlQtMrSTXGc3to', '2009-05-31 13:24:07', '2013-05-31 13:24:18', '2013-05-31 13:24:18');
INSERT INTO usuario (usuario_pk, usuario, clave, correo, fecha_creacion, cuenta_no_expirada, cuenta_no_bloqueada, credencial_no_expirada, habilitado, contador_intentos_fallidos, instante_de_bloqueo, pregunta_secreta, respuesta_secreta, id_de_seguridad, ventana_para_id_seguridad, fecha_ultimo_acceso, fecha_ultimo_cambio_clave) VALUES (2, 'user', '21366b8f5c1b9a6e084000a4f5d15a08', 'empresa@correo.org', '2013-05-31 13:24:07', 't', 't', 't', 't', 0, '2013-05-31 13:24:18', '¿Cuanto es uno más uno?', 'dos', '8342T$XVEQ7C8gK$NLvgfkc6WurzkbGuTrctGlQtMrSTXGc3jo', '2013-05-31 13:24:18', '2013-05-31 13:24:18', '2013-05-31 13:24:18');


-- -----------------------------------------------------
-- Data for table usuario_detalle
-- -----------------------------------------------------
INSERT INTO usuario_detalle (usuario_fk, nombre, ap_paterno, ap_materno, telefonos, direccion, manda_correo_promo) VALUES (1, 'Administrador', 'Admin', NULL, '1234567890', 'Confidencial', 'f');
INSERT INTO usuario_detalle (usuario_fk, nombre, ap_paterno, ap_materno, telefonos, direccion, manda_correo_promo) VALUES (2, 'Usuario', 'User', NULL, '1234567890', 'Confidencial', 'f');


-- -----------------------------------------------------
-- Data for table usuario_perfil
-- -----------------------------------------------------
INSERT INTO usuario_perfil (usuario_fk, perfil_fk) VALUES (1, 1);
INSERT INTO usuario_perfil (usuario_fk, perfil_fk) VALUES (1, 2);
INSERT INTO usuario_perfil (usuario_fk, perfil_fk) VALUES (2, 1);


-- -----------------------------------------------------
-- Data for table tipo_configuracion
-- -----------------------------------------------------
INSERT INTO tipo_configuracion (tipo_configuracion_pk, nombre, descripcion) VALUES (1, 'String', 'Valores de tipo texto');
INSERT INTO tipo_configuracion (tipo_configuracion_pk, nombre, descripcion) VALUES (2, 'Integer', 'Valores de tipo entero');
INSERT INTO tipo_configuracion (tipo_configuracion_pk, nombre, descripcion) VALUES (3, 'Float', 'Valores de tipo decimales');
INSERT INTO tipo_configuracion (tipo_configuracion_pk, nombre, descripcion) VALUES (4, 'Boolean', 'Valores de tipo booleano');


-- -----------------------------------------------------
-- Data for table tipo_bitacora
-- -----------------------------------------------------
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (1, 'GENERAL', 'Mensaje general');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (2, 'LOGIN_NOT_FOUND', 'No existe el login');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (3, 'EMAIL_NOT_FOUND', 'No existe el correo electrónico');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (4, 'UNAUTHORIZED_ACCESS', 'Acceso no permitdo');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (5, 'INVALID_SID', 'SID inválido');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (6, 'USER_BLOCKED', 'Usuario bloqueado');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (7, 'MAIL_SENT', 'Correo enviado');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (8, 'WRONG_PASSWORD', 'Clave incorrecta');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (9, 'REGISTRATION_APPLIED', 'Registro solicitado');
INSERT INTO tipo_bitacora (tipo_bitacora_pk, codigo, descripcion) VALUES (10, 'REGISTRATION_COMPLETED', 'Registro completado');

