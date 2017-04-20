package com.technancy.courses.dao;

import com.technancy.courses.exc.DaoException;
import com.technancy.courses.model.Course;
import com.technancy.courses.model.Review;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Sql2oReviewDaoTest {
    private Connection conn;
    private Sql2oCourseDao courseDao;
    private Sql2oReviewDao reviewDao;
    private Course course;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        courseDao = new Sql2oCourseDao(sql2o);
        reviewDao = new Sql2oReviewDao(sql2o);

        conn = sql2o.open();
        course = new Course("Test", "http://test.com");
        courseDao.add(course);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingReviewSetsId() throws Exception {
        Review review = newTestReview();
        int originalReviewId = review.getId();

        reviewDao.add(review);

        assertNotEquals(originalReviewId, review.getId());
    }

    private Review newTestReview() {
        return new Review(course.getId(), 5, "Great course");
    }

    @Test
    public void addedReviewsAreReturnedFromFindAll() throws Exception {
        Review review = newTestReview();
        reviewDao.add(review);
        reviewDao.add(review);

        assertEquals(2, reviewDao.findAll().size());
    }

    @Test
    public void multipleReviewsAreFoundWhenTheyExistForACourse() throws Exception {
        Review review = newTestReview();
        reviewDao.add(review);
        reviewDao.add(review);

        List<Review> reviews = reviewDao.findByCourseId(course.getId());

        assertEquals(2, reviews.size());
    }

    @Test(expected = DaoException.class)
    public void addingAReviewToANonExistingCourseFails() throws Exception {
        Review review = new Review(42, 5, "Test Comment");

        reviewDao.add(review);

    }
}
