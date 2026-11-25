# NEXA - Microservicio de Acceso e Identificación

## Requisitos
- Java 25 (LTS)
- Maven 3.9+
- MySQL 8
- Visual Studio Code + Extension Pack for Java + Spring Boot Extension Pack

## Base de datos
Ejecuta `src/main/resources/schema.sql` en MySQL Workbench. El script crea la base de datos y las tablas.
Nota: Hibernate también puede crear/actualizar las tablas al iniciar (`ddl-auto=update`).

## Configuración
Edita `src/main/resources/application.properties` o define variables de entorno:
- DB_URL
- DB_USERNAME
- DB_PASSWORD
- JWT_SECRET (mínimo 32 caracteres; en producción usa un secreto aleatorio robusto)
- ADMIN_USERNAME
- ADMIN_EMAIL
- ADMIN_PASSWORD

## Ejecutar
Desde la carpeta del proyecto:
```bash
mvn clean spring-boot:run
```
API: http://localhost:8081

## Endpoints principales
- POST `/api/auth/login` (público)
- POST `/api/auth/logout` (autenticado)
- GET `/api/auth/yo` (autenticado)
- POST `/api/cuentas` (solo ADMINISTRADOR)
- GET `/api/cuentas` (solo ADMINISTRADOR)
- GET `/api/sesiones/mias` (autenticado)
- POST `/api/solicitudes-restablecimiento` (público)
- GET `/api/solicitudes-restablecimiento` (solo ADMINISTRADOR)
- PATCH `/api/solicitudes-restablecimiento/{id}` (solo ADMINISTRADOR)

## Importante
El administrador inicial se crea automáticamente si no existe. Cambia la contraseña de desarrollo antes de desplegar.
El flujo de restablecimiento aquí registra y gestiona la solicitud; el cambio efectivo de contraseña debe ejecutarse por un proceso administrativo seguro aún no implementado.
El token JWT es válido durante el tiempo configurado y además requiere que la sesión siga abierta en la base de datos.
