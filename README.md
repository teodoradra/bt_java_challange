# bt_java_challange

This application serves as a client enrollment system for a banking service. It allows frontdesk employees to input client data, perform client checks, generate enrollment or denial documents, and manage document signing.

OpenAPI documentation can be found when the application is running at:
http://localhost:8080/swagger-ui/index.html

http://localhost:8081/swagger-ui/index.html

http://localhost:8082/swagger-ui/index.html


## How to run the application
1. `docker build -t existence .`
2. `docker build -t reputation .`
3. `docker build -t enrolment .`
4. `docker compose up`


## Usage

The application exposes various endpoints to perform client enrollment processes:

API Endpoints
POST `/enrolment/client-check`: Perform a client check operation.
POST `/enrolment/submit`: Input client data and trigger the enrollment process.
POST `/enrolment/generate`: Retrieve the generated document (enrollment/denial) by ID.
POST `/client/existence`: Checks if a client exists in the banking system
POST `/client/reputation`: Checks if a client has a good enough reputation to create an account
