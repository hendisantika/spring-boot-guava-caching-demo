# Spring Boot Guava Cache Demo

[![Java CI with Maven](https://github.com/hendisantika/spring-boot-guava-caching-demo/actions/workflows/maven.yml/badge.svg)](https://github.com/hendisantika/spring-boot-guava-caching-demo/actions/workflows/maven.yml)
![Java](https://img.shields.io/badge/Java-25-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.2-6DB33F?logo=springboot)
![Guava](https://img.shields.io/badge/Guava-33.8.0--jre-blue)

A small demo application showing how to use [Google Guava](https://github.com/google/guava) caches as the
backing store for Spring's cache abstraction (`@Cacheable`, `@CachePut`).

Spring Framework removed its built-in `GuavaCache` in 5.0, so this project ships its own lightweight adapter
([`GuavaCache`](src/main/java/com/hendisantika/guava/cachingdemo/config/GuavaCache.java)) and registers the caches
through a `SimpleCacheManager` in [`CacheConfig`](src/main/java/com/hendisantika/guava/cachingdemo/config/CacheConfig.java).

## Tech Stack

| Component       | Version      |
|-----------------|--------------|
| Java (JDK)      | 25           |
| Spring Boot     | 4.1.2        |
| Google Guava    | 33.8.0-jre   |
| Database        | H2 (in-memory) |
| Build tool      | Maven 3.9.x (via Maven Wrapper) |
| Tests           | JUnit 5, Mockito, AssertJ |

## Caches

| Cache         | Used by                                   | Policy                                                        |
|---------------|-------------------------------------------|---------------------------------------------------------------|
| `postcode`    | `PostcodeService#getPostcode`             | max size `cache.postcode.maximum.size` (1000), expire 1 day after access, caches `null` results |
| `temperature` | `TemperatureService#getTemperatureForCoordinate` | expire `cache.expire.temperature.seconds` (15s) after write |
| `shares`      | `SharesService#getValue` / `setNewSharePrice` | no eviction, updated with `@CachePut`                     |

## Requirements

- JDK 25 (e.g. [Eclipse Temurin](https://adoptium.net/))
- No local Maven needed — use the bundled `./mvnw`

## Build & Run

```bash
# build and run all tests
./mvnw clean verify

# run the application
./mvnw spring-boot:run

# or run the packaged jar
java -jar target/caching-demo-0.0.1-SNAPSHOT.jar
```

The application starts on port `8080` and seeds an in-memory H2 database with Dutch postcodes
(see [`import.sql`](src/main/resources/import.sql)).

## API

| Method | Endpoint                    | Description                                                                         |
|--------|-----------------------------|-------------------------------------------------------------------------------------|
| GET    | `/`                         | Simple greeting with the current date                                               |
| GET    | `/temperature/{postcode}`   | Temperature for a valid Dutch postcode (4 digits + 2 letters), e.g. `1000AA`        |
| GET    | `/share/{share}`            | Share price for one of `AKZO`, `SHELL`, `ASML`, `UNILEVER`, `ING` (see `Shares` enum) |

Examples:

```bash
curl http://localhost:8080/temperature/1000AA
curl http://localhost:8080/share/ASML
```

Call the same endpoint repeatedly and watch the logs: only the first call hits the "database" / "weather station" /
"stock exchange", subsequent calls are served from the Guava cache until the entry expires.

## CI & Dependency Updates

- **GitHub Actions** ([`maven.yml`](.github/workflows/maven.yml)) builds and tests the project on Temurin JDK 25 for every
  push and pull request to `master`, and submits the Maven dependency graph on pushes to `master`.
- **Dependabot** ([`dependabot.yml`](.github/dependabot.yml)) checks Maven dependencies and GitHub Actions weekly
  (Monday 05:00 Asia/Jakarta), grouping related updates (Spring Boot, testing libraries, Guava, Actions) into fewer PRs.

## Author

**Hendi Santika** — [s.id/hendisantika](https://s.id/hendisantika) · Telegram [@hendisantika34](https://t.me/hendisantika34)
