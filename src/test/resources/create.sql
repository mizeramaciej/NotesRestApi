CREATE TABLE IF NOT EXISTS Notes(
  ID int(10) PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255),
  content VARCHAR(255),
  createdDate TIMESTAMP,
  lastModified TIMESTAMP,
);