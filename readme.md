# 📚 Library - Library Management System

## Project Description
Library is a modern web application that enables efficient management of library resources. Users can browse available books, reserve and borrow them, while library staff can manage returns and approve reservations. The application emphasizes usability and security.

## Key Features
- 📖 **Browse the book catalog**
- 📅 **Book reservation and borrowing**
- 💼 **Admin panel for library staff**
- ⚖️ **Return management and reservation approval**
- 🔒 **Secure login and user role management**

## Technologies
- **Backend:** Spring Boot, Spring Data JPA, Spring Security, Flyway, PostgreSQL
- **Frontend:** Thymeleaf, HTML, CSS
- **Testing:** JUnit, Testcontainers
- **Tools:** MapStruct (automatic DTO mapping), Lombok

## Installation & Setup
1. **Clone the repository:**
   ```sh
   git clone https://github.com/krawczykMichal/Library
   cd library
   ```
2. **Ensure PostgreSQL is running and create a database named library_app:**
   ```sh
   createdb library_app
   ```
3. **Make sure you have application.yaml file configure**


4. **Run database migrations (Flyway will run automatically at application startup):**
   ```sh
   mvn flyway:migrate
   ```
5. **Start the application:**
   ```sh
   mvn spring-boot:run
   ```
6. **The application will be available at:** `http://localhost:8190/library-app`

## Sample Accounts
- **User:** `jKowalski` / `test`
- **Employee:** `jNowak` / `test`

## Future Enhancements
- 🛠️ **REST API for mobile application integration**
- 🌟 **Email notifications for returns**
- 🛍️ **Automated report generation**

## 📜 License
This project is licensed under the MIT License - see the [License](https://github.com/krawczykMichal/ToDoList/blob/master/MitLicense) file for details.

## Kontakt
I am open to new challenges and career opportunities!
If you have any questions or want to discuss collaboration, reach out to me via **[LinkedIn](https://www.linkedin.com/in/michał-krawczyk-26385b254/)** or **email: michalkrawczyk2499@gmail.com**.

---
📚 **Library** - Smart Library Management!

