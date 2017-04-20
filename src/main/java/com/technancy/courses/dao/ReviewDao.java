package com.technancy.courses.dao;

import com.technancy.courses.exc.DaoException;
import com.technancy.courses.model.Review;

import java.util.List;

public interface ReviewDao {
    void add(Review review) throws DaoException;

    List<Review> findAll();
    List<Review> findByCourseId(int courseId);

}
