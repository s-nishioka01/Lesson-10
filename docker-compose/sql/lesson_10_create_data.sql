USE user_list;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
	id INT unsigned AUTO_INCREMENT,
	name VARCHAR(100) NOT NULL,
	PRIMARY KEY (id)
);

INSERT INTO users (id, name) VALUE (1, "Harry Potter");
INSERT INTO users (id, name) VALUE (2, "Ron Weasley");
INSERT INTO users (id, name) VALUE (3, "Hermione Granger");


