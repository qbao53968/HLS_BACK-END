use hls;
CREATE TABLE admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username varchar(50),
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    password VARCHAR(100)
);
ALTER TABLE admin 
ADD COLUMN role_id INT,
ADD CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES role(id);

INSERT INTO admin VALUES
(1,'thiennhan02','John', 'Doe', '$2a$10$b6TgtZ4KA/Atjp02ENQkGeJA4hDvYDVQoiNV79GDXDvNeP2QqmYOe',1),
(2,'minduck17', 'Jane', 'Smith', '$2a$10$b6TgtZ4KA/Atjp02ENQkGeJA4hDvYDVQoiNV79GDXDvNeP2QqmYOe',1);

CREATE TABLE role (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255)
);

INSERT INTO role (name, description) VALUES 
('admin', 'Administrator role'),
('user', 'User role');


/*tạo và thêm dữ liệu cho bảng user*/

CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    delete_flag BOOLEAN DEFAULT FALSE,
    province VARCHAR(255),
    children_care_code VARCHAR(255),
    gender VARCHAR(10),
    role_id INT,
    FOREIGN KEY (role_id) REFERENCES role(id)
);


INSERT INTO `user` (`name`, `password`, `firstname`, `lastname`, `role_id`)
VALUES
('user1', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'John', 'Doe', 2),
('user2', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Jane', 'Doe', 2),
('user3', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Alice', 'Smith', 2),
('user4', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Bob', 'Smith', 2),
('user5', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Emily', 'Johnson', 2),
('user6', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Michael', 'Johnson', 2),
('user7', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Emma', 'Wilson', 2),
('user8', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'David', 'Wilson', 2),
('user9', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Olivia', 'Brown', 2),
('user10', '$2a$10$IfP..KQQeLbCVG6QWFXJX.cQ9jT0cyq4VgqODt7M05eVDhG3DApLO', 'Daniel', 'Brown', 2);

