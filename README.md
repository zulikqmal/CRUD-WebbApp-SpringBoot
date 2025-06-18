# CRUD (Create, Read, Update, and Delete) for User Web Application

This project is a Spring Boot web application designed for managing user information. It provides functionalities for adding, listing, editing, and deleting user records, along with an automatic lookup for town names based on postcodes. The application uses Spring Data JPA for database interactions, Thymeleaf for server-side rendering of web pages, and MySQL as the database.

## Features

* **User Management**: Perform CRUD (Create, Read, Update, Delete) operations on user records.
* **Automatic Date of Birth from IC Number**: Automatically populates the Date of Birth field based on the provided IC Number format (e.g., `YYMMDD-XX-XXXX`).
* **Postcode to Town Lookup**: Integrates with a postcode mapping to automatically suggest the town name when a postcode is entered.
* **Database Integration**: Uses Spring Data JPA with MySQL for persistent storage of user and postcode data.
* **Thymeleaf Templates**: Dynamic web pages are rendered using Thymeleaf, providing a clean separation of concerns.
* **Form Validation**: Basic client-side validation for the IC Number format is included.
* **Bootstrap Styling**: Utilizes Bootstrap for a responsive and modern user interface.

## Technologies Used

* **Spring Boot**: 3.5.0
* **Java**: 24
* **Spring Data JPA**: For database interaction
* **Thymeleaf**: For server-side templating
* **Spring Web**: For building web applications
* **MySQL Connector/J**: JDBC driver for MySQL database
* **Lombok**: (Implicit, often used with Spring Boot for boilerplate reduction, though not explicitly listed in pom.xml, it's a common practice)
* **Bootstrap**: 5.3.6
* **Webjars**: For managing client-side dependencies like Bootstrap

## Getting Started

### Prerequisites

* Java Development Kit (JDK) 24
* Maven
* MySQL Database

### Database Setup

1.  Create a database named `userdb` in your MySQL instance.
2.  Run the `scheme.sql` script to create the `user` and `postcode_mapping` tables and insert initial postcode data.
3.  Update the `application.properties` file with your MySQL username and password if they are different from `your_db_username` and `your_db_password#` respectively.

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
    ```

### Building and Running the Application

1.  Clone the repository:
    ```bash
    git clone <repository_url>
    cd WebApp
    ```
2.  Build the project using Maven:
    ```bash
    mvn clean install
    ```
3.  Run the application:
    ```bash
    mvn spring-boot:run
    ```

The application will start on port 8080 by default. You can access it in your browser at `http://localhost:8080`.

## Class Diagram

![Class Diagram](C:\Users\User\Lonpac-Test\WebApp\.idea\classDiagram.png)

## Project Structure

* `src/main/java/com/LonpacInsurance/WebAppApplication.java`: The main Spring Boot application entry point.
* `src/main/java/com/LonpacInsurance/core/User.java`: The JPA entity for the `User` table.
* `src/main/java/com/LonpacInsurance/core/Postcode.java`: The JPA entity for the `Postcode_Mapping` table.
* `src/main/java/com/LonpacInsurance/repository/UserRepository.java`: Spring Data JPA repository for `User` entity.
* `src/main/java/com/LonpacInsurance/repository/PostcodeRepository.java`: Spring Data JPA repository for `Postcode` entity.
* `src/main/java/com/LonpacInsurance/service/UserService.java`: Service layer for user-related business logic.
* `src/main/java/com/LonpacInsurance/service/PostcodeService.java`: Service layer for postcode-related business logic.
* `src/main/java/com/LonpacInsurance/controller/MainController.java`: Controller for handling web requests related to user management.
* `src/main/java/com/LonpacInsurance/controller/LocationRestController.java`: REST controller for handling postcode lookup API requests.
* `src/main/java/com/LonpacInsurance/UserNotFoundException.java`: Custom exception for user not found scenarios.
* `src/main/resources/application.properties`: Application configuration, including database connection details.
* `src/main/resources/templates/`: Contains Thymeleaf HTML templates (e.g., `index.html`, `users.html`, `addUser.html`).
* `src/main/resources/schema.sql`: SQL script for database schema creation and initial data.
* `src/test/java/com/LonpacInsurance/UserRepositoryTests.java`: JUnit tests for the `UserRepository`.
* `pom.xml`: Maven project object model file, defining dependencies and build process.

## Usage

1.  Navigate to `http://localhost:8080` in your web browser.
2.  Click on "Manage User" to go to the user listing page.
3.  From the user listing page, you can:
    * Add a new user by clicking "Add New User".
    * Edit an existing user by clicking the "Edit" icon next to their entry.
    * Delete a user by clicking the "Delete" icon.
4.  When adding or editing a user, enter the IC Number, and the Date of Birth will be automatically calculated.
5.  Enter the Postcode, and the Town field will be automatically populated if a match is found in the database.

## Reference
: <img src="https://img.icons8.com/color/12/000000/youtube-play.png"/> [https://youtu.be/u8a25mQcMOI?si=mxXX8gl2GJJFCEQV](https://youtu.be/u8a25mQcMOI?si=mxXX8gl2GJJFCEQV)