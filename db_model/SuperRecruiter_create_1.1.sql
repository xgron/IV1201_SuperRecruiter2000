-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-01-25 12:16:55.914

-- tables
-- Table: Availability
CREATE TABLE Availability (
    Person_ssn int NOT NULL,
    start_date date NOT NULL,
    end_date date NOT NULL,
    CONSTRAINT Availability_pk PRIMARY KEY (Person_ssn,start_date)
);

-- Table: Competence
CREATE TABLE Competence (
    name varchar(255) NOT NULL,
    CONSTRAINT Competence_pk PRIMARY KEY (name)
);

-- Table: Experience
CREATE TABLE Experience (
    Person_ssn int NOT NULL,
    Competence_name varchar(255) NOT NULL,
    years decimal(10,1) NOT NULL,
    CONSTRAINT Experience_pk PRIMARY KEY (Person_ssn,Competence_name)
);

-- Table: Person
CREATE TABLE Person (
    name varchar(255) NOT NULL,
    surname varchar(255) NOT NULL,
    ssn int NOT NULL,
    email varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    Role_name varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    hired bool NULL,
    UNIQUE INDEX username (username),
    CONSTRAINT Person_pk PRIMARY KEY (ssn)
);

-- Table: Role
CREATE TABLE Role (
    name varchar(255) NOT NULL,
    CONSTRAINT Role_pk PRIMARY KEY (name)
);

-- foreign keys
-- Reference: Availability_Person (table: Availability)
ALTER TABLE Availability ADD CONSTRAINT Availability_Person FOREIGN KEY Availability_Person (Person_ssn)
    REFERENCES Person (ssn);

-- Reference: Experience_Competence (table: Experience)
ALTER TABLE Experience ADD CONSTRAINT Experience_Competence FOREIGN KEY Experience_Competence (Competence_name)
    REFERENCES Competence (name);

-- Reference: Experience_Person (table: Experience)
ALTER TABLE Experience ADD CONSTRAINT Experience_Person FOREIGN KEY Experience_Person (Person_ssn)
    REFERENCES Person (ssn);

-- Reference: Person_Role (table: Person)
ALTER TABLE Person ADD CONSTRAINT Person_Role FOREIGN KEY Person_Role (Role_name)
    REFERENCES Role (name);

-- End of file.

