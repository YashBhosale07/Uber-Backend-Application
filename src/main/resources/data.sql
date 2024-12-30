INSERT INTO app_user (name, email, password) VALUES
('John Doe', 'john.doe@example.com', 'password123'),
('Jane Smith', 'jane.smith@example.com', 'password456'),
('Alice Johnson', 'alice.johnson@example.com', 'password789'),
('Bob Brown', 'bob.brown@example.com', 'password101'),
('Charlie Davis', 'charlie.davis@example.com', 'password102'),
('Emily Wilson', 'emily.wilson@example.com', 'password103'),
('Frank Miller', 'frank.miller@example.com', 'password104'),
('Grace Lee', 'grace.lee@example.com', 'password105'),
('Henry Walker', 'henry.walker@example.com', 'password106'),
('Ivy Adams', 'ivy.adams@example.com', 'password107');
INSERT INTO user_roles (user_id, roles) VALUES
(1, 'RIDER'),
(2, 'DRIVER'),
(3, 'RIDER'),
(4, 'DRIVER'),
(5, 'RIDER'),
(6, 'DRIVER'),
(7, 'RIDER'),
(8, 'DRIVER'),
(9, 'RIDER'),
(10, 'DRIVER');

INSERT INTO rider (id,user_id,rating)VALUES
(1,1,4.9);

INSERT INTO driver (id, rating, user_id, available, current_location) VALUES
(1, 4.5, 1, true, ST_PointFromText('POINT(72.8777 19.0760)', 4326)), -- Mumbai
(2, 3.8, 2, false, ST_PointFromText('POINT(77.1025 28.7041)', 4326)), -- Delhi
(3, 4.2, 3, true, ST_PointFromText('POINT(88.3639 22.5726)', 4326)), -- Kolkata
(4, 4.7, 4, true, ST_PointFromText('POINT(80.2707 13.0827)', 4326)), -- Chennai
(5, 3.5, 5, false, ST_PointFromText('POINT(78.4867 17.3850)', 4326)), -- Hyderabad
(6, 4.9, 6, true, ST_PointFromText('POINT(73.8567 18.5204)', 4326)), -- Pune
(7, 4.3, 7, false, ST_PointFromText('POINT(77.5946 12.9716)', 4326)), -- Bengaluru
(8, 3.7, 8, true, ST_PointFromText('POINT(75.8577 22.7196)', 4326)), -- Indore
(9, 4.0, 9, true, ST_PointFromText('POINT(73.8478 18.5246)', 4326)), -- Near Pune
(10, 4.8, 10, false, ST_PointFromText('POINT(78.9629 20.5937)', 4326)); -- Central India
