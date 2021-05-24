# REST API using Spring for posting Blogs

This is my first spring project. I have developed a REST API for posting blogs. For now, users can only post blogs containing text, but soon I will add feature to add images to the blogs.
Following services are provided by the REST API:

1. Users can be added (Authentication facility is not added)
2. New Blogs can be added.
3. Existing Blogs can be updated.
4. Delete existing Blogs.
5. Get All Blogs.
6. Get specific Blog by given ID.
7. Post Comment on existing Blog.
8. Get all Comments for a Blog.
9. Add Comment on a Comment.
10. Get all Comments under the given Comment.

> I have handled major exceptions and errors, but still due to time constraint I have not implemented many checks, I will update the new version soon.

### API Endpoints

1. **POST** `/user` create new user with email, name and password (No validation is done here, this is for demo purpose only).
2. **GET** `/user/{email}` get specific user with given email.
3. **PUT** `/user/{email}` update user with given email.
4. **DELETE** `/user/{email}` Delete user with given email.
5. **GET** `/blogs` Get all blogs.
6. **GET** `/blog/{post_id}` Get blog with given ID.
7. **POST** `/blogs` Create new blog.
8. **PUT** `/blogs/{post_id}` Update existing blog with given ID.
9. **DELETE** `/blogs/{post_id}` Delete blog with given ID.
10. **GET** `/blogs/{post_id}/comments` Get all comments for given blogs with id = post_id.
11. **POST** `/blogs/{post_id}/comments` Add new comment under the blog with id = post_id.
12. **GET** `/blogs/{post_id}/comments/{comment_id}` Get all comments under a comment(id=comment_id) of a given blog(id=post_id).
13. **POST**  `/blogs/{post_id}/comments/{parent_id}` Create a comment under comment(id=comment_id) in blog (id=post_id).
14. **PUT** `/blogs/{post_id}/comments/{comment_id}` Update root comment with id=comment_id under blog with id=post_id.
15. **PUT** `/blogs/{post_id}/comments/{parent_id}/{comment_id}` Update nested comment(id=comment_id) under a comment(id=parent_id) with blog(id=post_id).

## Database Schema

- USERS
    - email VARCHAR(50) PK
    - password VARCHAR(20)
    - full_name VARCHAR (250)

- BLOG
    - post_id VARCHAR(10) PK
    - content LONGTEXT
    - author VARCHAR(50) FK references USERS(email)
    - created_on TIMESTAMP
    - last_modified TIMESTAMP
    
- COMMENTS
    - comment_id VARCHAR(10) PK
    - post_id VARCHAR(10) PK FK references BLOG(post_id)
    - parent_id VARCHAR(10) PK
    - content LONGTEXT
    - author VARCHAR(50) FK references USERS(email)
    - created_on TIMESTAMP
    - last_modified TIMESTAMP

**Note: All the id's are generated randomly and have length=10.**

To retrieve all the comments under a given comment, I have used **RECURSIVE** query which is most suitable for hierarchical data.

I tried to handle following exceptions and validations:
- When Particular endpoint is not implemented it will give appropriate error message.
- Internal server errors. User will get approriate message.
- User already exists with given email.
- Blog Already exists.
- SQL exceptions (contraint violations).

For every error response, user will get appropriate message along with its HTTP status code.

This API can be improved further, but due to time constraint I have added only limited functionalities. For further API information I am adding POSTMAN collection in the repository.

