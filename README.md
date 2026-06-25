# AppsLandia Plum Demo

## Features
- Java MVC Architecture
- CRUD operations
- Login, logout, and "Remember Me" support
- Authorization
- CSRF protection
- UI Form controls
- JSP/JSTL or Facelets tags/functions
- Main layout templates (JSP and Facelets)
- Multi languages
- Externalized messages
- Configure HTTP headers, Compression, ETag, ASYNC, etc.

## Prerequisites

- Java 21 or later installed  
- Maven installed

## How to Run

1. Clone the project:  
   `git clone https://github.com/haducloc/plum-demo.git`

2. Start the application:  
   `mvn clean install payara-micro:start`

3. Open your browser and navigate to:  
   [http://localhost:8080/plum-demo](http://localhost:8080/plum-demo)

4. Credentials:  
   `admin:password`

5. To stop Payara, press:  
   `Ctrl+C`

## Facelet Views

1. Set `config.view_suffixes=xhtml,.jsp` in `config.properties`.
2. Start the application as above.
