DROP TABLE IF EXISTS heroes;

CREATE TABLE heroes (
                              id INT AUTO_INCREMENT  PRIMARY KEY,
                              hero VARCHAR(250) NOT NULL,
                              movie VARCHAR(250) NOT NULL
);

