package vn.com.doanyennhi.app;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import vn.com.doanyennhi.models.Course;
import vn.com.doanyennhi.models.Student;
import vn.com.doanyennhi.models.StudentEnrolment;
import vn.com.doanyennhi.models.StudentEnrolmentManagerImpl;
import vn.com.doanyennhi.models.interfaces.Csv;
import vn.com.doanyennhi.processing.CsvImpl;
import vn.com.doanyennhi.processing.EnrolmentDataProcessor;

public class Main {

  /**
   * Keep asking user for a semester until the semester entered exists in the system
   * @param sc scanner to get user input
   * @param manager the student enrolment management program
   * @return the valid semester
   */
  public static String getValidSemester(Scanner sc, StudentEnrolmentManagerImpl manager) {
    System.out.print("Please enter the semester: ");
    String sem = sc.nextLine();
    System.out.println();

    while (!manager.hasSemester(sem)) {
      System.out.print("Please enter again: ");
      sem = sc.nextLine();
      System.out.println();
    }
    return sem;
  }


  /**
   * Keep asking user for a student ID until the ID entered exists in the system
   * @param sc scanner to get user input
   * @param manager the student enrolment management program
   * @return valid student ID
   */
  public static String getValidStudentId(Scanner sc, StudentEnrolmentManagerImpl manager) {
    System.out.print("Please enter the student ID: ");
    String sID = sc.nextLine();
    System.out.println();

    while (manager.findStudent(sID) == null) {
      System.out.print("Please enter again: ");
      sID = sc.nextLine();
      System.out.println();
    }
    return sID;
  }


  /**
   * Keep asking user for a course ID until the ID entered exists in the system
   * @param sc scanner to get user input
   * @param manager the student enrolment management program
   * @return valid course ID
   */
  public static String getValidCourseId(Scanner sc, StudentEnrolmentManagerImpl manager) {
    System.out.print("Please enter the course ID: ");
    String cID = sc.nextLine();
    System.out.println();

    while (manager.findCourse(cID) == null) {
      System.out.print("Please enter again: ");
      cID = sc.nextLine();
      System.out.println();
    }
    return cID;
  }


  // TODO: add comments, add prompt user if they want to continue, uppercase user input
  public static void main(String[] args) {
    Csv csv = new CsvImpl();
    List<String[]> dataList = csv.readCsv("csv/default.csv");

    EnrolmentDataProcessor processor = new EnrolmentDataProcessor();
    List<StudentEnrolment> enrolmentList = processor.processEnrolmentData(dataList);
    StudentEnrolmentManagerImpl manager = new StudentEnrolmentManagerImpl(enrolmentList);
    Scanner sc = new Scanner(System.in);

    // print system's header and menu
    System.out.println("""
                
        ------------------------------------------------
        Welcome to STUDENT ENROLMENT MANAGEMENT SYSTEM!
        ------------------------------------------------
        """);

    while (true) {
      System.out.println();
      System.out.print("""
          What would you like to do?
          1. Enrol a student for 1 semester
          2. Update an enrolment of a student for 1 semester
          3. View all courses for 1 student in 1 semester
          4. View all students of 1 course in 1 semester
          5. View all courses offered in 1 semester
          6. Quit
                
          Please enter a number (1-6) to choose an option:\s"""
      );

      String option = sc.nextLine();
      System.out.println("------------------------------------------------");
      System.out.println();

      // re-display the menu if user enters invalid option
      if (!option.equals("1") && !option.equals("2") && !option.equals("3")
          && !option.equals("4") && !option.equals("5") && !option.equals("6")) {
        System.out.println("Your option is invalid. Please check the menu again.");
        System.out.println();
        continue;
      }

      if (option.equals("6")) {
        break;
      }

      String sem = getValidSemester(sc, manager);

      if (option.equals("1")) {
        String sID = getValidStudentId(sc, manager);
        String cID = getValidCourseId(sc, manager);
        manager.add(sID, cID, sem);

      } else if (option.equals("2")) {
        String sID = getValidStudentId(sc, manager);
        List<Course> coursesOfStudent = manager.getCoursesOfStudent(sID, sem);
        if (coursesOfStudent != null) {
          System.out.printf("Here is the list of courses for student %s in semester %s: \n", sID, sem);
          coursesOfStudent.forEach(course -> System.out.print(course));
        }

        System.out.print("""
              
              Please enter 1 to delete a course or 2 to add a course
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

        String cID = getValidCourseId(sc, manager);
        manager.update(sID, cID, sem, input);

      } else if (option.equals("3")) {
        String sID = getValidStudentId(sc, manager);
        List<Course> coursesOfStudent = manager.getCoursesOfStudent(sID, sem);
        if (coursesOfStudent != null) {
          System.out.printf("Courses of student %s in semester %s: \n", sID, sem);
          coursesOfStudent.forEach(course -> System.out.print(course));
        }

      } else if (option.equals("4")) {
        String cID = getValidCourseId(sc, manager);
        List<Student> studentsInCourse = manager.getStudentsInCourse(cID, sem);
        if (studentsInCourse != null) {
          System.out.printf("Students enrolled in course %s in semester %s: \n", cID, sem);
          studentsInCourse.forEach(student -> System.out.print(student));
        }

      } else {
        Set<Course> coursesInSem = manager.getCoursesInSem(sem);
        System.out.printf("Courses offered in semester %s: \n", sem);
        coursesInSem.forEach(course -> System.out.println(course));
      }
    }
  }
}

