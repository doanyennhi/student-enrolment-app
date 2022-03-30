package vn.com.doanyennhi.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import vn.com.doanyennhi.models.Course;
import vn.com.doanyennhi.models.Student;
import vn.com.doanyennhi.models.StudentEnrolment;
import vn.com.doanyennhi.models.StudentEnrolmentManagerImpl;
import vn.com.doanyennhi.models.interfaces.Csv;
import vn.com.doanyennhi.processing.CsvImpl;
import vn.com.doanyennhi.processing.DataProcessor;


/**
 * @author Doan Yen Nhi (s3880599)
 * Course: Further Programming (COSC2440)
 * Instructor: Minh Thanh Vu
 * RMIT University Vietnam - Semester 1, 2022
 *
 * This is a Java console app that can be used to manage student enrolment for a school.
 * It can perform various enrolment-related functionalities, such as enrolling a student
 * in a course or update an enrolment.
 */
public class Main {

  /**
   * Keep asking user for a semester until the semester entered exists in the system
   * @param sc scanner to get user input
   * @param manager the student enrolment management program
   * @return the valid semester
   */
  public static String getValidSemester(Scanner sc, StudentEnrolmentManagerImpl manager) {
    System.out.print("Please enter the semester: ");
    String sem = sc.nextLine().toUpperCase();
    System.out.println();

    // user has to enter semester again until the semester entered is found in the database
    while (!manager.hasSemester(sem)) {
      System.out.print("Please enter again: ");
      sem = sc.nextLine().toUpperCase();
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
    String sID = sc.nextLine().toUpperCase();
    System.out.println();

    // user has to enter student ID again until the ID entered is found in the database
    while (manager.findStudent(sID) == null) {
      System.out.print("Please enter again: ");
      sID = sc.nextLine().toUpperCase();
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
    String cID = sc.nextLine().toUpperCase();
    System.out.println();

    // user has to enter course ID again until the ID entered is found in the database
    while (manager.findCourse(cID) == null) {
      System.out.print("Please enter again: ");
      cID = sc.nextLine().toUpperCase();
      System.out.println();
    }
    return cID;
  }

  public static String getValidAnswer(Scanner sc, String msg) {
    System.out.print(msg);
    String input = sc.nextLine().toLowerCase();

    // keep asking for user input until user enters valid yes/ no answer
    while (!input.equals("y")
        && !input.equals("n")
        && !input.equals("yes")
        && !input.equals("no"))
    {
      System.out.print("Please enter either yes/no or y/n: ");
      input = sc.nextLine().toLowerCase();
    }
    return input;
  }

//  public static void saveToCsv(String file, Csv csv, DataProcessor processor, List) {
//      try {
//        csv.writeCsv(file, processor.convertCourseToCsv(coursesOfStudent));
//        System.out.println("Report is saved to file " + file);
//      } catch (IOException e) {
//        System.out.println(e.getMessage());
//        System.exit(0);     // close system if there is problem with file
//      }
//  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<String[]> dataList = new ArrayList<String[]>();
    // messages to ask users
    String saveCsvMsg = "\nWould you like to save this to a CSV file (yes/no): ";
    String continueMsg = "\nWould you like to continue (y/n): ";

    // asks for csv file path or use default
    Csv csv = new CsvImpl();
    System.out.print("Please enter a CSV file path (type 'default' to use the default file): ");
    String path = sc.nextLine();
    if (path.equals("default")) {
      path = "csv/default.csv";
    }

    // try to read data from CSV file
    try {
      dataList = csv.readCsv(path);
    } catch (IOException e) {
      System.out.println(e.getMessage());
      System.exit(0);     // close system if there is problem with file path
    }

    // process data into StudentEnrolment objects
    DataProcessor processor = new DataProcessor();
    List<StudentEnrolment> enrolmentList = processor.convertCsvToEnrolment(dataList);

    StudentEnrolmentManagerImpl manager = new StudentEnrolmentManagerImpl(enrolmentList);

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
        break;      // exit system
      }

      String sem = getValidSemester(sc, manager);

      if (option.equals("1")) {
        String sID = getValidStudentId(sc, manager);
        String cID = getValidCourseId(sc, manager);
        manager.add(sID, cID, sem);

      } else if (option.equals("2")) {
        String sID = getValidStudentId(sc, manager);
        List<Course> coursesOfStudent = manager.getCoursesOfStudent(sID, sem);
        // display the list of courses for a student in a semester if it's not empty
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
        // display all courses if the course list is not empty
        if (coursesOfStudent != null) {
          System.out.printf("Courses of student %s in semester %s: \n", sID, sem);
          coursesOfStudent.forEach(course -> System.out.print(course));

          // asks user if they want to save report to CSV file
          String input = getValidAnswer(sc, saveCsvMsg);
          if (input.equals("y") || input.equals("yes")) {
            String file = "courses_" + sID + "_" + sem + ".csv";
            try {
              csv.writeCsv(file, processor.convertCourseToCsv(coursesOfStudent));
            } catch (IOException e) {
              System.out.println(e.getMessage());
            }
            System.exit(0);
          }
        }

      } else if (option.equals("4")) {
        String cID = getValidCourseId(sc, manager);
        List<Student> studentsInCourse = manager.getStudentsInCourse(cID, sem);
        // display list of students for a course in a semester if it's not empty
        if (studentsInCourse != null) {
          System.out.printf("Students enrolled in course %s in semester %s: \n", cID, sem);
          studentsInCourse.forEach(student -> System.out.print(student));

          String input = getValidAnswer(sc, saveCsvMsg);
          if (input.equals("y") || input.equals("yes")) {
            String file = "students_" + cID + "_" + sem + ".csv";

            try {
              csv.writeCsv(file, processor.convertStudentToCsv(studentsInCourse));
            } catch (IOException e) {
              System.out.println(e.getMessage());
            }
            System.exit(0);
          }
        }

      } else {   // TODO: implement save CSV
        Set<Course> coursesInSem = manager.getCoursesInSem(sem);
        System.out.printf("Courses offered in semester %s: \n", sem);
        coursesInSem.forEach(course -> System.out.println(course));

      }

      // allows user to continue the program or exit
      String input = getValidAnswer(sc, continueMsg);
      if (input.equals("n") || input.equals("no")) {
        break;
      }
    }
  }
}

