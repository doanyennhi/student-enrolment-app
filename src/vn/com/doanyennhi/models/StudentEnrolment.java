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

  public Student getStudent() {
    return student;
  }

  public Course getCourse() {
    return course;
  }

  public String getSemester() {
    return semester;
  }

  @Override
  public String toString() {
    return "Enrolment \n " +
        "student ID: " + this.student.getsId() +
        ", course ID: " + this.course.getcId() +
        ", semester: " + this.semester + "\n";
  }
}
