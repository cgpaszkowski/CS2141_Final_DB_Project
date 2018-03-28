DROP SCHEMA MusicDB;
CREATE SCHEMA MusicDB;
USE MusicDB;

CREATE TABLE IF NOT EXISTS Genre (
  Genre_ID   INT AUTO_INCREMENT,
  Genre_Name VARCHAR(30) NOT NULL,
  PRIMARY KEY (Genre_ID)
);

CREATE TABLE IF NOT EXISTS Country (
  Country_ID   INT         AUTO_INCREMENT,
  Country_Name VARCHAR(30) NOT NULL ,
  PRIMARY KEY (Country_ID)
);
CREATE TABLE IF NOT EXISTS Label (
  Label_ID INT AUTO_INCREMENT,
  Label_Name VARCHAR(30) NOT NULL,
  PRIMARY KEY (Label_ID)
);

CREATE TABLE IF NOT EXISTS Artist (
  Artist_ID   INT AUTO_INCREMENT,
  Artist_Name VARCHAR(45) NOT NULL,
  Country_ID  INT,
  Label_ID INT,
  PRIMARY KEY (Artist_ID),
  FOREIGN KEY (Country_ID) REFERENCES Country (Country_ID),
  FOREIGN KEY (Label_ID) REFERENCES Label(Label_ID)
);


CREATE TABLE IF NOT EXISTS Album (
  Album_ID   INT AUTO_INCREMENT,
  Album_Name VARCHAR(45) NOT NULL,
  Genre_ID   INT,
  Artist_ID int,
  PRIMARY KEY (Album_ID),
  FOREIGN KEY (Genre_ID) REFERENCES Genre (Genre_ID),
  FOREIGN KEY (artist_ID) REFERENCES artist (artist_ID)
);


CREATE TABLE IF NOT EXISTS Track (
  Track_ID     INT AUTO_INCREMENT,
  Track_Name   VARCHAR(45) NOT NULL,
  Album_ID     INT,
  artist_id int,
  PRIMARY KEY (Track_ID),
  FOREIGN KEY (Album_ID) REFERENCES Album (Album_ID),
    FOREIGN KEY (artist_id) REFERENCES artist (Artist_id)
);

CREATE TABLE IF NOT EXISTS AlbumRelease (
  Release_ID      INT AUTO_INCREMENT,
  Album_ID        INT      NOT NULL,
  Release_Date    DATETIME NOT NULL,
  PRIMARY KEY (Release_ID),
  FOREIGN KEY (Album_ID) REFERENCES Album (Album_ID)
);


CREATE TABLE IF NOT EXISTS Review (
  Review_ID      INT AUTO_INCREMENT,
  Review_Score   INT NOT NULL,
  Album_ID       INT NOT NULL,
  PRIMARY KEY (Review_ID),
  FOREIGN KEY (Album_ID) REFERENCES Album (Album_ID)
);