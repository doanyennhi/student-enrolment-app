package vn.com.doanyennhi.processing;

import java.util.ArrayList;
import java.util.List;
import vn.com.doanyennhi.models.Course;
import vn.com.doanyennhi.models.Student;
import vn.com.doanyennhi.models.StudentEnrolment;

/**
 *  <b> DataProcessor </b> class is used to process the data we got
 *  from CSV file and convert our data to a format that can be written to CSV file.
 */
public class DataProcessor {

  /**
   * Get data from reading CSV file. Process and convert data into a StudentEnrolment object
   * @param enrolmentsData list of enrolment data in string format
   * @return list of all enrolments as StudentEnrolment objects
   */
  public List<StudentEnrolment> convertCsvToEnrolment(List<String[]> enrolmentsData) {
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


  /**
   * Convert Student objects to array of strings to be written to CSV file
   * @param students list
   * @return list of string arrays containing student data
   */
  public List<String[]> convertStudentToCsv(List<Student> students) {
    List<String[]> csvData = new ArrayList<String[]>();

    // get all info of student in array of string
    for (Student student: students) {
      String[] dataLine = {
          student.getSId(),
          student.getName(),
          student.getBirthdate(),
      };
      csvData.add(dataLine);
    }
    return csvData;
  }


  // similar to function above but convert Course to array of strings
  public List<String[]> convertCourseToCsv(List<Course> courses) {
    List<String[]> csvData = new ArrayList<String[]>();

    // get all info of course in array of string
    for (Course course: courses) {
      String[] dataLine = {
          course.getCId(),
          course.getName(),
          Integer.toString(course.getCredits()),
      };
      csvData.add(dataLine);
    }
    return csvData;
  }
}
