package vn.com.doanyennhi.models;

import java.util.ArrayList;

public class StudentEnrolmentManagerImpl {

  private ArrayList<StudentEnrolment> studentEnrolments;

  public StudentEnrolmentManagerImpl() {
    this.studentEnrolments = new ArrayList<StudentEnrolment>();
  }

  public ArrayList<StudentEnrolment> getStudentEnrolments() {
    return studentEnrolments;
  }

  public void setStudentEnrolments(ArrayList<StudentEnrolment> studentEnrolments) {
    this.studentEnrolments = studentEnrolments;
  }


}
