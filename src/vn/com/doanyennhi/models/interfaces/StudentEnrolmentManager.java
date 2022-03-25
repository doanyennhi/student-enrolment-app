package vn.com.doanyennhi.models.interfaces;

import java.util.List;
import vn.com.doanyennhi.models.StudentEnrolment;

/**
 * <b> StudentEnrolmentManager </b> contains CRUD methods for managing enrolment.
 */
public interface StudentEnrolmentManager {
  boolean add(String sId, String cId, String semester);

  boolean update(String sId, String cId, String semester, String option);

  boolean delete(String sId, String cId, String semester);

  StudentEnrolment getOne(String sId, String cId, String semester);

  List<StudentEnrolment> getAll();

}
