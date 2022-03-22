package vn.com.doanyennhi.models.interfaces;

import java.util.List;
import vn.com.doanyennhi.models.StudentEnrolment;

public interface StudentEnrolmentManager {
  boolean add(String sId, String cId, String semester);

  boolean update(String sId, String semester);

  boolean delete(String sId, String cId, String semester);

  StudentEnrolment getOne(String sId, String cId, String semester);

  List<StudentEnrolment> getAll();

}
