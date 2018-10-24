SET DEFINE OFF;
--------------------------------------------------------
--  DDL for Table BITACORA
--------------------------------------------------------

  CREATE TABLE "BITACORA" 
   (	"BITACORA_PK" NUMBER(10,0), 
	"TIPO_BITACORA_FK" NUMBER(10,0), 
	"IP" VARCHAR2(45 CHAR), 
	"EVENTDATE" DATE, 
	"USERNAME" VARCHAR2(25 CHAR), 
	"EXTRAINFO" VARCHAR2(255 CHAR)
   );
--------------------------------------------------------
--  DDL for Table CONFIGURACION
--------------------------------------------------------

  CREATE TABLE "CONFIGURACION" 
   (	"CONFIGURACION_PK" NUMBER(10,0), 
	"TIPO_CONFIGURACION_FK" NUMBER(10,0), 
	"LLAVE" VARCHAR2(45 CHAR), 
	"VALOR_STR" VARCHAR2(250 CHAR), 
	"VALOR_INT" NUMBER(10,0), 
	"VALOR_FLT" FLOAT(126), 
	"VALOR_BOL" CHAR(1 CHAR)
   );

   COMMENT ON COLUMN  "CONFIGURACION"."CONFIGURACION_PK" IS 'llave primaria de la tabla';
   COMMENT ON COLUMN  "CONFIGURACION"."TIPO_CONFIGURACION_FK" IS 'indica el tipo de configuración';
   COMMENT ON COLUMN  "CONFIGURACION"."LLAVE" IS 'nombre que identifica la configuración';
   COMMENT ON COLUMN  "CONFIGURACION"."VALOR_STR" IS 'Valor STRING de la configuración';
   COMMENT ON COLUMN  "CONFIGURACION"."VALOR_INT" IS 'Valor INTEGER de la configuración';
   COMMENT ON COLUMN  "CONFIGURACION"."VALOR_FLT" IS 'Valor FLOAT de la configuración';
   COMMENT ON COLUMN  "CONFIGURACION"."VALOR_BOL" IS 'Valor BOOLEAN de la configuración';
--------------------------------------------------------
--  DDL for Table IMAGEN
--------------------------------------------------------

  CREATE TABLE  "IMAGEN" 
   (	"ID_IMAGEN" NUMBER(10,0), 
	"MIME_TYPE" VARCHAR2(20 CHAR), 
	"NOMBRE" VARCHAR2(150 CHAR), 
	"CONTENIDO" BLOB
   );
--------------------------------------------------------
--  DDL for Table IMAGEN_USUARIO
--------------------------------------------------------

  CREATE TABLE  "IMAGEN_USUARIO" 
   (	"USUARIO_USUARIO_PK" NUMBER(10,0), 
	"IMAGEN_ID_IMAGEN" NUMBER(10,0)
   );
--------------------------------------------------------
--  DDL for Table PERFIL
--------------------------------------------------------

  CREATE TABLE  "PERFIL" 
   (	"PERFIL_PK" NUMBER(10,0), 
	"CODIGO_PERFIL" VARCHAR2(40 CHAR), 
	"DESCRIPCION_PERFIL" VARCHAR2(200 CHAR)
   );

   COMMENT ON COLUMN  "PERFIL"."PERFIL_PK" IS 'ID de la tabla perfil';
--------------------------------------------------------
--  DDL for Table PREREGISTRO
--------------------------------------------------------

  CREATE TABLE  "PREREGISTRO" 
   (	"PREREGISTRO_PK" NUMBER(10,0), 
	"CORREO" VARCHAR2(80 CHAR), 
	"ID_SEGURIDAD" VARCHAR2(50 CHAR), 
	"VENTANA_PARA_ID_SEGURIDAD" DATE
   );
