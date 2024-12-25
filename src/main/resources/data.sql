-- INITAL DATA
----- COUNTRIES
SELECT NEXT VALUE FOR COUNTRIES_SEQ;
INSERT INTO COUNTRIES
    (COUNTRY_ID, NAME, LANGUAGE, CURRENCY)
VALUES
    (1, 'Poland', 'polish', 'PLN'),
    (2, 'Germany', 'german', 'EUR'),
    (3, 'France', 'french', 'EUR'),
    (4, 'Italy', 'italian', 'EUR'),
    (5, 'Spain', 'spanish', 'EUR');

------ CITIES
SELECT NEXT VALUE FOR CITIES_SEQ;
INSERT INTO CITIES
    (CITY_ID, NAME, POPULATION, SIZE, COUNTRY_ID)
VALUES
    (1, 'Warsaw', 1.86, 517 , 1),
    (2, 'Cracow', 0.80, 326, 1),
    (3, 'Lodz', 0.69, 293, 1),
    (4, 'Wroclaw', 0.64, 292, 1),
    (5, 'Gdansk', 0.48, 262, 1),

    (6, 'Berlin', 3.7, 891, 2),
    (7, 'Hamburg', 1.9, 755, 2),
    (8, 'Munich', 1.6, 310, 2),

    (9, 'Paris', 2.1, 105, 3),
    (10, 'Marseille', 0.87, 204, 3),
    (11, 'Lyon', 0.52, 47, 3),

    (12, 'Rome', 2.9, 1285, 4),
    (13, 'Milan', 1.4, 181, 4),
    (14, 'Naples', 0.97, 119, 4),

    (15, 'Madrid', 3.3, 604, 5),
    (16, 'Barcelona', 1.6, 101, 5),
    (17, 'Valencia', 0.8, 134, 5);

------ ADRESSES
SELECT NEXT VALUE FOR ADDRESSES_SEQ;
SELECT NEXT VALUE FOR ADDRESSES_SEQ;
INSERT INTO ADDRESSES
    (ADDRESS_ID, STREET, POSTAL_CODE, CITY_ID)
VALUES
    (1, 'Mini 5', '01-123', 1),
    (2, 'Epic 20', '03-456', 1),
    (3, 'Christmas street 3', '01-651', 3),
    (4, 'Christmas street 4', '01-652', 3),
    (5, 'Magical street 77', '07-777', 9),
    (6, 'Bagshot Row 1', '05-989', 8),
    (7, 'Bagshot Row 3', '05-991', 8);


------- USERS
SELECT NEXT VALUE FOR USERS_SEQ;
INSERT INTO USERS
    (USER_ID, USERNAME, PASSWORD_HASH)
VALUES
    (1, 'Jan', 'Kowalski');

SELECT NEXT VALUE FOR USERS_SEQ;
INSERT INTO USERS
    (USER_ID, USERNAME, PASSWORD_HASH)
VALUES
    (2, 'w', '1');

------ COTTAGES
SELECT NEXT VALUE FOR COTTAGES_SEQ;
SELECT NEXT VALUE FOR COTTAGES_SEQ;
INSERT INTO COTTAGES
    (COTTAGE_ID, NAME, ADDRESS_ID, SIZE_M2, BATHROOMS_NUMBER, ROOMS_NUMBER, MAX_PEOPLE_NUM, MIN_PRICE_PER_DAY, OWNER_ID)
VALUES
    (1, 'Micro Cottage', 1, 23, 1, 2, 1, 10.89, 1),
    (2, 'The Grand Villa', 2, 600, 100, 50, 100, 149.99, 1),
    (3, 'Merry Cottage', 3, 45, 1, 5, 3, 25.89, 1),
    (4, 'Merry House', 4, 68, 3, 7, 6, 44.89, 1),
    (5, 'Elfish Home', 5, 80, 6, 10, 8, 49.99, 2),
    (6, 'Hobbit Hole', 6, 56, 2, 4, 2, 17.89, 2),
    (7, 'Baggins Hill', 7, 136, 4, 12, 8, 129.99, 2);