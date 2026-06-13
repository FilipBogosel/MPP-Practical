# my-quarkus-app

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/my-quarkus-app-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Architecture and Database Design

The backend is structured with a layered architecture:
- **Entities/Models (`com.example.entity`)**: Defines Java classes with JPA annotations that map directly to database tables.
- **Repositories (`com.example.repository`)**: Exposes database operations using Panache Repository Pattern.
- **Services (`com.example.service`)**: Encapsulates business logic and manages transactions.
- **Resources (`com.example.resource`)**: Defines JAX-RS REST Endpoints (`/api/articles`).

### Database Model

1. **`Author` Entity**:
   - Maps to the `authors` table.
   - Localized roles (`role` field) are stored in an auxiliary table `author_roles` using `@ElementCollection`.

2. **`ArticleTag` Entity**:
   - Maps to the `article_tags` table.
   - Stores the localized tag name and its language locale.

3. **`Article` Entity**:
   - Maps to the `articles` table.
   - Localized fields (`title`, `summary`) are stored in `article_titles` and `article_summaries` tables using `@ElementCollection`.
   - Has a `@ManyToOne` relationship to `Author`.
   - Has a `@OneToMany` relationship to `ArticleTag`.
   - Custom serialization logic is added via `@JsonProperty("tags")` to dynamically group tags by locale, presenting the exact JSON format expected by the frontend.

### Data Initialization
On startup, `DataInitializer` checks if the database is empty. If it is, it populates it programmatically with the six mock articles matching the ones in the frontend. Because H2 is configured to `drop-and-create` the schema on startup, the data is automatically seeded every time the application is started in development mode.

---

## How to Switch to PostgreSQL (Production/Real DB Setup)

When you are ready to use a persistent PostgreSQL database:

1. **Update `pom.xml`**:
   Replace the H2 JDBC driver dependency:
   ```xml
   <dependency>
       <groupId>io.quarkus</groupId>
       <artifactId>quarkus-jdbc-h2</artifactId>
   </dependency>
   ```
   With the PostgreSQL JDBC driver dependency:
   ```xml
   <dependency>
       <groupId>io.quarkus</groupId>
       <artifactId>quarkus-jdbc-postgresql</artifactId>
   </dependency>
   ```

2. **Update `src/main/resources/application.properties`**:
   Change the datasource kind, JDBC URL, username, password, and Hibernate generation strategy:
   ```properties
   # Enable CORS for frontend requests
   quarkus.http.cors=true
   quarkus.http.cors.origins=http://localhost:5173,http://localhost:3000

   # Database Configuration (PostgreSQL)
   quarkus.datasource.db-kind=postgresql
   quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/your_database_name
   quarkus.datasource.username=your_username
   quarkus.datasource.password=your_password

   # Hibernate Configuration
   # Use 'update' in production so that it updates the schema without destroying data,
   # or 'none' if you manage migrations with Liquibase or Flyway.
   quarkus.hibernate-orm.database.generation=update
   quarkus.hibernate-orm.log.sql=true
   ```

