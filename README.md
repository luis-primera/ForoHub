# 🧠 ForoHub API

API RESTful desarrollada con **Spring Boot 3** para la gestión segura de tópicos de un foro técnico.  
Incluye **autenticación JWT**, control de roles, validación de duplicados y documentación interactiva con **Swagger UI**.  
Ideal para entornos educativos y empresariales donde la seguridad y claridad son prioridad.

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

## 📘 Documentación interactiva (Swagger UI)

- Acceso: `/swagger-ui/index.html`
- Permite login directo desde Swagger
- Prueba endpoints protegidos usando token JWT
- Muestra modelos de datos, parámetros y ejemplos de respuesta

---

## 🧩 Endpoints disponibles

| Endpoint | Método | Seguridad | Descripción |
|----------|--------|-----------|-------------|
| /auth/login | POST | Público | Autenticación del usuario |
| /topicos | GET | Protegido | Listado de tópicos |
| /topicos | POST | Protegido | Crear nuevo tópico |
| /topicos/{id} | GET | Protegido | Obtener tópico específico |
| /topicos | PUT | Protegido | Actualizar tópico |
| /topicos/{id} | DELETE | Protegido | Eliminar tópico |
| /respuestas/{id} | POST | Protegido | Responder a un tópico |
| /cursos | GET | Protegido | Listar cursos |
| /cursos | POST | Protegido | Crear curso |

> Todos los endpoints protegidos requieren **JWT válido** y perfil autorizado.

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
- **Spring Security + JWT**
- **JPA + Hibernate**
- **MySQL**
- **Lombok**
- **Jackson** (JSON)
- **Swagger/OpenAPI**
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

## 📸 Ejemplos de request/response

### Crear tópico (POST `/topicos`)
**Request:**
```json
{
  "titulo": "Error en JavaScript",
  "mensaje": "No se ejecutan archivos .js",
  "autor_id": 1,
  "curso_id": 1,
  "status": "abierto"
}

{
  "mensaje": "Tópico creado correctamente",
  "detalle": {
    "id": 4,
    "titulo": "Error en JavaScript",
    "mensaje": "No se ejecutan archivos .js",
    "fecha_creacion": "2025-08-16T21:10:00"
  }
}

🗂️ Estructura del Proyecto
Carpeta / Paquete	Descripción
controller	Controladores REST (TopicoController, AutenticacionController)
domain	Entidades del modelo de negocio (Curso, Topico, Usuario, Respuesta)
dto	Objetos de transferencia de datos (DatosRegistroTopico, DatosListaTopicos)
repository	Interfaces JPA para CRUD sobre entidades
infra	Configuración de seguridad, filtros JWT, manejo de errores, Swagger
resources	Archivos de configuración (application.properties)
test	Pruebas unitarias e integración
🎯 Conclusión

ForoHub es una API segura, documentada y lista para producción, que permite:

Gestión completa de usuarios, cursos, tópicos y respuestas

Control de acceso mediante JWT y roles

Validación de duplicados y manejo de errores consistente

Documentación interactiva con Swagger UI

Este proyecto aplica buenas prácticas REST, diseño de backend robusto y seguridad avanzada en Spring Boot 3.