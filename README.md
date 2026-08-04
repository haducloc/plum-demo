# AppsLandia Plum Demo

## Features

- Java MVC architecture
- Two modules (Website and API)
- Full CRUD operations
- Authentication (login, logout, JWT, and Remember Me)
- Authorization and CSRF protection
- Externalized messages and configuration
- HTTP optimizations (security headers, compression, ETag, asynchronous processing, etc.)
- Reusable layout templates for JSP and Facelets
- Rich UI form controls
- Custom JSP/JSTL and Facelets tags/functions
- Paging and sorting components

## Prerequisites

- Java 21 or later
- Maven

## Testing the Web Module

1. Clone the repository:

   `git clone https://github.com/haducloc/plum-demo.git`

2. Start the application:

   `mvn clean install payara-micro:start`

3. Open your browser:

   `http://localhost:8080/plum-demo`

4. Log in with:

   - Username: `admin`
   - Password: `password`

5. To stop Payara Micro, press `Ctrl+C`.

## Testing the API Module

1. Obtain a JWT & CURL command:

   `http://localhost:8080/plum-demo/api/login?userName=admin&password=password`

2. Call a protected API using the JWT:

   `curl -X GET "http://localhost:8080/plum-demo/en/api/users" -H "Authorization: Bearer {JWT}"`

## Testing Facelets Views

1. Open `config.properties`.

2. Change:

   `config.view_suffixes=.jsp,.xhtml`

   to:

   `config.view_suffixes=.xhtml,.jsp`

3. Start the application as described above.
