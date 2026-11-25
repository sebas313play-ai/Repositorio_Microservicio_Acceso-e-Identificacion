CREATE DATABASE IF NOT EXISTS nexa_acceso CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE nexa_acceso;

CREATE TABLE IF NOT EXISTS cuenta (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 nombre_usuario VARCHAR(60) NOT NULL UNIQUE,
 correo_electronico VARCHAR(120) NOT NULL UNIQUE,
 password_hash VARCHAR(100) NOT NULL,
 rol ENUM('ADMINISTRADOR','PROPIETARIO','GUARDA') NOT NULL,
 propietario_id BIGINT NULL,
 activa BOOLEAN NOT NULL DEFAULT TRUE,
 fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS sesion (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 cuenta_id BIGINT NOT NULL,
 fecha_inicio DATETIME NOT NULL,
 fecha_cierre DATETIME NULL,
 ip_acceso VARCHAR(45),
 dispositivo VARCHAR(255),
 CONSTRAINT fk_sesion_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);

CREATE TABLE IF NOT EXISTS solicitud_restablecimiento (
 id BIGINT AUTO_INCREMENT PRIMARY KEY,
 cuenta_id BIGINT NULL,
 solicitante VARCHAR(150) NOT NULL,
 fecha_solicitud DATETIME NOT NULL,
 resultado VARCHAR(30) NOT NULL,
 observaciones TEXT,
 CONSTRAINT fk_solicitud_cuenta FOREIGN KEY (cuenta_id) REFERENCES cuenta(id)
);
