# CLOUD-BOX

CLOUD-BOX is a Spring Boot REST API designed for secure cloud storage of personal photos and videos, enabling users to access their files from anywhere. The project leverages HTTP requests to manage user interactions and file operations, with a MySQL database hosted on Railway for efficient data storage.

## Features

- **User Management**:
    - **Signup**: Register a new user account.
    - **Login**: Authenticate and access the system.
    - **Profile Update**: Modify user profile details.
    - **Logout**: Securely end user sessions.

- **File Operations**:
    - **File Upload**: Upload photos or videos to the cloud.
    - **File Read**: Retrieve and view stored files.
    - **File Delete**: Remove files from the cloud storage using a file ID.

- **Dashboard**: Centralized interface to manage files and user settings.

## Tech Stack

- **Backend**: Spring Boot (Java)
- **Database**: MySQL (Hosted on Railway)
- **API**: RESTful endpoints using HTTP requests
- **Deployment**: Configured for cloud deployment

## Endpoints

| Endpoint                | Method | Description                     | Parameters                     |
|-------------------------|--------|---------------------------------|--------------------------------|
| `/signup`               | POST   | Register a new user            | None                           |
| `/login`                | POST   | Authenticate a user            | None                           |
| `/dashboard`            | GET    | View user dashboard            | None                           |
| `/profileUpdate`        | PUT    | Update user profile            | None                           |
| `/file/upload`          | POST   | Upload a file to the cloud     | None                           |
| `/file/read//{fileId}`  | GET    | Retrieve a file from the cloud | `fileId` (Path parameter)      |
| `/file/delete/{fileId}` | DELETE | Delete a file from the cloud   | `fileId` (Path parameter)      |
| `/logout`               | POST   | Log out the user               | None                           |

## Getting Started

### Prerequisites

- Java 17 or higher (17 preferred)
- Gradle
- MySQL database (Railway-hosted or local)
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation

1. **Clone the repository**:
   ```bash
   https://github.com/phanison898/spring-boot-rest-api.git
   ```
2. **Navigate to the project directory**:
   ```bash
    cd spring-boot-rest-api
    ```
3. **Open the project in your IDE**.
4. **Create .env file and add the below properties**:
   ```
   DB_URL=jdbc:mysql://<your-database-url>:<your-database-port>/<your-database-name>
   DB_USERNAME=<your-database-username>
   DB_PASSWORD=<your-database-password>
   ```
5. **Run the application**:
   ```bash
    .\gradlew bootRun
   ```
6. **Access the API**:
   - Use tools like Postman or cURL to interact with the API endpoints.
   - Example for signup:
     ```bash
     curl -X POST http://localhost:8080/signup 
          -d '{"username":"testuser", "password":"Testp@s5", "email":"testuser@gmail.com"}'
          -H "Content-Type: application/json"
     ```