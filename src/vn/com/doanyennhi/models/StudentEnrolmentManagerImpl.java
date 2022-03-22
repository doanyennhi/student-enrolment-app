package vn.com.doanyennhi.models;

import java.util.List;
import vn.com.doanyennhi.models.interfaces.StudentEnrolmentManager;
import vn.com.doanyennhi.processing.EnrolmentDataProcessor;

/**
 * <b> StudentEnrolmentManagerImpl </b> is used to manage enrolment-related functionalities.
 */
public class StudentEnrolmentManagerImpl implements StudentEnrolmentManager {
  private List<StudentEnrolment> studentEnrolments;

  public StudentEnrolmentManagerImpl(String path) {
    // retrieve enrolment data
    this.studentEnrolments = EnrolmentDataProcessor.processEnrolmentData(path);
  }

  public Student findStudent(String studentId) {
    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getStudent().getsId().equals(studentId)) {
        return enrolment.getStudent();
      }
    }
    System.out.println("This student does not exist in the system.");
    return null;
  }

  public Course findCourse(String courseId) {
    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getCourse().getcId().equals(courseId)) {
        return enrolment.getCourse();
      }
    }
    System.out.println("This course is not offered in the system.");
    return null;
  }

  public String findSemester(String semester) {
    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getSemester().equals(semester)) {
        return enrolment.getSemester();
      }
    }
    System.out.println("This semester does not exist in the database.");
    return null;
  }


  /**
   * Function to enrol a student for a course in a semester
   * @param sId student ID
   * @param cId course ID
   * @param sem semester
   * @return true if enrolment is successful, false otherwise
   */
  @Override
  public boolean add(String sId, String cId, String sem) {
    Student student = findStudent(sId);
    Course course = findCourse(cId);
    String semester = findSemester(sem);
    if (student  == null | course == null | semester == null) {
      // cannot enrol as info provided cannot be found
      return false;
    }

    // check if student has already enrolled in the provided course and semester
    for (StudentEnrolment enrolment: this.studentEnrolments) {
      if (enrolment.getStudent().equals(student)
          && enrolment.getCourse().equals(course)
          && enrolment.getSemester().equals(semester)
      ) {
        System.out.printf("Student %s already enrolled in course %s for semester %s\n",
            sId, cId, sem);
        return false;
      }
    }

    studentEnrolments.add(new StudentEnrolment(student, course, semester));
    System.out.println("Enrol successfully!");

    for (StudentEnrolment enrolment: this.studentEnrolments) {
      System.out.println(enrolment);
    }
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
