# AppsLandia Plum Demo

## Features

- CRUD operations  
- JPA/JTA integration  
- Login, Logout, and "Remember Me" support  
- Authorization  
- CSRF protection  
- HTTP security headers  
- Externalized messages  
- Custom JSTL and Facelet tags/functions  
- Main layout templates (JSP & Facelet)

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

1. Set `config.view_suffixes=xhtml,.jsp,.peb` in `config.properties`.
2. Start the application as above.
