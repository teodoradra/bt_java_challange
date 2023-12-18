CREATE TABLE IF NOT EXISTS client (
  CNP CHAR(13),
  LastName varchar(255) NOT NULL,
  FirstName varchar(255) NOT NULL,
  DocumentId varchar(6),
  DocumentExpDate DATE,
  PRIMARY KEY (CNP)
);