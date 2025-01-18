INSERT INTO app_user (name, email, password) VALUES
('John Doe', 'john.doe@example.com', 'password123'),
('Jane Smith', 'jane.smith@example.com', 'password456'),
('Alice Johnson', 'alice.johnson@example.com', 'password789'),
('Bob Brown', 'bob.brown@example.com', 'password101'),
('Charlie Davis', 'charlie.davis@example.com', 'password102'),
('Emily Wilson', 'emily.wilson@example.com', 'password103');


INSERT INTO user_roles (user_id, roles) VALUES
(1, 'RIDER'),
(2, 'DRIVER'),
(3, 'DRIVER'),
(4, 'DRIVER'),
(5, 'DRIVER'),
(6, 'DRIVER');



INSERT INTO rider (user_id, rating) VALUES
( 1, 4.9);



INSERT INTO driver (user_id, rating, available, current_location) VALUES
(2, 4.5, true, ST_PointFromText('POINT(74.1313 28.244123)', 4326)), -- Within ~1.5 km
(3, 4.7, true, ST_PointFromText('POINT(74.1103 28.214123)', 4326)), -- Within ~3.0 km
(4, 4.9, true, ST_PointFromText('POINT(74.1413 28.224123)', 4326)), -- Within ~14 km
(5, 3.7, true, ST_PointFromText('POINT(74.2713 28.324123)', 4326)), -- ~18 km
(6, 4.8, true, ST_PointFromText('POINT(74.2913 28.304123)', 4326)); -- ~19.5 km

insert into wallet (user_id,balance) values(1,100),(2,500);