--------------------------------------------------------
--  DDL for Table USUARIO
--------------------------------------------------------

  CREATE TABLE  "USUARIO" 
   (	"USUARIO_PK" NUMBER(10,0), 
	"USUARIO" VARCHAR2(50 CHAR), 
	"CLAVE" VARCHAR2(128 CHAR), 
	"CORREO" VARCHAR2(254 CHAR), 
	"FECHA_CREACION" DATE, 
	"CUENTA_NO_EXPIRADA" CHAR(1 CHAR) DEFAULT '1', 
	"CUENTA_NO_BLOQUEADA" CHAR(1 CHAR) DEFAULT '1', 
	"CREDENCIAL_NO_EXPIRADA" CHAR(1 CHAR) DEFAULT '1', 
	"HABILITADO" CHAR(1 CHAR) DEFAULT '1', 
	"CONTADOR_INTENTOS_FALLIDOS" NUMBER(10,0) DEFAULT '1', 
	"INSTANTE_DE_BLOQUEO" DATE, 
	"PREGUNTA_SECRETA" VARCHAR2(200 CHAR), 
	"RESPUESTA_SECRETA" VARCHAR2(200 CHAR), 
	"ID_DE_SEGURIDAD" VARCHAR2(50 CHAR), 
	"VENTANA_PARA_ID_SEGURIDAD" DATE, 
	"FECHA_ULTIMO_ACCESO" DATE, 
	"FECHA_ULTIMO_CAMBIO_CLAVE" DATE
   );

   COMMENT ON COLUMN  "USUARIO"."USUARIO_PK" IS 'ID de la tabla Usuario';
   COMMENT ON COLUMN  "USUARIO"."USUARIO" IS 'Login del usuario';
   COMMENT ON COLUMN  "USUARIO"."CLAVE" IS 'Password del usuario';
   COMMENT ON COLUMN  "USUARIO"."CORREO" IS 'Email del usuario.';
   COMMENT ON COLUMN  "USUARIO"."FECHA_CREACION" IS 'Fecha en que se creó la cuenta de usuario';
   COMMENT ON COLUMN  "USUARIO"."CUENTA_NO_EXPIRADA" IS 'Indica si la cuenta no ha expirado';
   COMMENT ON COLUMN  "USUARIO"."CUENTA_NO_BLOQUEADA" IS 'Indica si la cuenta no está bloqueada';
   COMMENT ON COLUMN  "USUARIO"."CREDENCIAL_NO_EXPIRADA" IS 'Indica si la clave no ha expirado';
   COMMENT ON COLUMN  "USUARIO"."HABILITADO" IS 'Indica si el usuario está habilitado';
   COMMENT ON COLUMN  "USUARIO"."CONTADOR_INTENTOS_FALLIDOS" IS 'Mantiene un conteo de los intentos fallidos para acceder al sistema';
   COMMENT ON COLUMN  "USUARIO"."INSTANTE_DE_BLOQUEO" IS 'Fecha en que el usuario fue bloqueado';
   COMMENT ON COLUMN  "USUARIO"."PREGUNTA_SECRETA" IS 'Pregunta secreta para recuperar el password';
   COMMENT ON COLUMN  "USUARIO"."RESPUESTA_SECRETA" IS 'Respuesta a la pregunta secreta';
   COMMENT ON COLUMN  "USUARIO"."ID_DE_SEGURIDAD" IS 'UID temporal utilizado para identificar al usuario por un medio extra al login';
   COMMENT ON COLUMN  "USUARIO"."VENTANA_PARA_ID_SEGURIDAD" IS 'Fecha hasta la que el id_de_seguridad es válido';
   COMMENT ON COLUMN  "USUARIO"."FECHA_ULTIMO_ACCESO" IS 'Fecha en la que el usuario accedió por última vez al sistema';
   COMMENT ON COLUMN  "USUARIO"."FECHA_ULTIMO_CAMBIO_CLAVE" IS 'Fecha en que el usuario cambió su password por última vez';
--------------------------------------------------------
--  DDL for Table USUARIO_DETALLE
--------------------------------------------------------

  CREATE TABLE  "USUARIO_DETALLE" 
   (	"USUARIO_FK" NUMBER(10,0), 
	"NOMBRE" VARCHAR2(40 CHAR), 
	"AP_PATERNO" VARCHAR2(40 CHAR), 
	"AP_MATERNO" VARCHAR2(40 CHAR), 
	"TELEFONOS" VARCHAR2(40 CHAR), 
	"DIRECCION" VARCHAR2(40 CHAR), 
	"MANDA_CORREO_PROMO" CHAR(1 CHAR) DEFAULT '1'
   );

   COMMENT ON COLUMN  "USUARIO_DETALLE"."USUARIO_FK" IS 'ID del usuario (llave foránea al id de la tabla usuario)';
   COMMENT ON COLUMN  "USUARIO_DETALLE"."NOMBRE" IS 'Nombre del usuario';
   COMMENT ON COLUMN  "USUARIO_DETALLE"."AP_PATERNO" IS 'Apellido paterno del usuario';
   COMMENT ON COLUMN  "USUARIO_DETALLE"."AP_MATERNO" IS 'Apellido materno del usuario';
   COMMENT ON COLUMN  "USUARIO_DETALLE"."TELEFONOS" IS 'Teléfonos del usuario';
   COMMENT ON COLUMN  "USUARIO_DETALLE"."DIRECCION" IS 'Dirección del usuario';
   COMMENT ON COLUMN  "USUARIO_DETALLE"."MANDA_CORREO_PROMO" IS 'Indica si el usuario desea que se le envíe información vía correo electrónico';
--------------------------------------------------------
--  DDL for Table USUARIO_PERFIL
--------------------------------------------------------

  CREATE TABLE  "USUARIO_PERFIL" 
   (	"USUARIO_FK" NUMBER(10,0), 
	"PERFIL_FK" NUMBER(10,0)
   );
--------------------------------------------------------
--  DDL for Index FK_BITACORA_TIPO_BITACORA1_IDX
--------------------------------------------------------

  CREATE INDEX  "FK_BITACORA_TIPO_BITACORA1_IDX" ON  "BITACORA" ("TIPO_BITACORA_FK");
--------------------------------------------------------
--  DDL for Index PRIMARY
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY" ON  "BITACORA" ("BITACORA_PK");
--------------------------------------------------------
--  DDL for Index FK_CONFIGURACION_TIPO_CONFIGUR
--------------------------------------------------------

  CREATE INDEX  "FK_CONFIGURACION_TIPO_CONFIGUR" ON  "CONFIGURACION" ("TIPO_CONFIGURACION_FK");
--------------------------------------------------------
--  DDL for Index LLAVE_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX  "LLAVE_UNIQUE" ON  "CONFIGURACION" ("LLAVE");
--------------------------------------------------------
--  DDL for Index PRIMARY_5
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_5" ON  "CONFIGURACION" ("CONFIGURACION_PK");
--------------------------------------------------------
--  DDL for Index PRIMARY_3
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_3" ON  "IMAGEN" ("ID_IMAGEN");
--------------------------------------------------------
--  DDL for Index FK_IMAGEN_USUARIO_IMAGEN1_IDX
--------------------------------------------------------

  CREATE INDEX  "FK_IMAGEN_USUARIO_IMAGEN1_IDX" ON  "IMAGEN_USUARIO" ("IMAGEN_ID_IMAGEN");
