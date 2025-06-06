-- SET DB_CLOSE_DELAY -1;
-- CREATE USER IF NOT EXISTS SA SALT '4E3A3BCDED2DC398' HASH '7F28392927350F5068F027DD266C7DADFA696EC41EDC3925CD41241A3A7F2A2D' ADMIN;
CREATE SEQUENCE COUNTRIES_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE CITIES_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE ADDRESSES_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE USERS_SEQ START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE COTTAGES_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE FACILITIES_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE EQUIPMENTS_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE RESTRICTIONS_SEQ START WITH 1 INCREMENT BY 50;

CREATE SEQUENCE REVIEWS_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE REQUESTS_SEQ START WITH 1 INCREMENT BY 50;
CREATE SEQUENCE REQUEST_APPROVALS_SEQ START WITH 1 INCREMENT BY 50;

CREATE TABLE COUNTRIES(
  COUNTRY_ID BIGINT NOT NULL,
  NAME VARCHAR (40) NOT NULL,
  LANGUAGE VARCHAR(50),
  CURRENCY VARCHAR(10),
  PRIMARY KEY (COUNTRY_ID)
 );


CREATE TABLE CITIES(
  CITY_ID BIGINT NOT NULL,
  NAME VARCHAR (40) NOT NULL ,
  POPULATION DECIMAL(7, 2),
  SIZE DECIMAL(7, 2),
  COUNTRY_ID NUMERIC NOT NULL REFERENCES COUNTRIES (COUNTRY_ID),
  PRIMARY KEY (CITY_ID)
 );

 CREATE TABLE ADDRESSES(
  ADDRESS_ID BIGINT NOT NULL,
  STREET VARCHAR (40) NOT NULL ,
  POSTAL_CODE VARCHAR (40 ) NOT NULL ,
  CITY_ID NUMERIC NOT NULL REFERENCES CITIES (CITY_ID),
  PRIMARY KEY (ADDRESS_ID)
 );

CREATE TABLE USERS(
    USER_ID BIGINT NOT NULL,
    NAME VARCHAR (80),
    SURNAME VARCHAR (80),
    EMAIL VARCHAR (255),
    USERNAME VARCHAR(100) NOT NULL,
    PASSWORD_HASH VARCHAR(100) NOT NULL,
    PRIMARY KEY (USER_ID)
)
ON COMMIT PRESERVE ROWS;

CREATE TABLE COTTAGES(
    COTTAGE_ID BIGINT NOT NULL,
    NAME VARCHAR(100) NOT NULL,
    SIZE_M2 DECIMAL(7, 2) NOT NULL,
    ADDRESS_ID NUMERIC (4) UNIQUE NOT NULL CONSTRAINT ADDR_COTTAGE_FK REFERENCES ADDRESSES (ADDRESS_ID),
    BATHROOMS_NUMBER INTEGER NOT NULL,
    MAX_PEOPLE_NUM INTEGER NOT NULL,
    MIN_PRICE_PER_DAY DECIMAL(7, 2) NOT NULL,
    ROOMS_NUMBER INTEGER NOT NULL,
    OWNER_ID NUMERIC (4) NOT NULL CONSTRAINT USER_COTTAGE_FK REFERENCES USERS (USER_ID),
    PRIMARY KEY (COTTAGE_ID)
);

CREATE TABLE FACILITIES(
    FACILITY_ID BIGINT NOT NULL,
    NAME VARCHAR(40) NOT NULL,
    DESCRIPTION VARCHAR(100),
    PRIMARY KEY (FACILITY_ID)
);

CREATE TABLE COTTAGES_FACILITIES(
    COTTAGE_ID BIGINT CONSTRAINT COTTAGE_FACILITY_FK REFERENCES COTTAGES (COTTAGE_ID),
    FACILITY_ID BIGINT CONSTRAINT FACILITY_FK REFERENCES FACILITIES (FACILITY_ID),
    PRIMARY KEY (COTTAGE_ID, FACILITY_ID)
);

CREATE TABLE EQUIPMENTS(
    EQUIPMENT_ID BIGINT NOT NULL,
    NAME VARCHAR(40) NOT NULL,
    DESCRIPTION VARCHAR(100),
    PRIMARY KEY (EQUIPMENT_ID)
);

CREATE TABLE COTTAGES_EQUIPMENTS(
    COTTAGE_ID BIGINT CONSTRAINT COTTAGE_EQUIPMENT_FK REFERENCES COTTAGES (COTTAGE_ID),
    EQUIPMENT_ID BIGINT CONSTRAINT EQUIPMENT_FK REFERENCES EQUIPMENTS (EQUIPMENT_ID),
    PRIMARY KEY (COTTAGE_ID, EQUIPMENT_ID)
);

CREATE TABLE RESTRICTIONS(
    RESTRICTION_ID BIGINT NOT NULL,
    NAME VARCHAR(40) NOT NULL,
    DESCRIPTION VARCHAR(100),
    PRIMARY KEY (RESTRICTION_ID)
);

CREATE TABLE COTTAGES_RESTRICTIONS(
    COTTAGE_ID BIGINT  CONSTRAINT COTTAGE_RESTRICTION_FK REFERENCES COTTAGES (COTTAGE_ID),
    RESTRICTION_ID BIGINT CONSTRAINT RESTRICTION_FK REFERENCES RESTRICTIONS (RESTRICTION_ID),
    PRIMARY KEY (COTTAGE_ID, RESTRICTION_ID)
);


CREATE TABLE REQUESTS(
    REQUEST_ID BIGINT NOT NULL,
    COTTAGE_ID BIGINT NOT NULL CONSTRAINT REQUEST_COTTAGE_FK REFERENCES COTTAGES (COTTAGE_ID),
    CHECKIN_DATE DATE NOT NULL,
    CHECKOUT_DATE DATE NOT NULL,
    TOTAL_PRICE DECIMAL(7, 2) NOT NULL,
    CUSTOMER_ID BIGINT NOT NULL CONSTRAINT REQUEST_CUSTOMER_FK REFERENCES USERS (USER_ID),
    PRIMARY KEY (REQUEST_ID)
);

CREATE TABLE REQUEST_APPROVALS (
  REQUEST_APPROVAL_ID BIGINT NOT NULL,
  DATE_CREATED DATE NOT NULL,
  IS_APPROVED BOOLEAN NOT NULL,
  REQUEST_ID NUMERIC (4) UNIQUE NOT NULL CONSTRAINT REQUEST_APPROVAL_REQUEST_FK REFERENCES REQUESTS (REQUEST_ID),
  PRIMARY KEY (REQUEST_APPROVAL_ID)
);

CREATE TABLE REVIEWS(
    REVIEW_ID BIGINT NOT NULL,
    COTTAGE_ID BIGINT NOT NULL CONSTRAINT REVIEW_COTTAGE_FK REFERENCES COTTAGES (COTTAGE_ID),
    AUTHOR_ID BIGINT NOT NULL CONSTRAINT AUTHOR_COTTAGE_FK REFERENCES USERS (USER_ID),
    TEXT VARCHAR(1020),
    DATE_POSTED DATE NOT NULL,
    GRADE NUMERIC(4) NOT NULL,
    PRIMARY KEY (REVIEW_ID)
);

