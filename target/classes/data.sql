INSERT INTO users
(username, full_name, phone_number, email, password, address, location_lat, location_long, created_at)
VALUES
(1, 'Krishna', '9876543210', 'krishna@example.com', '12345',
 'Hyderabad', 17.385, 78.4867, '2025-12-12T10:20:00');

INSERT INTO users
(username, full_name, phone_number, email, password, address, location_lat, location_long, created_at)
VALUES
(2, 'Anil Kumar', '9988776655', 'anil@example.com', 'password123',
 'Vijayawada', 16.5062, 80.6480, '2025-12-12T11:00:00');

INSERT INTO users
(username, full_name, phone_number, email, password, address, location_lat, location_long, created_at)
VALUES
(3, 'Ravi Teja', '9123456789', 'ravi@example.com', 'ravi@321',
 'Guntur', 16.3067, 80.4365, '2025-12-12T09:45:00');

INSERT INTO users
(username, full_name, phone_number, email, password, address, location_lat, location_long, created_at)
VALUES
(4, 'Sita Devi', '9876501234', 'sita@example.com', 'sita789',
 'Vizag', 17.6868, 83.2185, '2025-12-12T14:30:00');

INSERT INTO users
(username, full_name, phone_number, email, password, address, location_lat, location_long, created_at)
VALUES
(5, 'Rohit Sharma', '9000090000', 'rohit@example.com', 'rohit123',
 'Tirupati', 13.6288, 79.4192, '2025-12-12T16:10:00');



INSERT INTO mechanic
(
  mechanic_id,
  name,
  phone_number,
  skills,
  email,
  password,
  experience_years,
  rating,
  availability_status,
  base_location_lat,
  base_location_long,
  service_radius_km,
  profile_verified
)
VALUES
(1, 'Ravi Kumar', '9876543210',
 'Bike Repair, Engine Repair',
 'ravi@gmail.com', 'ravi123',
 5, 4.5, TRUE,
 17234567, 78345612,
 10, TRUE),

(2, 'Suresh Naidu', '9123456789',
 'Car AC Repair, Electrical',
 'suresh@gmail.com', 'suresh123',
 8, 4.7, TRUE,
 17234890, 78345999,
 15, TRUE),

(3, 'Anil Reddy', '9988776655',
 'Tyre Change, Puncture Repair',
 'anil@gmail.com', 'anil123',
 3, 4.2, TRUE,
 17234001, 78345001,
 8, FALSE),

(4, 'Mahesh Rao', '9012345678',
 'Diesel Engine, Heavy Vehicles',
 'mahesh@gmail.com', '1',
 12, 4.8, FALSE,
 17234999, 78346888,
 20, TRUE),

(5, 'Kiran Patel', '9090909090',
 'General Service, Oil Change',
 'kiran@gmail.com', 'kiran123',
 4, 4.0, TRUE,
 17234123, 78345234,
 12, FALSE);


INSERT INTO service_request
(username, assigned_mechanic_id, vehicle_type, vehicle_number,
 problem_description, current_location_lat, current_location_long,
 requested_time, status, amount, payment_status)
VALUES
(1, 0, 'Bike', 'AP09AB1234',
 'Bike not starting, battery issue',
 16.5062, 80.6480,
 CURRENT_TIMESTAMP,
 'Pending', 0, 'Unpaid'),

(2, 1, 'Car', 'TS10CD5678',
 'Car engine overheating',
 17.3850, 78.4867,
 CURRENT_TIMESTAMP,
 'Assigned', 1200, 'Pending'),

(3, 2, 'Auto', 'AP16EF9012',
 'Auto clutch problem',
 15.8281, 78.0373,
 CURRENT_TIMESTAMP,
 'In Progress', 800, 'Pending'),

(4, 3, 'Truck', 'KA05GH3456',
 'Truck tyre burst on highway',
 12.9716, 77.5946,
 CURRENT_TIMESTAMP,
 'Completed', 2500, 'Paid'),

(5, 0, 'Car', 'TN07IJ7890',
 'Car AC not cooling',
 13.0827, 80.2707,
 CURRENT_TIMESTAMP,
 'Pending', 0, 'Unpaid');



INSERT INTO payments
(request_id, amount, payment_method, payment_status, transaction_id, timestamp)
VALUES
(1, 500, 'UPI', 'Paid', 'TXN1001', CURRENT_TIMESTAMP),
(2, 1200, 'Card', 'Paid', 'TXN1002', CURRENT_TIMESTAMP),
(3, 800, 'Cash', 'Pending', 'TXN1003', CURRENT_TIMESTAMP),
(4, 2500, 'UPI', 'Paid', 'TXN1004', CURRENT_TIMESTAMP),
(5, 600, 'Card', 'Failed', 'TXN1005', CURRENT_TIMESTAMP);

INSERT INTO reviews
(username, mechanic_id, rating, comments, created_at)
VALUES
(1, 1, 5, 'Excellent service, very quick and professional', CURRENT_TIMESTAMP),

(2, 2, 4, 'Good service, but arrived a bit late', CURRENT_TIMESTAMP),

(3, 1, 3, 'Average experience, issue fixed though', CURRENT_TIMESTAMP),

(4, 3, 5, 'Mechanic was very polite and skilled', CURRENT_TIMESTAMP),

(5, 2, 2, 'Problem not fully resolved', CURRENT_TIMESTAMP);