--------------------------------------------------------
--  DDL for Index PRIMARY_2
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_2" ON  "IMAGEN_USUARIO" ("USUARIO_USUARIO_PK", "IMAGEN_ID_IMAGEN");
--------------------------------------------------------
--  DDL for Index PRIMARY_7
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_7" ON  "PERFIL" ("PERFIL_PK");
--------------------------------------------------------
--  DDL for Index IDX_COIGO_PERFIL
--------------------------------------------------------

  CREATE UNIQUE INDEX  "IDX_COIGO_PERFIL" ON  "PERFIL" ("CODIGO_PERFIL");
--------------------------------------------------------
--  DDL for Index ID_SEGURIDAD_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX  "ID_SEGURIDAD_UNIQUE" ON  "PREREGISTRO" ("ID_SEGURIDAD");
--------------------------------------------------------
--  DDL for Index PRIMARY_6
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_6" ON  "PREREGISTRO" ("PREREGISTRO_PK");
--------------------------------------------------------
--  DDL for Index ID_DE_SEGURIDAD_UNIQUE
--------------------------------------------------------

  CREATE UNIQUE INDEX  "ID_DE_SEGURIDAD_UNIQUE" ON  "USUARIO" ("ID_DE_SEGURIDAD");
--------------------------------------------------------
--  DDL for Index IDX_CORREO
--------------------------------------------------------

  CREATE UNIQUE INDEX  "IDX_CORREO" ON  "USUARIO" ("CORREO") ;
--------------------------------------------------------
--  DDL for Index IDX_USUARIO
--------------------------------------------------------

  CREATE UNIQUE INDEX  "IDX_USUARIO" ON  "USUARIO" ("USUARIO");
--------------------------------------------------------
--  DDL for Index PRIMARY_4
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_4" ON  "USUARIO" ("USUARIO_PK");
--------------------------------------------------------
--  DDL for Index PRIMARY_1
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_1" ON  "USUARIO_DETALLE" ("USUARIO_FK");
--------------------------------------------------------
--  DDL for Index FK_USUARIO_PERFIL_PERFIL
--------------------------------------------------------

  CREATE INDEX  "FK_USUARIO_PERFIL_PERFIL" ON  "USUARIO_PERFIL" ("PERFIL_FK");
--------------------------------------------------------
--  DDL for Index PRIMARY_10
--------------------------------------------------------

  CREATE UNIQUE INDEX  "PRIMARY_10" ON  "USUARIO_PERFIL" ("USUARIO_FK", "PERFIL_FK");
