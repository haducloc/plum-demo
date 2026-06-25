# AppsLandia Plum Demo

## Features
- Java MVC architecture
- Full CRUD operations
- Authentication (login, logout, and "Remember Me")
- Authorization and CSRF protection
- Internationalization (i18n) and multi-language support
- Externalized messages and configuration
- HTTP optimizations (security headers, compression, ETag, asynchronous processing, etc.)
- Reusable layout templates for JSP and Facelets
- Rich UI form controls
- Custom JSP/JSTL and Facelets tags/functions
- Paging and sorting components

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

## Test Facelet Views

1. In `config.properties`, change `config.view_suffixes` from `.jsp,.xhtml` to `.xhtml,.jsp`.
2. Start the application as above.
