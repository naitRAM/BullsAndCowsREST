DROP DATABASE IF EXISTS GuessTheNumberTestDB;
CREATE DATABASE GuessTheNumberTestDB;
USE GuessTheNumberTestDB;

CREATE TABLE Game (
	ID INT PRIMARY KEY AUTO_INCREMENT,
    Answer INT NOT NULL,
    `Status` VARCHAR(20) NOT NULL
);

CREATE TABLE Guess (
	ID INT PRIMARY KEY AUTO_INCREMENT,
    `Time` DATETIME NOT NULL,
    `Value` INT NOT NULL,
    Result VARCHAR(20),
    GameID INT NOT NULL,
    CONSTRAINT fk_Guess_Game FOREIGN KEY (GameID) REFERENCES Game(ID)
);