--------------------------------------------------------
--  Constraints for Table BITACORA
--------------------------------------------------------

  ALTER TABLE  "BITACORA" ADD CONSTRAINT "PRIMARY" PRIMARY KEY ("BITACORA_PK");
  ALTER TABLE  "BITACORA" MODIFY ("EVENTDATE" NOT NULL ENABLE);
  ALTER TABLE  "BITACORA" MODIFY ("IP" NOT NULL ENABLE);
  ALTER TABLE  "BITACORA" MODIFY ("TIPO_BITACORA_FK" NOT NULL ENABLE);
  ALTER TABLE  "BITACORA" MODIFY ("BITACORA_PK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table CONFIGURACION
--------------------------------------------------------

  ALTER TABLE  "CONFIGURACION" ADD CONSTRAINT "PRIMARY_5" PRIMARY KEY ("CONFIGURACION_PK");
  ALTER TABLE  "CONFIGURACION" ADD CONSTRAINT "LLAVE_UNIQUE" UNIQUE ("LLAVE");
  ALTER TABLE  "CONFIGURACION" MODIFY ("LLAVE" NOT NULL ENABLE);
  ALTER TABLE  "CONFIGURACION" MODIFY ("TIPO_CONFIGURACION_FK" NOT NULL ENABLE);
  ALTER TABLE  "CONFIGURACION" MODIFY ("CONFIGURACION_PK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table IMAGEN
--------------------------------------------------------

  ALTER TABLE  "IMAGEN" ADD CONSTRAINT "PRIMARY_3" PRIMARY KEY ("ID_IMAGEN");
  ALTER TABLE  "IMAGEN" MODIFY ("CONTENIDO" NOT NULL ENABLE);
  ALTER TABLE  "IMAGEN" MODIFY ("MIME_TYPE" NOT NULL ENABLE);
  ALTER TABLE  "IMAGEN" MODIFY ("ID_IMAGEN" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table IMAGEN_USUARIO
--------------------------------------------------------

  ALTER TABLE  "IMAGEN_USUARIO" ADD CONSTRAINT "PRIMARY_2" PRIMARY KEY ("USUARIO_USUARIO_PK", "IMAGEN_ID_IMAGEN");
  ALTER TABLE  "IMAGEN_USUARIO" MODIFY ("IMAGEN_ID_IMAGEN" NOT NULL ENABLE);
  ALTER TABLE  "IMAGEN_USUARIO" MODIFY ("USUARIO_USUARIO_PK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PERFIL
--------------------------------------------------------

  ALTER TABLE  "PERFIL" ADD CONSTRAINT "IDX_COIGO_PERFIL" UNIQUE ("CODIGO_PERFIL");
  ALTER TABLE  "PERFIL" ADD CONSTRAINT "PRIMARY_7" PRIMARY KEY ("PERFIL_PK");
  ALTER TABLE  "PERFIL" MODIFY ("DESCRIPCION_PERFIL" NOT NULL ENABLE);
  ALTER TABLE  "PERFIL" MODIFY ("CODIGO_PERFIL" NOT NULL ENABLE);
  ALTER TABLE  "PERFIL" MODIFY ("PERFIL_PK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table PREREGISTRO
--------------------------------------------------------

  ALTER TABLE  "PREREGISTRO" ADD CONSTRAINT "PRIMARY_6" PRIMARY KEY ("PREREGISTRO_PK");
  ALTER TABLE  "PREREGISTRO" ADD CONSTRAINT "ID_SEGURIDAD_UNIQUE" UNIQUE ("ID_SEGURIDAD");
  ALTER TABLE  "PREREGISTRO" MODIFY ("CORREO" NOT NULL ENABLE);
  ALTER TABLE  "PREREGISTRO" MODIFY ("PREREGISTRO_PK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USUARIO
--------------------------------------------------------

  ALTER TABLE  "USUARIO" ADD CONSTRAINT "PRIMARY_4" PRIMARY KEY ("USUARIO_PK");
  ALTER TABLE  "USUARIO" ADD CONSTRAINT "IDX_USUARIO" UNIQUE ("USUARIO");
  ALTER TABLE  "USUARIO" ADD CONSTRAINT "IDX_CORREO" UNIQUE ("CORREO");
  ALTER TABLE  "USUARIO" ADD CONSTRAINT "ID_DE_SEGURIDAD_UNIQUE" UNIQUE ("ID_DE_SEGURIDAD");
  ALTER TABLE  "USUARIO" MODIFY ("RESPUESTA_SECRETA" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("PREGUNTA_SECRETA" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("CONTADOR_INTENTOS_FALLIDOS" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("HABILITADO" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("CREDENCIAL_NO_EXPIRADA" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("CUENTA_NO_BLOQUEADA" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("CUENTA_NO_EXPIRADA" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("FECHA_CREACION" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("CORREO" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("CLAVE" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("USUARIO" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO" MODIFY ("USUARIO_PK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USUARIO_DETALLE
--------------------------------------------------------

  ALTER TABLE  "USUARIO_DETALLE" ADD CONSTRAINT "PRIMARY_1" PRIMARY KEY ("USUARIO_FK");
  ALTER TABLE  "USUARIO_DETALLE" MODIFY ("MANDA_CORREO_PROMO" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO_DETALLE" MODIFY ("DIRECCION" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO_DETALLE" MODIFY ("TELEFONOS" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO_DETALLE" MODIFY ("AP_PATERNO" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO_DETALLE" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO_DETALLE" MODIFY ("USUARIO_FK" NOT NULL ENABLE);
--------------------------------------------------------
--  Constraints for Table USUARIO_PERFIL
--------------------------------------------------------

  ALTER TABLE  "USUARIO_PERFIL" ADD CONSTRAINT "PRIMARY_10" PRIMARY KEY ("USUARIO_FK", "PERFIL_FK");
  ALTER TABLE  "USUARIO_PERFIL" MODIFY ("PERFIL_FK" NOT NULL ENABLE);
  ALTER TABLE  "USUARIO_PERFIL" MODIFY ("USUARIO_FK" NOT NULL ENABLE);
--------------------------------------------------------
--  Ref Constraints for Table IMAGEN_USUARIO
--------------------------------------------------------

  ALTER TABLE  "IMAGEN_USUARIO" ADD CONSTRAINT "FK_IMAGEN_USUARIO_IMAGEN1" FOREIGN KEY ("IMAGEN_ID_IMAGEN")
	  REFERENCES  "IMAGEN" ("ID_IMAGEN") ENABLE;
  ALTER TABLE  "IMAGEN_USUARIO" ADD CONSTRAINT "FK_IMAGEN_USUARIO_USUARIO1" FOREIGN KEY ("USUARIO_USUARIO_PK")
	  REFERENCES  "USUARIO" ("USUARIO_PK") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USUARIO_DETALLE
--------------------------------------------------------

  ALTER TABLE  "USUARIO_DETALLE" ADD CONSTRAINT "FK_USUARIO_DETALLE_USUARIO1" FOREIGN KEY ("USUARIO_FK")
	  REFERENCES  "USUARIO" ("USUARIO_PK") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table USUARIO_PERFIL
--------------------------------------------------------

  ALTER TABLE  "USUARIO_PERFIL" ADD CONSTRAINT "FK_USUARIO_PERFIL_PERFIL" FOREIGN KEY ("PERFIL_FK")
	  REFERENCES  "PERFIL" ("PERFIL_PK") ENABLE;
  ALTER TABLE  "USUARIO_PERFIL" ADD CONSTRAINT "FK_USUARIO_PERFIL_USUARIO" FOREIGN KEY ("USUARIO_FK")
	  REFERENCES  "USUARIO" ("USUARIO_PK") ENABLE;


--------------------------------------------------------
--  DDL for Table TIPO_BITACORA
--------------------------------------------------------

  CREATE TABLE  "TIPO_BITACORA" 
   (	"TIPO_BITACORA_PK" INTEGER, 
	"CODIGO" VARCHAR2(30 BYTE), 
	"DESCRIPCION" VARCHAR2(150 BYTE)
   );
--------------------------------------------------------
--  DDL for Index SYS_C0048704
--------------------------------------------------------

  CREATE UNIQUE INDEX  "SYS_C0048704" ON  "TIPO_BITACORA" ("TIPO_BITACORA_PK");
--------------------------------------------------------
--  DDL for Index SYS_C0048705
--------------------------------------------------------

  CREATE UNIQUE INDEX  "SYS_C0048705" ON  "TIPO_BITACORA" ("CODIGO");
--------------------------------------------------------
--  Constraints for Table TIPO_BITACORA
--------------------------------------------------------

  ALTER TABLE  "TIPO_BITACORA" ADD UNIQUE ("CODIGO");
  ALTER TABLE  "TIPO_BITACORA" ADD PRIMARY KEY ("TIPO_BITACORA_PK");
  ALTER TABLE  "TIPO_BITACORA" MODIFY ("CODIGO" NOT NULL ENABLE);
  ALTER TABLE  "TIPO_BITACORA" MODIFY ("TIPO_BITACORA_PK" NOT NULL ENABLE);

--------------------------------------------------------
--  DDL for Table TIPO_CONFIGURACION
--------------------------------------------------------

  CREATE TABLE  "TIPO_CONFIGURACION" 
   (	"TIPO_CONFIGURACION_PK" INTEGER, 
	"NOMBRE" VARCHAR2(45 BYTE), 
	"DESCRIPCION" VARCHAR2(150 BYTE)
   ) ;
--------------------------------------------------------
--  DDL for Index SYS_C0048690
--------------------------------------------------------

  CREATE UNIQUE INDEX  "SYS_C0048690" ON  "TIPO_CONFIGURACION" ("TIPO_CONFIGURACION_PK");
--------------------------------------------------------
--  DDL for Index SYS_C0048691
--------------------------------------------------------

  CREATE UNIQUE INDEX  "SYS_C0048691" ON  "TIPO_CONFIGURACION" ("NOMBRE");
--------------------------------------------------------
--  Constraints for Table TIPO_CONFIGURACION
--------------------------------------------------------

  ALTER TABLE  "TIPO_CONFIGURACION" ADD UNIQUE ("NOMBRE");
  ALTER TABLE  "TIPO_CONFIGURACION" ADD PRIMARY KEY ("TIPO_CONFIGURACION_PK");
  ALTER TABLE  "TIPO_CONFIGURACION" MODIFY ("NOMBRE" NOT NULL ENABLE);
  ALTER TABLE  "TIPO_CONFIGURACION" MODIFY ("TIPO_CONFIGURACION_PK" NOT NULL ENABLE);

--------------------------------------------------------
--  Ref Constraints for Table CONFIGURACION
--------------------------------------------------------

  ALTER TABLE  "CONFIGURACION" ADD CONSTRAINT "FK_CONFIGURACION_TIPO_CONFIGUR" FOREIGN KEY ("TIPO_CONFIGURACION_FK")
	  REFERENCES  "TIPO_CONFIGURACION" ("TIPO_CONFIGURACION_PK") ENABLE;
--------------------------------------------------------
--  Ref Constraints for Table BITACORA
--------------------------------------------------------

  ALTER TABLE  "BITACORA" ADD CONSTRAINT "FK_BITACORA_TIPO_BITACORA1" FOREIGN KEY ("TIPO_BITACORA_FK")
	  REFERENCES  "TIPO_BITACORA" ("TIPO_BITACORA_PK") ENABLE;


CREATE SEQUENCE preregistro_seq;
CREATE SEQUENCE usuario_seq;
CREATE SEQUENCE configuracion_seq;
CREATE SEQUENCE imagen_seq;
CREATE SEQUENCE bitacora_seq;

--------------------------------------------------------
--  DDL for Table CCS_CAT_EJERCICIOS
--------------------------------------------------------

  CREATE TABLE  "CCS_CAT_EJERCICIOS" 
   (	"ID_EJERCICIO" NUMBER(38,0), 
	"EJERCICIO" NUMBER(4,0), 
	"VIGENTE" NUMBER(1,0)
   ) ;
--------------------------------------------------------
--  DDL for Index CCS_CAT_EJERCICIOS_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX  "CCS_CAT_EJERCICIOS_PK" ON  "CCS_CAT_EJERCICIOS" ("ID_EJERCICIO");
--------------------------------------------------------
--  Constraints for Table CCS_CAT_EJERCICIOS
--------------------------------------------------------

  ALTER TABLE  "CCS_CAT_EJERCICIOS" ADD CONSTRAINT "CCS_CAT_EJERCICIOS_PK" PRIMARY KEY ("ID_EJERCICIO");
  ALTER TABLE  "CCS_CAT_EJERCICIOS" MODIFY ("VIGENTE" NOT NULL ENABLE);
  ALTER TABLE  "CCS_CAT_EJERCICIOS" MODIFY ("EJERCICIO" NOT NULL ENABLE);
  ALTER TABLE  "CCS_CAT_EJERCICIOS" MODIFY ("ID_EJERCICIO" NOT NULL ENABLE);

  commit;

  ////::::::::::::::::::

SET DEFINE OFF;
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (1,'GENERAL','Mensaje general');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (2,'LOGIN_NOT_FOUND','No existe el login');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (3,'EMAIL_NOT_FOUND','No existe el correo electrónico');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (4,'UNAUTHORIZED_ACCESS','Acceso no permitdo');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (5,'INVALID_SID','SID inválido');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (6,'USER_BLOCKED','Usuario bloqueado');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (7,'MAIL_SENT','Correo enviado');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (8,'WRONG_PASSWORD','Clave incorrecta');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (9,'REGISTRATION_APPLIED','Registro solicitado');
Insert into  TIPO_BITACORA (TIPO_BITACORA_PK,CODIGO,DESCRIPCION) values (10,'REGISTRATION_COMPLETED','Registro completado');

-------/////////////////////////////////////////////////////////////////////////////////////////////////
Insert into  TIPO_CONFIGURACION (TIPO_CONFIGURACION_PK,NOMBRE,DESCRIPCION) values (1,'String','Valores de tipo texto');
Insert into  TIPO_CONFIGURACION (TIPO_CONFIGURACION_PK,NOMBRE,DESCRIPCION) values (2,'Integer','Valores de tipo entero');
Insert into  TIPO_CONFIGURACION (TIPO_CONFIGURACION_PK,NOMBRE,DESCRIPCION) values (3,'Float','Valores de tipo decimales');
Insert into  TIPO_CONFIGURACION (TIPO_CONFIGURACION_PK,NOMBRE,DESCRIPCION) values (4,'Boolean','Valores de tipo booleano');

-------/////////////////////////////////////////////////////////////////////////////////////////////////
Insert into  BITACORA (BITACORA_PK,TIPO_BITACORA_FK,IP,EVENTDATE,USERNAME,EXTRAINFO) values (201,8,'localhost',to_timestamp('11/06/18 16:11:10.094000000','DD/MM/RR HH24:MI:SSXFF'),'anonymousUser','Password Incorreto. Intento fallido numero: 1 de 5 [admin] ');
Insert into  BITACORA (BITACORA_PK,TIPO_BITACORA_FK,IP,EVENTDATE,USERNAME,EXTRAINFO) values (162,4,'localhost',to_timestamp('07/06/18 13:47:25.521000000','DD/MM/RR HH24:MI:SSXFF'),'anonymousUser','Password Incorreto ! [weblogic] ');
Insert into  BITACORA (BITACORA_PK,TIPO_BITACORA_FK,IP,EVENTDATE,USERNAME,EXTRAINFO) values (141,4,'localhost',to_timestamp('07/06/18 11:54:52.244000000','DD/MM/RR HH24:MI:SSXFF'),'admin','Password Incorreto ! [dgticexterno.128] ');
Insert into  BITACORA (BITACORA_PK,TIPO_BITACORA_FK,IP,EVENTDATE,USERNAME,EXTRAINFO) values (161,4,'localhost',to_timestamp('07/06/18 13:47:23.567000000','DD/MM/RR HH24:MI:SSXFF'),'anonymousUser','Password Incorreto ! [weblogic] ');
Insert into  BITACORA (BITACORA_PK,TIPO_BITACORA_FK,IP,EVENTDATE,USERNAME,EXTRAINFO) values (163,4,'localhost',to_timestamp('07/06/18 16:08:14.066000000','DD/MM/RR HH24:MI:SSXFF'),'anonymousUser','Password Incorreto ! [weblogic] ');
Insert into  BITACORA (BITACORA_PK,TIPO_BITACORA_FK,IP,EVENTDATE,USERNAME,EXTRAINFO) values (181,8,'localhost',to_timestamp('11/06/18 09:43:33.498000000','DD/MM/RR HH24:MI:SSXFF'),'anonymousUser','Password Incorreto. Intento fallido numero: 1 de 5 [admin] ');
Insert into  BITACORA (BITACORA_PK,TIPO_BITACORA_FK,IP,EVENTDATE,USERNAME,EXTRAINFO) values (202,8,'localhost',to_timestamp('11/06/18 16:11:29.787000000','DD/MM/RR HH24:MI:SSXFF'),'anonymousUser','Password Incorreto. Intento fallido numero: 2 de 5 [admin] ');

-------/////////////////////////////////////////////////////////////////////////////////////////////////
Insert into  PERFIL (PERFIL_PK,CODIGO_PERFIL,DESCRIPCION_PERFIL) values (1,'PERFIL_USER','PERFIL USUARIO');
Insert into  PERFIL (PERFIL_PK,CODIGO_PERFIL,DESCRIPCION_PERFIL) values (2,'PERFIL_ADMIN','PERFIL ADMINISTRADOR');
Insert into  PERFIL (PERFIL_PK,CODIGO_PERFIL,DESCRIPCION_PERFIL) values (3,'PERFIL_CAPTURISTA','PERFIL CAPTURISTA');
Insert into  PERFIL (PERFIL_PK,CODIGO_PERFIL,DESCRIPCION_PERFIL) values (4,'PERFIL_VALIDADOR','PERFIL VALIDADOR');
Insert into  PERFIL (PERFIL_PK,CODIGO_PERFIL,DESCRIPCION_PERFIL) values (5,'PERFIL_PUBLICADOR','PERFIL PUBLICADOR');

-------/////////////////////////////////////////////////////////////////////////////////////////////////
Insert into  USUARIO (USUARIO_PK,USUARIO,CLAVE,CORREO,FECHA_CREACION,CUENTA_NO_EXPIRADA,CUENTA_NO_BLOQUEADA,CREDENCIAL_NO_EXPIRADA,HABILITADO,CONTADOR_INTENTOS_FALLIDOS,INSTANTE_DE_BLOQUEO,PREGUNTA_SECRETA,RESPUESTA_SECRETA,ID_DE_SEGURIDAD,VENTANA_PARA_ID_SEGURIDAD,FECHA_ULTIMO_ACCESO,FECHA_ULTIMO_CAMBIO_CLAVE) values (45,'publicador','56e4066a99c6d05d53d1672d9b96e88b','publicador@hotmail.com',to_timestamp('12/06/18 18:03:51.543000000','DD/MM/RR HH24:MI:SSXFF'),1,1,1,1,0,to_timestamp('12/06/09 18:42:01.352000000','DD/MM/RR HH24:MI:SSXFF'),'Perfil','Publicador','F9KxuKpW6digQSVBswXp1Qx8sJcE7g3VFgBup8IS0fMcKwQ8f1',to_timestamp('12/06/09 18:03:51.543000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('12/06/18 18:42:01.352000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('12/06/18 18:03:51.543000000','DD/MM/RR HH24:MI:SSXFF'));
Insert into  USUARIO (USUARIO_PK,USUARIO,CLAVE,CORREO,FECHA_CREACION,CUENTA_NO_EXPIRADA,CUENTA_NO_BLOQUEADA,CREDENCIAL_NO_EXPIRADA,HABILITADO,CONTADOR_INTENTOS_FALLIDOS,INSTANTE_DE_BLOQUEO,PREGUNTA_SECRETA,RESPUESTA_SECRETA,ID_DE_SEGURIDAD,VENTANA_PARA_ID_SEGURIDAD,FECHA_ULTIMO_ACCESO,FECHA_ULTIMO_CAMBIO_CLAVE) values (1,'admin','5ef6edb5ee6c9f5888389aa5871cd97e','otromail@hotmail.com',to_timestamp('31/05/13 00:00:00.000000000','DD/MM/RR HH24:MI:SSXFF'),1,1,1,1,0,to_timestamp('12/06/09 18:47:31.366000000','DD/MM/RR HH24:MI:SSXFF'),'¿Cuanto es uno más uno?','dostres','ffa17a',to_timestamp('31/05/09 00:00:00.000000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('12/06/18 18:47:31.366000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('20/03/18 00:00:00.000000000','DD/MM/RR HH24:MI:SSXFF'));
Insert into  USUARIO (USUARIO_PK,USUARIO,CLAVE,CORREO,FECHA_CREACION,CUENTA_NO_EXPIRADA,CUENTA_NO_BLOQUEADA,CREDENCIAL_NO_EXPIRADA,HABILITADO,CONTADOR_INTENTOS_FALLIDOS,INSTANTE_DE_BLOQUEO,PREGUNTA_SECRETA,RESPUESTA_SECRETA,ID_DE_SEGURIDAD,VENTANA_PARA_ID_SEGURIDAD,FECHA_ULTIMO_ACCESO,FECHA_ULTIMO_CAMBIO_CLAVE) values (44,'validador','83b9403594c84287e94e7e4daefcfccf','validador@hotmail.com',to_timestamp('12/06/18 17:58:25.224000000','DD/MM/RR HH24:MI:SSXFF'),1,1,1,1,0,to_timestamp('12/06/09 18:35:58.635000000','DD/MM/RR HH24:MI:SSXFF'),'Perfil','Validador','bb0TPk1iKIg4x1MxkpNCgmaJqvC6miYWPJY4ruD1VXEFVdCkjR',to_timestamp('12/06/09 17:58:25.224000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('12/06/18 18:35:58.635000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('12/06/18 17:58:25.225000000','DD/MM/RR HH24:MI:SSXFF'));
Insert into  USUARIO (USUARIO_PK,USUARIO,CLAVE,CORREO,FECHA_CREACION,CUENTA_NO_EXPIRADA,CUENTA_NO_BLOQUEADA,CREDENCIAL_NO_EXPIRADA,HABILITADO,CONTADOR_INTENTOS_FALLIDOS,INSTANTE_DE_BLOQUEO,PREGUNTA_SECRETA,RESPUESTA_SECRETA,ID_DE_SEGURIDAD,VENTANA_PARA_ID_SEGURIDAD,FECHA_ULTIMO_ACCESO,FECHA_ULTIMO_CAMBIO_CLAVE) values (21,'dgticexterno.48','937ac0771d47f08eb2f34141f2303a4b','dgticexterno.48@ift.org.com',to_timestamp('31/05/18 16:04:59.277000000','DD/MM/RR HH24:MI:SSXFF'),1,1,1,1,0,to_timestamp('31/05/09 16:08:37.199000000','DD/MM/RR HH24:MI:SSXFF'),'dgticexterno.48','dgticexterno.48','AqZnXdPjGVeaYwsmo00AbFWZe3BNBoTtcOEJBanWuZibseGEbs',to_timestamp('31/05/09 16:04:59.277000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('31/05/18 16:08:37.199000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('31/05/18 16:04:59.277000000','DD/MM/RR HH24:MI:SSXFF'));
Insert into  USUARIO (USUARIO_PK,USUARIO,CLAVE,CORREO,FECHA_CREACION,CUENTA_NO_EXPIRADA,CUENTA_NO_BLOQUEADA,CREDENCIAL_NO_EXPIRADA,HABILITADO,CONTADOR_INTENTOS_FALLIDOS,INSTANTE_DE_BLOQUEO,PREGUNTA_SECRETA,RESPUESTA_SECRETA,ID_DE_SEGURIDAD,VENTANA_PARA_ID_SEGURIDAD,FECHA_ULTIMO_ACCESO,FECHA_ULTIMO_CAMBIO_CLAVE) values (41,'capturista','c128b8c6a29fd487f306874a3d55aff5','capturista@mail.com',to_timestamp('12/06/18 17:36:26.751000000','DD/MM/RR HH24:MI:SSXFF'),1,1,1,1,0,to_timestamp('12/06/09 18:22:04.121000000','DD/MM/RR HH24:MI:SSXFF'),'perfil','capturista','GCcgfmmMSF49cbTdFkESxjX8KC27nzBkYj9jVcngmhbTo7NHcI',to_timestamp('12/06/09 17:36:26.751000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('12/06/18 18:22:04.121000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('12/06/18 18:09:29.905000000','DD/MM/RR HH24:MI:SSXFF'));

-------/////////////////////////////////////////////////////////////////////////////////////////////////
Insert into  USUARIO_DETALLE (USUARIO_FK,NOMBRE,AP_PATERNO,AP_MATERNO,TELEFONOS,DIRECCION,MANDA_CORREO_PROMO) values (1,'Administrador','Paterno','Materno','1234567890','Confidencial',0);
Insert into  USUARIO_DETALLE (USUARIO_FK,NOMBRE,AP_PATERNO,AP_MATERNO,TELEFONOS,DIRECCION,MANDA_CORREO_PROMO) values (45,'Publicador','Perfil','Publicador','NA','NA',0);
Insert into  USUARIO_DETALLE (USUARIO_FK,NOMBRE,AP_PATERNO,AP_MATERNO,TELEFONOS,DIRECCION,MANDA_CORREO_PROMO) values (44,'Validador','Perfil','Validador','5554535214','IFT Nápoles',0);
Insert into  USUARIO_DETALLE (USUARIO_FK,NOMBRE,AP_PATERNO,AP_MATERNO,TELEFONOS,DIRECCION,MANDA_CORREO_PROMO) values (21,'dgticexterno.48','dgticexterno.48',null,'0','ift',0);
Insert into  USUARIO_DETALLE (USUARIO_FK,NOMBRE,AP_PATERNO,AP_MATERNO,TELEFONOS,DIRECCION,MANDA_CORREO_PROMO) values (41,'Capturista','Cargas','Masivas','NA','NA',0);

-------/////////////////////////////////////////////////////////////////////////////////////////////////
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (1,1);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (1,2);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (21,1);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (21,2);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (21,3);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (21,4);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (41,1);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (41,3);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (44,1);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (44,4);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (45,1);
Insert into  USUARIO_PERFIL (USUARIO_FK,PERFIL_FK) values (45,5);

Insert into CCS_CAT_EJERCICIOS (ID_EJERCICIO,EJERCICIO,VIGENTE) values (1,2018,1);
Insert into CCS_CAT_EJERCICIOS (ID_EJERCICIO,EJERCICIO,VIGENTE) values (2019,2019,1);
Insert into CCS_CAT_EJERCICIOS (ID_EJERCICIO,EJERCICIO,VIGENTE) values (3,2017,1);

commit;

-------/////////////////////////////////////////////////////////////////////////////////////////////////
--------------------------------------------------------
--  DDL for View PERFILES_POR_USUARIO
--------------------------------------------------------

  CREATE OR REPLACE FORCE EDITIONABLE VIEW "PERFILES_POR_USUARIO" ("USUARIO_PK", "USUARIO", "PERFIL_PK", "CODIGO_PERFIL") AS 
  SELECT 
    usuario.usuario_pk AS usuario_pk,
		usuario.usuario AS usuario,
		perfil.perfil_pk AS perfil_pk,
		perfil.codigo_perfil AS codigo_perfil
FROM 
    usuario
		JOIN usuario_perfil
			ON usuario.usuario_pk = usuario_perfil.usuario_fk
		JOIN perfil
			ON usuario_perfil.perfil_fk = perfil.perfil_pk;
      
-------/////////////////////////////////////////////////////////////////////////////////////////////////

create or replace FUNCTION FN_DA_OBTEN_EJERCICIOS(P_VIGENTE Integer)
RETURN SYS_REFCURSOR                                       

IS
  /******************************************************************************
  NAME:     FN_DA_OBTEN_EJERCICIOS
  PURPOSE:  Función para obtener los ejercicios fiscales
  DATE:     2018/05/18
  ******************************************************************************/

V_CURSOR       SYS_REFCURSOR; 
ERR_CODE       INTEGER;
ERR_MSG        VARCHAR2(250);
VALIDARGTOS INTEGER;

BEGIN
  OPEN V_CURSOR FOR
    Select ID_EJERCICIO,EJERCICIO,VIGENTE from CCS_CAT_EJERCICIOS where VIGENTE=P_VIGENTE ORDER BY EJERCICIO;    
  RETURN V_CURSOR;


  EXCEPTION
    WHEN NO_DATA_FOUND THEN  
      RETURN NULL;
    WHEN OTHERS THEN  
     err_code := SQLCODE;
     err_msg := SUBSTR(SQLERRM, 1, 200);
     DBMS_OUTPUT.put_line ('Error:'||err_code||','||err_msg);
     RETURN NULL;
END;

