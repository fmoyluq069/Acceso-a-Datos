-- Crear la base de datos "paises" si aún no existe
CREATE DATABASE IF NOT EXISTS paises;

-- Usar la base de datos "paises"
USE paises;

-- Crear la tabla "paises"
CREATE TABLE IF NOT EXISTS paises (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ISO3 VARCHAR(255) NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    CAPITAL VARCHAR(255) NOT NULL,	
);


