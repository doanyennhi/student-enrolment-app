package vn.com.doanyennhi.models.interfaces;

import vn.com.doanyennhi.models.StudentEnrolment;

public interface StudentEnrolmentManager {
  boolean add(StudentEnrolment studentEnrolment);

  boolean update(StudentEnrolment studentEnrolment);

  boolean delete(StudentEnrolment studentEnrolment);

  StudentEnrolment getOne();

  StudentEnrolment[] getAll();

}
