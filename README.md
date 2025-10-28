Proyecto esqueleto Spring Boot con la estructura deseada.

Instrucciones rápidas (PowerShell):

# Construir
./mvnw.cmd clean package

# Ejecutar (usa variables de entorno para Neon/Postgres)
$env:DB_URL = 'jdbc:postgresql://<HOST>:5432/<DB_NAME>'
$env:DB_USER = '<DB_USER>'
$env:DB_PASSWORD = '<DB_PASSWORD>'
./mvnw.cmd spring-boot:run

Notas sobre Neon/Postgres:
- Neon te dará una URL de conexión y credenciales; reemplaza los valores arriba.
- Si tu Neon requiere SSL, añade `?sslmode=require` a la `DB_URL` o configura el datasource.

Endpoints de ejemplo:
GET /api/hello
POST /api/people
