# Aplicación de Mensajes Programados con Spring Boot

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-2.7.0-green" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Java-17-blue" alt="Java">
</p>

---

## Introducción

Esta es una aplicación backend desarrollada en **Spring Boot** que permite programar mensajes para ser enviados automáticamente en una fecha y hora específicas.

---

## Funcionalidades

- **Programar mensajes**: Los usuarios pueden programar mensajes con un contenido y una fecha/hora específicos.
- **Listar mensajes**: Ver todos los mensajes programados, incluyendo su estado (pendiente o enviado).
- **Eliminar mensajes**: Eliminar mensajes programados que ya no sean necesarios.

---

## Tecnologías Utilizadas

- **Spring Boot**: Framework para desarrollar aplicaciones Java.
- **Spring Data JPA**: Para interactuar con la base de datos.
- **H2 Database**: Base de datos en memoria (puede cambiarse por MySQL, PostgreSQL, etc.).
- **Thymeleaf**: Motor de plantillas para la interfaz web.
- **Bootstrap**: Para el diseño de la interfaz web.

---

## Configuración

### Requisitos

- Java 17 o superior.
- Maven

### Pasos para Configurar

1. **Clona el repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/tu-repositorio.git
   ```

2. **Configura el archivo `application.properties`**:

   Abre el archivo `src/main/resources/application.properties` y establece las siguientes propiedades:

   ```properties
   # Configuración de la base de datos (H2 en memoria)
   spring.datasource.url=jdbc:h2:mem:testdb
   spring.datasource.driverClassName=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=password
   spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
   spring.h2.console.enabled=true
   ```

---

## Ejecución

1. **Compila el proyecto**:

   ```bash
   mvn clean install
   ```

2. **Ejecuta la aplicación**:

   ```bash
   mvn spring-boot:run
   ```

3. **Accede a la aplicación**:

   Abre tu navegador y ve a [http://localhost:8080](http://localhost:8080).

---

## Uso

### Interfaz Web

#### Programar un mensaje

1. Ingresa el contenido del mensaje y selecciona la fecha/hora programada.
2. Haz clic en **"Programar Mensaje"**.

#### Ver mensajes programados

- La tabla muestra todos los mensajes programados, la fecha/hora de envío, su estado y el boton de eliminar.

#### Eliminar un mensaje

- Has click en el boton y confirma si deseas eliminar el **"Mensaje Programado"**.

---

## API Rest

### Programar un mensaje

1. Utiliza un programa como **Postman**.
2. Ingresa la URL `http://localhost:8080/message` con el método **POST**.
3. En la sección **Body**, selecciona `raw` y el formato `JSON`.
4. Introduce el siguiente contenido:
   
   ```json
   {
     "message_content": "Esto es un mensaje programado",
     "date": "2025-02-15T22:00:00"
   }
   ```
   
5. Haz clic en **Send** para programar el mensaje.

### Ver mensajes programados

- Ingresa a la URL `http://localhost:8080/messages` con el método **GET** para obtener todos los mensajes programados.

### Eliminar un mensaje

- Ingresa a la URL `http://localhost:8080/messages/{id}` con el método **DELETE**, donde `{id}` es el identificador del mensaje que deseas eliminar.

---

## Estructura del Proyecto

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── message/
│   │           ├── componenet/
│   │           ├── controller/
│   │           ├── entities/
│   │           ├── repositories/
│   │           ├── service/
│   │               └──  impl/
│   │           └── MessageApplication.java
│   ├── resources/
│   │   ├── static/
│   │   ├── templates/
│   │   └── application.properties
└── test/
```

---

## Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. **Haz un fork del repositorio**.
2. **Crea una rama para tu contribución**:

   ```bash
   git checkout -b mi-contribucion
   ```

3. **Realiza tus cambios y haz commit**:

   ```bash
   git commit -m "Descripción de los cambios"
   ```

4. **Envía un pull request**.

---

## Licencia

Este proyecto está bajo la licencia MIT. Para más detalles, consulta el archivo LICENSE.
