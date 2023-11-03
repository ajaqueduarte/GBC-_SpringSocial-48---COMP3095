  # GBC-_SpringSocial-48---COMP3095

Assignment 1 for COMP 3095 - Jaqueline 101400994 and Gorkem 101266503

## Introduction
This directory presents the Spring Social project, which showcases the application of our learnings from COMP 3095. We are creating microservices, utilizing Docker Compose for containerization, and implementing MongoDB and JPA Jakarta.

## Features
- **Comment Microservice**: Manages comments on posts.
  - **Endpoint**: `/api/comment`
    - `POST`: Create a new comment.
    - `GET`: Retrieve all comments.
    - `PUT`: Update a comment by ID.
    - `DELETE`: Delete a comment by ID.

- **Friendship Microservice**: Handles friendship connections between users.
  - **Endpoint**: `/api/friendship`
    - `GET`: Retrieve a user's friend list by UserID.
    - `POST`: Add a friend.
    - `POST`: Delete a friend.

- **Post Microservice**: Manages posts created by users.
  - **Endpoint**: `/api/post`
    - `POST`: Create a new post.
    - `GET`: Retrieve all posts.
    - `PUT`: Update a post by PostID.
    - `DELETE`: Delete a post by PostID.

- **User Microservice**: Manages user profiles and authentication.
  - **Endpoint**: `/api/users`
    - `POST`: Create a new user.
    - `POST`: Login a user.
    - `GET`: Get a user by ID.
    - `PUT`: Update a user by ID.
    - `DELETE`: Delete a user by ID.
    - `GET`: Get all users.

## Installation
### Setup
1. **Prerequisites:**
   - Ensure Docker is installed on your system.

2. **Running the Project:**
   - Open a terminal.
   - Navigate to the root directory containing the `docker-compose.yml` file.
   - Run the following command:
     ```bash
     docker-compose -f docker-compose.yml up -d --build
     ```
   - Once the containers are up and running, you can interact with the microservices using tools like Postman.

   Example API Endpoints:
   - Comment Microservice: Use Postman to send POST, GET, PUT, DELETE requests to `/api/comment`.
   - Friendship Microservice: Use Postman to send GET, POST, POST requests to `/api/friendship`.
   - Post Microservice: Use Postman to send POST, GET, PUT, DELETE requests to `/api/post`.
   - User Microservice: Use Postman to send POST, POST, GET, PUT, DELETE, GET requests to `/api/users`.

## Authors
- Jaqueline Duarte
- Gorkem Sari
