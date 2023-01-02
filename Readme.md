# 🚀 Spring Boot + PostgreSQL + Custom DAO Example

A clean and production-grade Spring Boot starter project integrating **PostgreSQL** with **Custom DAO logic**, using best practices like DTOs, `@Query`, `@RequestParam`, and more.

---

## 📂 Tech Stack

| Tech | Description |
|------|-------------|
| 🧠 Spring Boot | Java framework for rapid web application development |
| 🐘 PostgreSQL | Open-source relational database |
| 🛠️ Spring Data JPA | ORM abstraction for managing data persistence |
| 📡 RESTful APIs | API endpoints using `@RestController` |
| 🧾 Maven | Project build and dependency management |
| ❤️ Lombok | Boilerplate reduction via annotations |
| 🔐 JPA Custom DAO | Fine-grained control over data access |

---

## ⚙️ Project Structure

```
src
├── main
│   ├── java
│   │   └── com.example.postgresdemo
│   │       ├── controller
│   │       ├── entity
│   │       ├── repository
│   │       └── service
│   └── resources
│       └── application.properties
```

---

## 🔗 Database Configuration (`application.properties`)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

---

## 🧩 Entity Example

```java
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private int age;
}
```

---

## 📦 Repository with Custom Query

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.id > :low AND u.id < :high")
  List<User> fetchInRange(@Param("low") Long min, @Param("high") Long max);
}
```

---

## 🌐 REST API Controller

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<?> createUser(@RequestBody User user) {
    userRepository.save(user);
    return ResponseEntity.ok(Map.of(
      "message", "User created successfully",
      "status", true,
      "data", userRepository.findAll()
    ));
  }

  @GetMapping("/range")
  public ResponseEntity<?> getUsersInRange(@RequestParam Long min, @RequestParam Long max) {
    List<User> users = userRepository.fetchInRange(min, max);
    return ResponseEntity.ok(Map.of(
      "message", "Fetched successfully",
      "status", true,
      "data", users
    ));
  }
}
```

---

## ✅ How to Run

```bash
git clone https://github.com/yourusername/springboot-postgres-dao.git
cd springboot-postgres-dao
./mvnw spring-boot:run
```

Make sure PostgreSQL is running and credentials are correctly set in `application.properties`.

---

## 📚 Learning Highlights

- ✅ Spring Boot + PostgreSQL integration
- ✅ Custom DAO using `@Query`
- ✅ DTO usage for data shaping
- ✅ Dynamic query parameter handling with `@RequestParam`
- ✅ Clean API response structuring

---

## 🧠 Bonus Tips

- Use `@Param` for safe JPQL queries
- Use `@RequestParam` to handle optional filters
- Use DTOs for exposing only necessary data
- Structure API responses using `Map<String, Object>`

---

## ✨ Author

Made with ❤️ by [Darshan Boyat](https://github.com/darshanboyat)

---

## 📌 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
