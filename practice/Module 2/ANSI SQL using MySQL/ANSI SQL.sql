CREATE DATABASE my_schema;
USE my_schema;
CREATE TABLE users (
	user_id INT PRIMARY KEY AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    city VARCHAR(100) NOT NULL,
    registration_date DATE NOT NULL
);

INSERT INTO Users (user_id, full_name, email, city, registration_date)
VALUES
(1, 'Alice Johnson', 'alice@example.com', 'New York', '2024-12-01'),
(2, 'Bob Smith', 'bob@example.com', 'Los Angeles', '2024-12-05'),
(3, 'Charlie Lee', 'charlie@example.com', 'Chicago', '2024-12-10'),
(4, 'Diana King', 'diana@example.com', 'New York', '2025-01-15'),
(5, 'Ethan Hunt', 'ethan@example.com', 'Los Angeles', '2025-02-01');

CREATE TABLE Events (
    event_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    city VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    end_date DATETIME NOT NULL,
    status ENUM('upcoming', 'completed', 'cancelled'),
    organizer_id INT,
    FOREIGN KEY (organizer_id) REFERENCES Users(user_id)
);

INSERT INTO Events (
    event_id, title, description, city, start_date, end_date, status, organizer_id
)
VALUES
(1, 'Tech Innovators Meetup', 'A meetup for tech enthusiasts.', 'New York',
 '2025-06-10 10:00:00', '2025-06-10 16:00:00', 'upcoming', 1),

(2, 'AI & ML Conference', 'Conference on AI and ML advancements', 'Chicago',
 '2025-05-15 09:00:00', '2025-05-15 17:00:00', 'completed', 3),

(3, 'Frontend Development Bootcamp', 'Hands-on training on frontend tech.', 'Los Angeles',
 '2025-07-01 10:00:00', '2025-07-03 16:00:00', 'upcoming', 2);

