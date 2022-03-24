package vn.com.doanyennhi.app;

import java.util.List;
import java.util.Scanner;
import jdk.swing.interop.SwingInterOpUtils;
import vn.com.doanyennhi.models.Course;
import vn.com.doanyennhi.models.Student;
import vn.com.doanyennhi.models.StudentEnrolment;
import vn.com.doanyennhi.models.StudentEnrolmentManagerImpl;
import vn.com.doanyennhi.models.interfaces.StudentEnrolmentManager;

public class Main {

  public static void main(String[] args) {
    StudentEnrolmentManagerImpl manager = new StudentEnrolmentManagerImpl("csv/default.csv");
    List<StudentEnrolment> enrolments = manager.getAll();
    Scanner sc = new Scanner(System.in);

    // print system's header and menu
    System.out.println("""
        ------------------------------------------------
        Welcome to STUDENT ENROLMENT MANAGEMENT SYSTEM!
        ------------------------------------------------
        
        """);
    System.out.print("""
      What would you like to do?
      1. Enrol a student for 1 semester
      2. Update an enrolment of a student for 1 semester
      3. View all courses for 1 student in 1 semester
      4. View all students of 1 course in 1 semester
      5. View all courses offered in 1 semester
      6. Quit
      
      Please enter a number (1-7) to choose an option:\s"""
    );

    String option = sc.nextLine();
    System.out.println("Please enter the semester: ");
    String sem = sc.nextLine();

    if (option.equals("1") || option.equals("2") || option.equals("3")) {
      System.out.println("Please enter the student ID: ");
      String sID = sc.nextLine();

      if (option.equals("1")) {
        System.out.println("Please enter the course ID you want to enrol: ");
        String cID = sc.nextLine();
        manager.add(sID, cID, sem);
      }
    }

    if (option.equals("6")) {
      System.out.println(manager.getAll());
    }

//    System.out.println("Course ID: ");
//     String cID = sc.nextLine();

    // Print all courses for 1 student in one sem
//    for (StudentEnrolment enrolment: enrolments) {
//      if (enrolment.getStudent().equals(manager.findStudent(sID))
//          && enrolment.getSemester().equals(sem)) {
//        System.out.println(enrolment.getCourse());
//      }
//    }

    // Print all students of 1 course in 1 sem
//    for (StudentEnrolment enrolment: enrolments) {
//      if (enrolment.getCourse().equals(manager.findCourse(cID))
//          && enrolment.getSemester().equals(sem)) {
//        System.out.println(enrolment.getStudent());
//      }
//    }

    // Print all courses for one sem
    // TODO: decide if I want to put these functions in Main or SEMImpl
    for (StudentEnrolment enrolment: enrolments) {
      if (enrolment.getSemester().equals(sem)) {
        System.out.println(enrolment.getCourse());
      }
    }

    //System.out.println(enrolments);

//    Student student = manager.findStudent(sId);
//    Course course = findCourse(cId);
//    String semester = findSemester(sem);
//    if (student  == null | course == null | semester == null) {
//      // cannot enrol as info provided cannot be found
//      return false;
//    }


  }

}
