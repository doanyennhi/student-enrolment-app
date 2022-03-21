package vn.com.doanyennhi.models;

public class StudentEnrolment {
  private Student student;
  private Course course;
  private String semester;

  public StudentEnrolment(Student student, Course course, String semester) {
    this.student = student;
    this.course = course;
    this.semester = semester;
  }

  @Override
  public String toString() {
    return "Enrolment \n " +
        "student ID: " + student.getsId() +
        ", course ID: " + course.getcId() +
        ", semester: " + semester + "\n";
  }
}
