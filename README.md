# API Automation Framework — Restful Booker

A Java-based API test automation framework built with RestAssured and TestNG, covering full CRUD operations on the [Restful-Booker API](https://restful-booker.herokuapp.com). Designed with scalability, reusability, and clean reporting in mind.

-----

## 🧰 Tech Stack

|Tool         |Purpose                        |
|-------------|-------------------------------|
|Java         |Core programming language      |
|RestAssured  |API testing and validation     |
|TestNG 7.9   |Test management and execution  |
|Maven        |Build and dependency management|
|Allure 2.29.1|Test reporting                 |
|AspectJ      |Allure-TestNG integration      |
|SLF4J        |Logging                        |

-----

## 📁 Project Structure

```
restful-booker/
├── src/
│   ├── main/java/
│   │   └── com.restbooker/
│   │       └── Main.java
│   └── test/java/
│       ├── test/
│       │   ├── BaseTest.java              # Suite setup, base URI, request logging
│       │   ├── AuthTest.java              # Token generation test
│       │   ├── CreateBookingTest.java     # POST /booking
│       │   ├── GetBookingTest.java        # GET /booking/{id}
│       │   ├── UpdateBookingTest.java     # PUT /booking/{id}
│       │   ├── UpdatePartialTest.java     # PATCH /booking/{id}
│       │   └── DeleteBookingTest.java     # DELETE /booking/{id}
│       └── utils/
│           └── AuthHelper.java            # Singleton token manager
├── src/test/resources/
│   ├── allure.properties                  # Allure configuration
│   ├── AuthFile.json                      # Auth request payload
│   ├── CreateUser.json                    # Create booking payload
│   ├── UpdateUser.json                    # Full update payload
│   └── PartialUpdate.json                 # Partial update payload
├── testng.xml                             # TestNG suite configuration
└── pom.xml                                # Maven dependencies
```

-----

## ✅ Test Coverage

|Test Class       |HTTP Method|Endpoint       |Description                           |
|-----------------|-----------|---------------|--------------------------------------|
|AuthTest         |POST       |`/auth`        |Generate auth token                   |
|CreateBookingTest|POST       |`/booking`     |Create new booking, extract booking ID|
|GetBookingTest   |GET        |`/booking/{id}`|Retrieve booking by ID                |
|UpdateBookingTest|PUT        |`/booking/{id}`|Full update of booking                |
|UpdatePartialTest|PATCH      |`/booking/{id}`|Partial update of booking             |
|DeleteBookingTest|DELETE     |`/booking/{id}`|Delete booking by ID                  |

-----

## ⚙️ Key Design Decisions

- **BaseTest** configures the base URI (`https://restful-booker.herokuapp.com`) and attaches a `RequestLoggingFilter` once via `@BeforeSuite` — keeping all test classes clean
- **AuthHelper** uses a lazy-loading singleton pattern to generate and reuse the auth token across tests, avoiding redundant auth calls
- **External JSON payloads** (`CreateUser.json`, `UpdateUser.json`, etc.) keep test data separate from test logic
- **Test ordering** via `@Test(priority=n)` ensures booking ID flows correctly from create → get → update → delete
- **JsonPath** used for response field extraction (e.g. extracting `bookingid` after POST)

-----

## 🚀 Getting Started

### Prerequisites

- Java 11+
- Maven 3.6+
- Allure CLI (for reports)

### Installation

```bash
git clone https://github.com/AfsalRehmanSDET/api-automation-restassured.git
cd api-automation-restassured/restful-booker
mvn clean install -DskipTests
```

### Run Tests

```bash
# Run all tests
mvn test

# Run specific suite
mvn test -DsuiteXmlFile=testng.xml
```

### Generate Allure Report

```bash
# Serve report locally
allure serve target/allure-results

# Or generate static report
allure generate target/allure-results --clean -o allure-report
```

-----

## 📊 Reporting

This framework uses **Allure** for rich test reporting including:

- Test execution timeline
- Pass/fail breakdown per test
- Request and response logs per test step
- Suite-level summary

-----

## 🔧 Configuration

Base URI is set in `BaseTest.java`:

```java
RestAssured.baseURI = "https://restful-booker.herokuapp.com";
```

To run against a different environment, update this value or externalize it via a config file.

-----

## 👤 Author

**AfsalRehmanSDET**  
[GitHub Profile](https://github.com/AfsalRehmanSDET)