CREATE TABLE Sessions (
    session_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    title VARCHAR(200) NOT NULL,
    speaker_name VARCHAR(100) NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

INSERT INTO Sessions (session_id, event_id, title, speaker_name, start_time, end_time)
VALUES
(1, 1, 'Opening Keynote', 'Dr. Tech', '2025-06-10 10:00:00', '2025-06-10 11:00:00'),
(2, 1, 'Future of Web Dev', 'Alice Johnson', '2025-06-10 11:15:00', '2025-06-10 12:30:00'),
(3, 2, 'AI in Healthcare', 'Charlie Lee', '2025-05-15 09:30:00', '2025-05-15 11:00:00'),
(4, 3, 'Intro to HTML5', 'Bob Smith', '2025-07-01 10:00:00', '2025-07-01 12:00:00');

CREATE TABLE Registrations (
    registration_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    registration_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

INSERT INTO Registrations (registration_id, user_id, event_id, registration_date)
VALUES
(1, 1, 1, '2025-05-01'),
(2, 2, 1, '2025-05-02'),
(3, 3, 2, '2025-04-30'),
(4, 4, 2, '2025-04-28'),
(5, 5, 3, '2025-06-15');

CREATE TABLE Feedback (
    feedback_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    event_id INT,
    rating INT CHECK (rating BETWEEN 1 AND 5),
    comments TEXT,
    feedback_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

INSERT INTO Feedback (feedback_id, user_id, event_id, rating, comments, feedback_date)
VALUES
(1, 3, 2, 4, 'Great insights!', '2025-05-16'),
(2, 4, 2, 5, 'Very informative.', '2025-05-16'),
(3, 2, 1, 3, 'Could be better.', '2025-06-11');

CREATE TABLE Resources (
    resource_id INT PRIMARY KEY AUTO_INCREMENT,
    event_id INT,
    resource_type ENUM('pdf', 'image', 'link'),
    resource_url VARCHAR(255) NOT NULL,
    uploaded_at DATETIME NOT NULL,
    FOREIGN KEY (event_id) REFERENCES Events(event_id)
);

INSERT INTO Resources (resource_id, event_id, resource_type, resource_url, uploaded_at)
VALUES
(1, 1, 'pdf', 'https://portal.com/resources/tech_meetup_agen_da.pdf', '2025-05-01 10:00:00'),
(2, 2, 'image', 'https://portal.com/resources/ai_poster.jpg', '2025-04-20 09:00:00'),
(3, 3, 'link', 'https://portal.com/resources/html5_docs', '2025-06-25 15:00:00');

select * from Users,Events,Sessions,Registrations,Feedback,Resources;


 -- Question 1:
 -- USER UPCOMING EVENTS
SELECT
    e.event_id,
    e.title,
    e.description,
    e.city,
    e.start_date,
    e.end_date,
    e.status
FROM
    events e
JOIN
    registrations r ON e.event_id = r.event_id
JOIN
    users u ON r.user_id = u.user_id
WHERE
    e.status = 'upcoming'
    AND e.city = u.city
    AND u.user_id = u.user_id
ORDER BY
    e.start_date ASC;
    
-- Question 2:
-- TOP RATED EVENTS
SELECT
    e.event_id,
    e.title,
    AVG(f.rating) AS avg_rating,
    COUNT(f.feedback_id) AS feedback_count
FROM
    events e
JOIN
    feedback f ON e.event_id = f.event_id
GROUP BY
    e.event_id, e.title
HAVING
    COUNT(f.feedback_id) >= 10
ORDER BY
    avg_rating DESC;

-- Question 3:
-- INACTIVE USERS

SELECT
    u.user_id,
    u.full_name
FROM
    users u
LEFT JOIN
    registrations r 
    ON u.user_id = r.user_id 
    AND r.registration_date >= CURDATE() - INTERVAL 90 DAY
WHERE
    r.registration_id IS NULL;

-- Question 4:
-- PEAK SESSION HOURS

SELECT
    event_id,
    COUNT(session_id) AS session_count
FROM
    sessions
WHERE
    TIME(start_time) >= '10:00:00'
    AND TIME(end_time) <= '12:00:00'
GROUP BY
    event_id;
    
-- Question 5
-- MOST ACTIVE CITIES
SELECT
    u.city,
    COUNT(DISTINCT r.user_id) AS user_count
FROM
    users u
JOIN
    registrations r ON u.user_id = r.user_id
GROUP BY
    u.city
ORDER BY
    user_count DESC
LIMIT 5;

-- Question 6
-- EVENT RESOURSE SUMMARY
SELECT
    event_id,
    SUM(CASE WHEN resource_type = 'pdf' THEN 1 ELSE 0 END) AS pdf_count,
    SUM(CASE WHEN resource_type = 'image' THEN 1 ELSE 0 END) AS image_count,
    SUM(CASE WHEN resource_type = 'link' THEN 1 ELSE 0 END) AS link_count
FROM
    resources
GROUP BY
    event_id;
-- Questin 7
-- LOW FEEDBACK ALERTS

SELECT
    u.user_id,
    u.full_name,
    f.rating,
    f.comments,
    e.title AS event_title
FROM
    feedback f
JOIN
    users u ON f.user_id = u.user_id
JOIN
    events e ON f.event_id = e.event_id
WHERE
    f.rating < 3;
-- Question 8
-- SESSIONS PER UPCOMING EVENT
SELECT
    e.event_id,
    e.title,
    COUNT(s.session_id) AS session_count
FROM
    events e
LEFT JOIN
    sessions s ON e.event_id = s.event_id
WHERE
    e.status = 'upcoming'
GROUP BY
    e.event_id, e.title;
-- Question 9
-- ORGANIZER EVENT SUMMARY

SELECT
    e.organizer_id,
    COUNT(e.event_id) AS event_count,
    e.status
FROM
    events e
GROUP BY
    e.organizer_id, e.status;
-- Question 10
-- FEEDBACK GAP
SELECT
    e.event_id,
    e.title
FROM
    events e
JOIN
    registrations r ON e.event_id = r.event_id
LEFT JOIN
    feedback f ON e.event_id = f.event_id
WHERE
    f.feedback_id IS NULL
GROUP BY
    e.event_id, e.title;
    
-- Question 11
-- DAILY NEW USER COUNT
SELECT
    registration_date,
    COUNT(user_id) AS new_users
FROM
    users
WHERE
    registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY
    registration_date
ORDER BY
    registration_date DESC;

-- Question 12
-- EVENT WITH MAXIMUM SESSIONS
SELECT
    event_id,
    COUNT(session_id) AS session_count
FROM
    sessions
GROUP BY
    event_id
ORDER BY
    session_count DESC
LIMIT 1;
-- Question 13
-- AVERAGE RATING PER CITY
SELECT
    e.city,
    AVG(f.rating) AS avg_rating
FROM
    events e
JOIN
    feedback f ON e.event_id = f.event_id
GROUP BY
    e.city;
-- Question 14
-- MOST REGISTERED EVENTS
SELECT
    e.event_id,
    e.title,
    COUNT(r.registration_id) AS registration_count
FROM
    events e
JOIN
    registrations r ON e.event_id = r.event_id
GROUP BY
    e.event_id, e.title
ORDER BY
    registration_count DESC
LIMIT 3;
-- Question 15
-- EVENT SESSION TIME CONFLICT
SELECT
    s1.event_id,
    s1.session_id AS session1,
    s2.session_id AS session2
FROM
    sessions s1
JOIN
    sessions s2 ON s1.event_id = s2.event_id
    AND s1.session_id < s2.session_id
    AND s1.start_time < s2.end_time
    AND s1.end_time > s2.start_time;
-- Question 16
-- UNREGISTERED ACTIVE USERS
SELECT
    u.user_id,
    u.full_name
FROM
    users u
LEFT JOIN
    registrations r ON u.user_id = r.user_id
WHERE
    u.registration_date >= CURRENT_DATE - INTERVAL 30 DAY
    AND r.registration_id IS NULL;

-- Question 17
-- MULTI-SESSION SPEAKERS
SELECT
    speaker_name,
    COUNT(session_id) AS session_count
FROM
    sessions
GROUP BY
    speaker_name
HAVING
    COUNT(session_id) > 1;
-- Question 18
-- RESOURSE AVAILABILITY CHECK 
SELECT
    e.event_id,
    e.title
FROM
    events e
LEFT JOIN
    resources r ON e.event_id = r.event_id
WHERE
    r.resource_id IS NULL;
-- Question 19
-- COMPLETED EVENTS WITH FEEDBACK SUMMARY
SELECT
    e.event_id,
    e.title,
    COUNT(DISTINCT r.registration_id) AS total_registrations,
    AVG(f.rating) AS avg_rating
FROM
    events e
LEFT JOIN
    registrations r ON e.event_id = r.event_id
LEFT JOIN
    feedback f ON e.event_id = f.event_id
WHERE
    e.status = 'completed'
GROUP BY
    e.event_id, e.title;
-- Question 20
-- USER ENGAGEMENT INDEX
SELECT
    u.user_id,
    u.full_name,
    COUNT(DISTINCT r.event_id) AS events_attended,
    COUNT(DISTINCT f.feedback_id) AS feedbacks_submitted
FROM
    users u
LEFT JOIN
    registrations r ON u.user_id = r.user_id
LEFT JOIN
    feedback f ON u.user_id = f.user_id
GROUP BY
    u.user_id, u.full_name;
-- Question 21
-- TOP FEEDBACK PROVIDERS
SELECT
    u.user_id,
    u.full_name,
    COUNT(f.feedback_id) AS feedback_count
FROM
    users u
JOIN
    feedback f ON u.user_id = f.user_id
GROUP BY
    u.user_id, u.full_name
ORDER BY
    feedback_count DESC
LIMIT 5;
-- Question 22
-- DUPLICATE REGISTRATIONS CHECK 
SELECT
    user_id,
    event_id,
    COUNT(*) AS registration_count
FROM
    registrations
GROUP BY
    user_id, event_id
HAVING
    COUNT(*) > 1;
-- Question 23
-- REGISTRATION TRENDS
SELECT
    DATE_FORMAT(registration_date, '%Y-%m-01') AS month,
    COUNT(registration_id) AS registration_count
FROM
    registrations
WHERE
    registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY
    month
ORDER BY
    month;

-- Question 24
-- AVERAGE SESSION DURATION PER EVENT
SELECT
    event_id,
    AVG(TIMESTAMPDIFF(SECOND, start_time, end_time) / 60) AS avg_duration_minutes
FROM
    sessions
GROUP BY
    event_id;

-- Question 25
-- EVENTS WIITHOUT SESSIONS
SELECT
    e.event_id,
    e.title
FROM
    events e
LEFT JOIN
    sessions s ON e.event_id = s.event_id
WHERE
    s.session_id IS NULL;





