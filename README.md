# 🧠 ForoHub API

API RESTful desarrollada con **Spring Boot 3** para la gestión segura de tópicos de un foro técnico.  
Incluye **autenticación JWT**, control de roles, validación de duplicados.

---

## 🔐 Autenticación y Seguridad con JWT

El sistema implementa un flujo completo de autenticación:

### 🔄 Flujo de autenticación
1. **Login (POST `/auth/login`)**: El usuario envía correo electrónico y contraseña.
2. **Generación de JWT**: Si las credenciales son correctas, se devuelve un token firmado.
3. **Acceso a recursos protegidos**: El token debe incluirse en cada solicitud:
4. **Control por rol**: Algunos endpoints requieren roles específicos (`usuario`, `admin`).

### ⚠️ Validaciones automáticas
- Token expirado o inválido → `401 Unauthorized`
- Acceso sin permisos → `403 Forbidden`
- Token válido → acceso a crear, listar y responder tópicos

---

---

---

## 🧪 Pruebas recomendadas

- ✅ Login exitoso con credenciales válidas
- 🚫 Acceso denegado sin token o con token inválido
- 🔄 Token expirado y comportamiento frente al refresh
- 🧾 Validación de roles accediendo a recursos restringidos
- 🐞 Pruebas desde Swagger UI o Insomnia simulando usuarios reales

---

## 📦 Tecnologías utilizadas

- **Java 17 + Spring Boot 3**
- **Spring Security**
- **JPA + Hibernate**
- **MySQL**
- **Lombok**
- **Jackson** (JSON)
- **Maven** como gestor de dependencias

---

## 🗄️ Base de Datos

- Motor: **MySQL Server**
- Persistencia: **JPA/Hibernate**
- Integridad referencial y validación por esquema SQL
- Migraciones controladas manualmente o con **Flyway** (opcional)
- Conexión configurada vía `application.properties`

### 🗂️ Tablas principales
- **Usuario**: id, nombre, correo_electronico, contrasena
- **Perfil**: id, nombre
- **Curso**: id, nombre, categoria
- **Tópico**: id, titulo, mensaje, fecha_creacion, status, autor_id, curso_id
- **Respuesta**: id, mensaje, topico_id, fecha_creacion, autor_id, solucion

**Relaciones:**
- Usuario <-> Perfil: muchos a muchos
- Topico -> Usuario: muchos a uno
- Topico -> Curso: muchos a uno
- Respuesta -> Topico: muchos a uno

---

## 📸 Capturas del Proyecto

### 🔹 CRUD en ForoHub
Ejemplo de las operaciones **CRUD** funcionando en Insomnia.
![Crud ForoHub](ForoHub/imagenes/CRUD_ForoHub.png)

### 🔹 Estructura del Proyecto
Vista de la estructura en **IntelliJ IDEA**.
![Estructura ForoHub](ForoHub/imagenes/Estructura_ForoHub.png)

### 🔹 Aplicación corriendo
Ejecución de la aplicación con **Spring Boot**.
![Run ForoHub](ForoHub/imagenes/Run_ForoHub.png)


🎥 Videos

Aplicación Funcionando:1 https://youtu.be/vJvTLT7byrY
Aplicación Funcionando 2:https://youtu.be/dOQMBNx38Ls

🎯 Conclusión

ForoHub es una API segura, documentada y lista para producción, que permite:

Gestión completa de usuarios, cursos, tópicos y respuestas

Validación de duplicados y manejo de errores consistente

Este proyecto aplica buenas prácticas REST, diseño de backend robusto y seguridad avanzada en Spring Boot 3.