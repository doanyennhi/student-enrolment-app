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
//    System.out.println("Student ID: ");
//    String sID = sc.nextLine();
//    System.out.println("Course ID: ");
//     String cID = sc.nextLine();
    System.out.println("Semester: ");
    String sem = sc.nextLine();

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
    // TODO: move all print functions to SEMimpl, check for duplicates, test again
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
