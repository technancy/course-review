package com.technancy.courses.dao;

import com.technancy.courses.exc.DaoException;
import com.technancy.courses.model.Course;

import java.util.List;

public interface CourseDao {
    void add(Course course) throws DaoException;

    List<Course> findAll();

    Course findById(int id);
}
