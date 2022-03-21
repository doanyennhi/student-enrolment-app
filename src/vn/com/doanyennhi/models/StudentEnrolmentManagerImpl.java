package vn.com.doanyennhi.models;

import java.util.ArrayList;
import java.util.HashSet;
import vn.com.doanyennhi.models.interfaces.StudentEnrolmentManager;
import vn.com.doanyennhi.processing.EnrolmentDataProcessor;

/**
 * <b> StudentEnrolmentManagerImpl </b> is used to manage enrolment-related functionalities.
 */
public class StudentEnrolmentManagerImpl implements StudentEnrolmentManager {
  private HashSet<Student> students;
  private HashSet<Course> courses;
  private HashSet<String> semesters;
  private ArrayList<StudentEnrolment> studentEnrolments;

  public StudentEnrolmentManagerImpl(String path) {
    this.studentEnrolments = EnrolmentDataProcessor.processEnrolmentData(path);
    // TODO: loop to add students, courses, semesters
  }


  /**
   * Function to enrol a student for a course in a semester
   * @param sId student ID
   * @param cId course ID
   * @param semester
   * @return true if enrolment is successful, false otherwise
   */
  @Override
  public boolean add(String sId, String cId, String semester) {
    for (StudentEnrolment enrolment: this.studentEnrolments) {
      String currentsId = enrolment.getStudent().getsId();
      String currentcId = enrolment.getCourse().getcId();
      String currentSemester = enrolment.getSemester();

      if (currentsId.equals(sId) && currentcId.equals(cId) && currentSemester.equals(semester)) {
        System.out.printf("Student %s already enrolled in course %s for semester %s\n",
            sId, cId, semester);
        return false;
      }
    }

    // TODO: implement add - studentEnrolments.add(new StudentEnrolment())
    return true;
  }

  @Override
  public boolean update(String sId, String semester) {
    return false;
  }

  @Override
  public boolean delete(String sId, String cId, String semester) {
    return false;
  }

  @Override
  public StudentEnrolment getOne(String sId, String cId, String semester) {
    return null;
  }

  @Override
  public StudentEnrolment[] getAll() {
    return new StudentEnrolment[0];
  }
}
