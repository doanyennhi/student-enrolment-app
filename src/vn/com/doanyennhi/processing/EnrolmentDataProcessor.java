package vn.com.doanyennhi.processing;

import java.util.ArrayList;
import java.util.List;
import vn.com.doanyennhi.models.Course;
import vn.com.doanyennhi.models.Student;
import vn.com.doanyennhi.models.StudentEnrolment;

/**
 *  <b> EnrolmentDataProcessor </b> class is used to process the data we got
 *  from CSV file.
 */
public class EnrolmentDataProcessor {

  /**
   * Get data from reading CSV file. Process and convert data into a StudentEnrolment object
   * @param enrolmentsData list of enrolment data in string format
   * @return list of all enrolments as StudentEnrolment objects
   */
  public List<StudentEnrolment> processEnrolmentData(List<String[]> enrolmentsData) {
    List<StudentEnrolment> studentEnrolmentList = new ArrayList<StudentEnrolment>();

    for (String[] enrolment: enrolmentsData) {
      // Create Student, Course, StudentEnrolment objects from data in the data array
      Student student = new Student(enrolment[0], enrolment[1], enrolment[2]);
      Course course = new Course(enrolment[3], enrolment[4], Integer.parseInt(enrolment[5]));
      StudentEnrolment studentEnrolment = new StudentEnrolment(student, course, enrolment[6]);

      studentEnrolmentList.add(studentEnrolment);
    }
    return studentEnrolmentList;
  }
}
