package vn.com.doanyennhi.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import vn.com.doanyennhi.models.interfaces.StudentEnrolmentManager;
import vn.com.doanyennhi.processing.EnrolmentDataProcessor;

/**
 * <b> StudentEnrolmentManagerImpl </b> is used to manage and implement
 * enrolment-related functionalities.
 */
public class StudentEnrolmentManagerImpl implements StudentEnrolmentManager {
  private List<StudentEnrolment> studentEnrolments;

  public StudentEnrolmentManagerImpl(String path) {
    // retrieve enrolment data and populate to enrolments list
    this.studentEnrolments = EnrolmentDataProcessor.processEnrolmentData(path);
  }


  /**
   * Find and retrieve a student from their ID
   * @param studentId id of student we want to get
   * @return the student if they exist in the data, null otherwise
   */
  public Student findStudent(String studentId) {
    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getStudent().getsId().equals(studentId)) {
        return enrolment.getStudent();
      }
    }
    System.out.println("This student does not exist.");
    return null;
  }


  /**
   * Find and retrieve a course from its ID
   * @param courseId id of course we want to get
   * @return the course if it exists in the data, null otherwise
   */
  public Course findCourse(String courseId) {
    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getCourse().getcId().equals(courseId)) {
        return enrolment.getCourse();
      }
    }
    System.out.println("This course does not exist.");
    return null;
  }


  /**
   * Check if the semester provided exists in the database
   * @param semester we want to check
   * @return true if it is in database, false otherwise
   */
  public boolean hasSemester(String semester) {
    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getSemester().equals(semester)) {
        return true;
      }
    }
    System.out.println("This semester does not exist.");
    return false;
  }


  /**
   * Method to enrol a student for a course in a semester
   * @param sId student ID of student we want to enrol
   * @param cId course ID of course to be enrolled in
   * @param semester specific semester
   * @return true if enrolment can be added to the list, false otherwise
   */
  @Override
  public boolean add(String sId, String cId, String semester) {
    // prevent enrolment if student has already enrolled in the provided course and semester
    StudentEnrolment enrolment = getOne(sId, cId, semester);
    if (enrolment != null) {
      System.out.printf("Student %s already enrolled in course %s for semester %s\n",
          sId, cId, semester);
      return false;
    }

    // find student and course in list to enrol them
    Student student = findStudent(sId);
    Course course = findCourse(cId);
    studentEnrolments.add(new StudentEnrolment(student, course, semester));
    System.out.println("Enrol successfully!");
    // for testing
    for (StudentEnrolment studentEnrolment: studentEnrolments) {
      System.out.println(studentEnrolment);
    }
    return true;
  }


  /**
   * Method to update enrolment of a student in 1 semester by deleting or adding new courses
   * @param sId student ID of student we want to update the enrolment for
   * @param semester of the enrolment
   * @return true if enrolment is updated successfully, false otherwise
   */
  @Override
  public boolean update(String sId, String cId, String semester, String option) {
    // delete or add the specified course based on user's choice
    if (option.equals("1")) {
      return delete(sId, cId, semester);
    } else {
      return add(sId, cId, semester);
    }
  }


  /**
   * Method to delete an enrolment
   * @param sId student ID of student in the enrolment that we want to delete
   * @param cId course ID of enrolment
   * @param semester semester of the enrolment
   * @return true if enrolment is deleted from data list, false otherwise
   */
  @Override
  public boolean delete(String sId, String cId, String semester) {
    StudentEnrolment enrolment = getOne(sId, cId, semester);
    // check if enrolment info exists
    if (enrolment == null) {
      System.out.println("Cannot find the enrolment you're looking for.");
      System.out.println("Delete unsuccessful!");
      return false;
    }

    studentEnrolments.remove(enrolment);
    System.out.println("The enrolment has been deleted.");
    // FOR TESTING
    for (StudentEnrolment studentEnrolment: studentEnrolments) {
      System.out.println(studentEnrolment);
    }
    return true;
  }


  /**
   * Method to retrieve an enrolment info
   * @param sId student ID of student in the enrolment we want to retrieve
   * @param cId course ID of enrolment
   * @param semester of enrolment
   * @return StudentEnrolment object if the enrolment info exists, null otherwise
   */
  @Override
  public StudentEnrolment getOne(String sId, String cId, String semester) {
    for (StudentEnrolment enrolment : studentEnrolments) {
      if (enrolment.getStudent().getsId().equals(sId)
          && enrolment.getCourse().getcId().equals(cId)
          && enrolment.getSemester().equals(semester)) {
        return enrolment;
      }
    }
    return null;
  }


  /**
   * Method to get all enrolment in system
   * @return list of student enrolment if it's not empty, null otherwise
   */
  @Override
  public List<StudentEnrolment> getAll() {
    if (studentEnrolments.isEmpty()) {
      System.out.println("There is currently no enrolment.");
      return null;
    }
    return studentEnrolments;
  }


  public List<Student> getStudentsInCourse(String cId, String semester) {
    List<Student> students = new ArrayList<Student>();

    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getCourse().getcId().equals(cId)
          && enrolment.getSemester().equals(semester))
      {
        students.add(enrolment.getStudent());
      }
    }
    return students;
  }

  public List<Course> getCoursesOfStudent(String sId, String semester) {
    List<Course> courses = new ArrayList<Course>();

    for (StudentEnrolment enrolment : studentEnrolments) {
      if (enrolment.getStudent().getsId().equals(sId)
          && enrolment.getSemester().equals(semester)) {
        courses.add(enrolment.getCourse());
      }
    }
    return courses;
  }


  public List<Course> getCoursesInSem(String sem) {
    List<Course> courses = new ArrayList<Course>();

    for (StudentEnrolment enrolment: studentEnrolments) {
      if (enrolment.getSemester().equals(sem)) {
        courses.add(enrolment.getCourse());
      }
    }
    return courses;
  }

}
