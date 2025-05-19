# MojaChata.pl - an application for renting apartments

- [Polish](README-PL.md)

## For PAP course

---

## Authors:
- Weronika Maślana
- Katarzyna Kanicka
- Tomasz Foryś

---

## Scope of the project
As part of the project, the team is to prepare a small application to create, edit and search information in a relational or document type (NoSQL) database set by the instructor.
At each stage, in addition to the created code, such elements as user interface design, source management, creation of automated tests, continuous integration of the software will be evaluated.

---

## Launching the application
From the main directory /app run in terminal:
`./mvnw spring-boot:run`.

in the browser we launch:
`http://localhost:8080`.

Done!

### Running the tests
From the root directory /app run in terminal:
`./mvnw test`.

---

## Architecture
- Oracle database
- Java backend using Spring framework
- Frontend in Thymeleaf

---

## Functionalities
1. adding apartment for rent
2. removing a rental apartment
3. changing the parameters of an apartment for rent
4. searching for available apartments for rent
5. possibility of booking an apartment we want to rent out
6. filtering by price, size and destination of the apartment
7. possibility of accepting or rejecting the application by the owner
8. adding an opinion
9. deleting an opinion
10. viewing only your apartments
11. logging into the application
12. notifying interest in the apartment (information to the owner of the apartment that he has a request for his apartment. It presents itself in the form of a flashing button my apartments)
13. cancelling of reservation
