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
    return "Enrolment \n" +
        "student: " + this.student.toString() +
        "course: " + this.course.toString() +
        "semester: " + this.semester + "\n";
  }
}
