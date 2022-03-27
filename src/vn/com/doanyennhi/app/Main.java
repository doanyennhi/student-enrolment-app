package vn.com.doanyennhi.app;

import java.util.List;
import java.util.Scanner;
import vn.com.doanyennhi.models.Course;
import vn.com.doanyennhi.models.Student;
import vn.com.doanyennhi.models.StudentEnrolmentManagerImpl;

public class Main {

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

  public static void main(String[] args) {
    StudentEnrolmentManagerImpl manager = new StudentEnrolmentManagerImpl("csv/default.csv");
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
          coursesOfStudent.forEach(course -> System.out.print(course));
        }

      } else if (option.equals("4")) {
        String cID = getValidCourseId(sc, manager);
        List<Student> studentsInCourse = manager.getStudentsInCourse(cID, sem);
        if (studentsInCourse != null) {
          studentsInCourse.forEach(student -> System.out.print(student));
        }

      } else {

      }
    }
  }
}

