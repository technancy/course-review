# course-review
a REST API using Spark micro-framework

Import into IntelliJ and Run

## Add a course
POST /courses
`{"name": "REST API with Spark", "url": "/github.com/technancy/course-review"}`

## Find a course
GET /course/2

## List all courses
GET /courses/

## Add a review
POST /courses/2/reviews
`{"rating": "5", "comment": "fantastic"}`

## List all reviews for a course
GET /courses/2/reviews
