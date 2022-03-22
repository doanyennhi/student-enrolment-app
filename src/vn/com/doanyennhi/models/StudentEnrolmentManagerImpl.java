package vn.com.doanyennhi.models;

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
   * Method to enrol a student for a course in a semester
   * @param sId student ID
   * @param cId course ID
   * @param semester specific semester
   * @return true if enrolment is successful, false otherwise
   */
  @Override
  public boolean add(String sId, String cId, String semester) {
    // check if student has already enrolled in the provided course and semester
    StudentEnrolment enrolment = getOne(sId, cId, semester);
    if (enrolment != null) {
      System.out.printf("Student %s already enrolled in course %s for semester %s\n",
          sId, cId, semester);
      return false;
    }

    // TODO: rewrite logic with findStudent and findCourse
    for (StudentEnrolment studentEnrolment: studentEnrolments) {
      if (studentEnrolment.getStudent().getsId().equals(sId)
          && studentEnrolment.getCourse().getcId().equals(cId))
      {
        Student student = studentEnrolment.getStudent();
        Course course = studentEnrolment.getCourse();
        studentEnrolments.add(new StudentEnrolment(student, course, semester));
        System.out.println("Enrol successfully!");
        return true;
      }
    }
    return false;
  }


  /**
   * Method to update enrolment of a student in 1 semester by deleting or adding new courses
   * @param sId student ID of student we want to update the enrolment for
   * @param semester of the enrolment
   * @return true if enrolment is updated successfully, false otherwise
   */
  @Override
  public boolean update(String sId, String semester) {
    // TODO: display courses
    Scanner sc = new Scanner(System.in);
    System.out.print("""
      Please enter a number if you would like to delete or add new courses
      1. Delete
      2. Add
    >>>>>\s"""
    );

    String input = sc.nextLine();
    // keep asking for user input until they enter one of the valid options
    while (!input.equals("1") && !input.equals("2")) {
      System.out.print("Please enter a valid option: ");
      input = sc.nextLine();
    }

    // delete or add the specified course based on user's choice
    System.out.print("Please enter the course ID: ");
    String cId = sc.nextLine();
    if (input.equals("1")) {
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
      System.out.println("Delete unsuccessful!");
      return false;
    }
    studentEnrolments.remove(enrolment);
    System.out.println("The enrolment has been deleted.");
    // FOR TESTING
//    for (StudentEnrolment studentEnrolment: studentEnrolments) {
//      System.out.println(studentEnrolment);
//    }
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
    System.out.println("Cannot find the enrolment you're looking for.");
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

}
