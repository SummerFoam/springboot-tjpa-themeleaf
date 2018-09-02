package com.test.springboottest0.dao;

import com.test.springboottest0.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer>{
   public Student queryStudentById(Integer id);
   public int deleteStudentById(Integer id);
   //public int update(Student student);
}
