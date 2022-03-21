package vn.com.doanyennhi.processing;

import java.util.ArrayList;
import java.util.List;
import vn.com.doanyennhi.models.Course;
import vn.com.doanyennhi.models.Student;
import vn.com.doanyennhi.models.StudentEnrolment;

public class EnrolmentDataProcessor {

  public static ArrayList<StudentEnrolment> processEnrolmentData(String path) {
    List<String[]> enrolmentsData = Csv.readCsv(path);
    ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<StudentEnrolment>();

    for (String[] enrolment: enrolmentsData) {
      Student student = new Student(enrolment[0], enrolment[1], enrolment[2]);
      Course course = new Course(enrolment[3], enrolment[4], Integer.parseInt(enrolment[5]));
      StudentEnrolment studentEnrolment = new StudentEnrolment(student, course, enrolment[6]);

      studentEnrolmentList.add(studentEnrolment);
    }
    return studentEnrolmentList;
  }
}
